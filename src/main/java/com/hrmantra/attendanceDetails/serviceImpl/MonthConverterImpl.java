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
        monthAbbreviationsToFullNames.put("JAN", "January");
        monthAbbreviationsToFullNames.put("FEB", "February");
        monthAbbreviationsToFullNames.put("MAR", "March");
        monthAbbreviationsToFullNames.put("APR", "April");
        monthAbbreviationsToFullNames.put("MAY", "May");
        monthAbbreviationsToFullNames.put("JUN", "June");
        monthAbbreviationsToFullNames.put("JUL", "July");
        monthAbbreviationsToFullNames.put("AUG", "August");
        monthAbbreviationsToFullNames.put("SEP", "September");
        monthAbbreviationsToFullNames.put("OCT", "October");
        monthAbbreviationsToFullNames.put("NOV", "November");
        monthAbbreviationsToFullNames.put("DEC", "December");
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
