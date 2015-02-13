package com.safien.code2015.disasterapp.entity;

import com.safien.code2015.disasterapp.util.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by salman on 2015-02-01.
 */
@Document(collection = "disasters")
public class DisasterEntity {

    @Id
    private String id;
    private String eventCategory;
    private String eventGroup;
    private String eventSubGroup;
    private String eventType;
    private String place;
    private Date eventStartDate;
    private Date eventEndDate;
    private String comments;
    private int fatalities;
    private int injured;
    private int evacuated;
    private double estimatedTotalCost;
    private double normalizedTotalCost;
    private double federalDFaaPayments;
    private double provincialDFaaPayments;
    private double provincialDepartmentPayments;
    private double municipalCosts;
    private double ogdCosts;
    private double insurancePayments;
    private double ngoPayments;
    private int peopleAffected;
    private double magnitude;
    private Point location;

    public double getProvincialDepartmentPayments() {
        return provincialDepartmentPayments;
    }

    public void setProvincialDepartmentPayments(double provincialDepartmentPayments) {
        this.provincialDepartmentPayments = provincialDepartmentPayments;
    }

    public double getInsurancePayments() {
        return insurancePayments;
    }

    public void setInsurancePayments(double insurancePayments) {
        this.insurancePayments = insurancePayments;
    }

    public double getNgoPayments() {
        return ngoPayments;
    }

    public void setNgoPayments(double ngoPayments) {
        this.ngoPayments = ngoPayments;
    }

    public int getPeopleAffected() {
        return peopleAffected;
    }

    public void setPeopleAffected(int peopleAffected) {
        this.peopleAffected = peopleAffected;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventGroup() {
        return eventGroup;
    }

    public void setEventGroup(String eventGroup) {
        this.eventGroup = eventGroup;
    }

    public String getEventSubGroup() {
        return eventSubGroup;
    }

    public void setEventSubGroup(String eventSubGroup) {
        this.eventSubGroup = eventSubGroup;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

	@JsonSerialize(using=JsonDateSerializer.class)
    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

	@JsonSerialize(using=JsonDateSerializer.class)
    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getFatalities() {
        return fatalities;
    }

    public void setFatalities(int fatalities) {
        this.fatalities = fatalities;
    }

    public int getInjured() {
        return injured;
    }

    public void setInjured(int injured) {
        this.injured = injured;
    }

    public int getEvacuated() {
        return evacuated;
    }

    public void setEvacuated(int evacuated) {
        this.evacuated = evacuated;
    }

    public double getEstimatedTotalCost() {
        return estimatedTotalCost;
    }

    public void setEstimatedTotalCost(double estimatedTotalCost) {
        this.estimatedTotalCost = estimatedTotalCost;
    }

    public double getNormalizedTotalCost() {
        return normalizedTotalCost;
    }

    public void setNormalizedTotalCost(double normalizedTotalCost) {
        this.normalizedTotalCost = normalizedTotalCost;
    }

    public double getFederalDFaaPayments() {
        return federalDFaaPayments;
    }

    public void setFederalDFaaPayments(double federalDFaaPayments) {
        this.federalDFaaPayments = federalDFaaPayments;
    }

    public double getProvincialDFaaPayments() {
        return provincialDFaaPayments;
    }

    public void setProvincialDFaaPayments(double provincialDFaaPayments) {
        this.provincialDFaaPayments = provincialDFaaPayments;
    }

    public double getMunicipalCosts() {
        return municipalCosts;
    }

    public void setMunicipalCosts(double municipalCosts) {
        this.municipalCosts = municipalCosts;
    }

    public double getOgdCosts() {
        return ogdCosts;
    }

    public void setOgdCosts(double ogdCosts) {
        this.ogdCosts = ogdCosts;
    }
}
