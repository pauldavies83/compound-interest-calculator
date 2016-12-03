package uk.co.pauldavies83.compoundinterestcalculator.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompoundCalculatorService {
    public List<Double> getFiveYearProjection(double deposit, double percentageRate) throws NegativeNumberArgumentExeption {
        List<Double> results = new ArrayList<>();

        if (deposit < 0 || percentageRate < 0) throw new NegativeNumberArgumentExeption("deposits cannot be less than zero");

        double decimalRate = percentageRate / 100;

        for (int i = 1; i <= 5; i++) {
            BigDecimal raw = BigDecimal.valueOf(deposit * Math.pow((1 + decimalRate), i));
            raw = raw.setScale(2, BigDecimal.ROUND_HALF_UP);
            results.add(raw.doubleValue());
        }

        return results;
    }

    public static class NegativeNumberArgumentExeption extends Exception {
        public NegativeNumberArgumentExeption(String s) {
            super(s);
        }
    }
}
