package com.safien.code2015.disasterapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

/**
 * Created by salman on 2015-02-01.
 */
@Document(collection = "disasters")
public class DisastersEntity {

    @Id
    private String id;
    private String eventCategory;
    private String eventGroup;
    private String eventSubGroup;
    private String eventType;
    private String place;
    private Timestamp eventStartDate;
    private Timestamp eventEndDate;
    private String comments;
    private int fatalities;
    private int injured;
    private int evacuated;
    private double estimatedTotalCost;
    private double normalizedTotalCost;
    private double fedralDFFAPayments;
    private double provincialDFFAPayments;
    private double municipalCosts;
    private double ogdCosts;

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

    public Timestamp getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Timestamp eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Timestamp getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Timestamp eventEndDate) {
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

    public double getFedralDFFAPayments() {
        return fedralDFFAPayments;
    }

    public void setFedralDFFAPayments(double fedralDFFAPayments) {
        this.fedralDFFAPayments = fedralDFFAPayments;
    }

    public double getProvincialDFFAPayments() {
        return provincialDFFAPayments;
    }

    public void setProvincialDFFAPayments(double provincialDFFAPayments) {
        this.provincialDFFAPayments = provincialDFFAPayments;
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
