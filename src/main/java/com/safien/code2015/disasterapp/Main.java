package com.safien.code2015.disasterapp;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by salman on 2015-02-08.
 */
public class Main {

    public static void main(String[] args) {
        try(ICsvBeanReader csvBeanReader = new CsvBeanReader(new InputStreamReader(new FileInputStream(new File("CDD.csv"))),
                                                             CsvPreference.STANDARD_PREFERENCE)) {
            final String[] header = csvBeanReader.getHeader(true);
//            System.out.println(header.length);
            for (int i = 0; i < header.length; i++) {
//                System.out.println(header[i]);
                String lowerString = header[i].toLowerCase();
                String strArray[] = lowerString.split(" ");
                StringBuilder stringBuilder = new StringBuilder(strArray[0]);
                for (int j = 1; j < strArray.length; j++) {
                    char firstLetter = strArray[j].charAt(0);
                    stringBuilder.append(Character.toString(firstLetter).toUpperCase() + strArray[j].substring(1));
                }
//                System.out.println(stringBuilder);
                System.out.println("headerMap.put(\"" + header[i] + "\", \"" + stringBuilder + "\");");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
