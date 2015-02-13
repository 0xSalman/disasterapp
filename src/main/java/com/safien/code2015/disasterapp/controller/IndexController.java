package com.safien.code2015.disasterapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.safien.code2015.disasterapp.dto.UserRequest;
import com.safien.code2015.disasterapp.entity.DisasterEntity;
import com.safien.code2015.disasterapp.service.DisasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by salman on 2014-11-15.
 */
@Controller
public class IndexController {

	private static final String GOOGLE_API_KEY = "AIzaSyC8AAkfoIlqIQEJZXkujCUlO-kvuHkx6OU";
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private GeoApiContext context = new GeoApiContext().setApiKey(GOOGLE_API_KEY);

	@Autowired
	private DisasterRepository disasterRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index.html";
	}

//    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
//    @RequestMapping(value = "/login/{username}:{password}", method = RequestMethod.GET, produces = "application/json")


	@RequestMapping(value = "loaddata", method = RequestMethod.GET)
	public String loadData() {

		try {

			Map<String, String> headerMap = new HashMap<>();
			headerMap.put("EVENT CATEGORY", "eventCategory");
			headerMap.put("EVENT GROUP", "eventGroup");
			headerMap.put("EVENT SUBGROUP", "eventSubgroup");
			headerMap.put("EVENT TYPE", "eventType");
			headerMap.put("PLACE", "place");
			headerMap.put("EVENT START DATE", "eventStartDate");
			headerMap.put("COMMENTS", "comments");
			headerMap.put("FATALITIES", "fatalities");
			headerMap.put("INJURED / INFECTED", "injured");
			headerMap.put("EVACUATED", "evacuated");
			headerMap.put("ESTIMATED TOTAL COST", "estimatedTotalCost");
			headerMap.put("NORMALIZED TOTAL COST", "normalizedTotalCost");
			headerMap.put("EVENT END DATE", "eventEndDate");
			headerMap.put("FEDERAL DFAA PAYMENTS", "federalDfaaPayments");
			headerMap.put("PROVINCIAL DFAA PAYMENTS", "provincialDfaaPayments");
			headerMap.put("PROVINCIAL DEPARTMENT PAYMENTS", "provincialDepartmentPayments");
			headerMap.put("MUNICIPAL COSTS", "municipalCosts");
			headerMap.put("OGD COSTS", "ogdCosts");
			headerMap.put("INSURANCE PAYMENTS", "insurancePayments");
			headerMap.put("NGO PAYMENTS", "ngoPayments");
			headerMap.put("UTILITY - PEOPLE AFFECTED", "peopleAffected");
			headerMap.put("MAGNITUDE", "magnitude");

			try(ICsvBeanReader csvBeanReader = new CsvBeanReader(new InputStreamReader(new FileInputStream(new File("CDD.csv"))),
					CsvPreference.STANDARD_PREFERENCE)) {
				final String[] header = csvBeanReader.getHeader(true);
				CellProcessor[] cellProcessors = new CellProcessor[headerMap.size()];
				String[] beanFields = new String[headerMap.size()];
				for (int i = 0; i < header.length; i++) {
					beanFields[i] = headerMap.get(header[i]);
					if(beanFields[i].contains("Cost") || beanFields[i].contains("Payment") || beanFields[i].equals("magnitude")) {
						cellProcessors[i] = new Optional(new ParseDouble());
					}
					else if(beanFields[i].contains("Date")) {
						cellProcessors[i] = new Optional(new ParseDate("dd/MM/yyyy"));
					}
					else if (beanFields[i].equals("fatalities") || beanFields[i].equals("injured") ||
							beanFields[i].equals("evacuated") || beanFields[i].equals("peopleAffected")) {
						cellProcessors[i] = new Optional(new ParseInt());
					} else {
						cellProcessors[i] = new Optional();
					}
				}

				DisasterEntity disaster;
				while((disaster = csvBeanReader.read(DisasterEntity.class, beanFields, cellProcessors)) != null) {
					disaster.setLocation(getCordForAddress(disaster.getPlace()));
					// ignore bad data or addresses which couldn't be found
					if (disaster.getLocation().getX() != 0 && disaster.getLocation().getX() != 0) {
						disasterRepository.save(disaster);
					}
				}
			}
			return "Successfully uploaded data to database";
		} catch (Exception e) {
			logger.error("Error - ", e);
			return "Failed to upload data to database";
		}
	}

	@RequestMapping(value = "/findDisasters", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<DisasterEntity> findNearbyDisasters(@RequestParam("jsonRequest")String jsonRequest) {

		List<DisasterEntity> disasters = null;

		try {
			UserRequest userRequest = new ObjectMapper().readValue(jsonRequest, UserRequest.class);
			Point point = new Point(userRequest.getX(), userRequest.getY());
			Distance distance = new Distance(userRequest.getDistance(), Metrics.KILOMETERS);
			disasters = disasterRepository.findByLocationNear(point, distance);
		} catch (IOException e) {
			logger.error("Error - ", e);
		}

		return disasters;
//		data: JSON.stringify(array),
	}

	@RequestMapping(value = "/allDisasters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DisasterEntity>getAllDisasters() {

		List<DisasterEntity> disasters = null;

		try {
			disasters = disasterRepository.findAll();
		} catch (Exception e) {
			logger.error("Error - ", e);
		}

		return disasters;
	}

	private Point getCordForAddress(String address) throws Exception {

		GeocodingResult[] results =  GeocodingApi.geocode(context, address).await();
		Point point;

		if (results.length > 0) {
			LatLng latLng = results[0].geometry.location;
			point = new Point(latLng.lng, latLng.lat);
		} else {
			point = new Point(0, 0);
		}

//		System.out.println(point);

		return point;
	}
}
