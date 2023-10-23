package com.hrmantra.attendanceDetails.serviceImpl;

import com.hrmantra.attendanceDetails.service.MonthConverter;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Service
public class MonthConverterImpl implements MonthConverter {
    private static final Map<String, String> monthAbbreviationsToFullNames = new HashMap<>();

    static {
        monthAbbreviationsToFullNames.put("Jan", "January");
        monthAbbreviationsToFullNames.put("Feb", "February");
        monthAbbreviationsToFullNames.put("Mar", "March");
        monthAbbreviationsToFullNames.put("Apr", "April");
        monthAbbreviationsToFullNames.put("May", "May");
        monthAbbreviationsToFullNames.put("Jun", "June");
        monthAbbreviationsToFullNames.put("Jul", "July");
        monthAbbreviationsToFullNames.put("Aug", "August");
        monthAbbreviationsToFullNames.put("Sep", "September");
        monthAbbreviationsToFullNames.put("Oct", "October");
        monthAbbreviationsToFullNames.put("Nov", "November");
        monthAbbreviationsToFullNames.put("Dec", "December");
    }
    @Override
    public String convertShortToFull(String stringMonth) {
        String inputUpperCase = stringMonth.toUpperCase();
        if (monthAbbreviationsToFullNames.containsKey(inputUpperCase)) {
            return monthAbbreviationsToFullNames.get(inputUpperCase);
        } else if (isValidMonth(stringMonth)) {
            return stringMonth;
        } else {
            throw new IllegalArgumentException("Invalid month: " + stringMonth);
        }
    }
    private boolean isValidMonth(String input) {
        try {
            Month.valueOf(input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
