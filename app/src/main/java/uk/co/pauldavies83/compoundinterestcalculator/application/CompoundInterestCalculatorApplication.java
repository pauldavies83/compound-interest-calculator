package uk.co.pauldavies83.compoundinterestcalculator.application;

import android.app.Application;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.data.LocalCompoundCalculatorService;

public final class CompoundInterestCalculatorApplication extends Application implements AppContainer {

    private CompoundCalculatorService service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = new LocalCompoundCalculatorService();
    }

    @Override
    public CompoundCalculatorService getCompoundCalculatorService() {
        return service;
    }
}
