package com.cmed.prescription.web.model;

import java.util.Date;

public class DailyPrescriptionCount {

    private final String day;
    private final Integer prescriptionCount;

    public DailyPrescriptionCount(String day, Integer prescriptionCount) {
        this.day = day;
        this.prescriptionCount = prescriptionCount;
    }

    public String  getDay() {
        return day;
    }

    public Integer getPrescriptionCount() {
        return prescriptionCount;
    }
}
