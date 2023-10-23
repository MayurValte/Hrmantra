package com.hrmantra.test;
import java.time.YearMonth;
import java.time.LocalDate;
public class Testing {
        public static void main(String[] args) {
            // Specify the year for which you want to get the last day of each month
            int year = 2023; // You can change this to the desired year

            for (int month = 1; month <= 12; month++) {
                YearMonth yearMonth = YearMonth.of(year, month);
                LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

                System.out.println("Last day of " + yearMonth.getMonth() + " " + year + ": " + lastDayOfMonth.getDayOfMonth());
            }
        }
}
