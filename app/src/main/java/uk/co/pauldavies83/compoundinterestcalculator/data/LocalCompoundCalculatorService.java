package uk.co.pauldavies83.compoundinterestcalculator.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.concurrency.ThreadScheduler;

public class LocalCompoundCalculatorService implements CompoundCalculatorService {

    private ThreadScheduler threadScheduler;
    private List<Double> fiveYearProjection;

    public LocalCompoundCalculatorService(ThreadScheduler threadScheduler) {
        this.threadScheduler = threadScheduler;
    }

    private List<Double> calculateFiveYearProjection(double deposit, double percentageRate) {
        List<Double> results = new ArrayList<>();
        double decimalRate = percentageRate / 100;

        for (int i = 1; i <= 5; i++) {
            BigDecimal raw = BigDecimal.valueOf(deposit * Math.pow((1 + decimalRate), i));
            raw = raw.setScale(2, BigDecimal.ROUND_HALF_UP);
            results.add(raw.doubleValue());
        }

        return results;
    }

    @Override
    public void getFiveYearProjection(final double deposit, final double percentageRate, final AsyncResult callback) throws NegativeNumberArgumentExeption {
        if (deposit < 0 || percentageRate < 0) throw new NegativeNumberArgumentExeption("deposits cannot be less than zero");
        threadScheduler.executeOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                fiveYearProjection = calculateFiveYearProjection(deposit, percentageRate);
                onFiveYearProjectionCalculated(callback);
            }
        });
    }

    private void onFiveYearProjectionCalculated(final AsyncResult callback) {
        threadScheduler.executeOnUiThread(new Runnable() {
            @Override
            public void run() {
                callback.onFiveYearProjectionResultFinished(fiveYearProjection);
            }
        });
    }
}
