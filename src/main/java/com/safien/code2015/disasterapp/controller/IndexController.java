package com.safien.code2015.disasterapp.controller;

import com.safien.code2015.disasterapp.entity.DisasterEntity;
import com.safien.code2015.disasterapp.service.DisasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by salman on 2014-11-15.
 */
@RestController
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private DisasterRepository disasterRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "Hello World";
	}

//    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
//    @RequestMapping(value = "/login/{username}:{password}", method = RequestMethod.GET, produces = "application/json")


	@RequestMapping(value = "/loaddata", method = RequestMethod.GET)
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
					} else if(beanFields[i].contains("Date")) {
						cellProcessors[i] = new Optional(new ParseDate("dd/MM/yyyy"));
					} else if (beanFields[i].equals("fatalities") || beanFields[i].equals("injured") ||
							beanFields[i].equals("evacuated") || beanFields[i].equals("peopleAffected")) {
						cellProcessors[i] = new Optional(new ParseInt());
					} else {
						cellProcessors[i] = new Optional();
					}
				}

				DisasterEntity disaster;
				while((disaster = csvBeanReader.read(DisasterEntity.class, beanFields, cellProcessors)) != null) {
					disasterRepository.save(disaster);
				}
			}
			return "Successfully uploaded data to database";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "Failed to upload data to database";
		}
	}
}
