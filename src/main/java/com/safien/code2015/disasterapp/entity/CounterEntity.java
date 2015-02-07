package com.safien.code2015.disasterapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by salman on 2014-11-16.
 */
@Document(collection = "repetitions")
public class CounterEntity {

    @Id
    @Field("_id")
    private String username;
    private int elbowCounter;
    private int shoulderCounter;
    private int shoulderMaxCounter;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getElbowCounter() {
        return elbowCounter;
    }

    public void setElbowCounter(int elbowCounter) {
        this.elbowCounter = elbowCounter;
    }

    public int getShoulderCounter() {
        return shoulderCounter;
    }

    public void setShoulderCounter(int shoulderCounter) {
        this.shoulderCounter = shoulderCounter;
    }

    public int getShoulderMaxCounter() {
        return shoulderMaxCounter;
    }

    public void setShoulderMaxCounter(int shoulderMaxCounter) {
        this.shoulderMaxCounter = shoulderMaxCounter;
    }

    public String toString() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("userName=").append(username);
        stringBuffer.append("elbowCounter=").append(elbowCounter);
        stringBuffer.append("shoulderCounter=").append(shoulderCounter);
        stringBuffer.append("shoulderMaxCounter=").append(shoulderMaxCounter);

        return stringBuffer.toString();
    }
}
