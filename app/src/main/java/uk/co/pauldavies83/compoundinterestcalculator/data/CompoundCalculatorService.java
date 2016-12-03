package uk.co.pauldavies83.compoundinterestcalculator.data;

import java.util.List;

public interface CompoundCalculatorService {

    interface AsyncResult {
        void onFiveYearProjectionResultFinished(List<Double> fiveYearProjection);
    }

    void getFiveYearProjection(double deposit, double percentageRate, AsyncResult callback) throws LocalCompoundCalculatorService.NegativeNumberArgumentExeption;

    class NegativeNumberArgumentExeption extends Exception {
        public NegativeNumberArgumentExeption(String s) {
            super(s);
        }
    }
}
