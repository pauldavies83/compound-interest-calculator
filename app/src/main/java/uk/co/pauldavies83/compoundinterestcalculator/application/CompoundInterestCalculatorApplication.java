package uk.co.pauldavies83.compoundinterestcalculator.application;

import android.app.Application;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;

public final class CompoundInterestCalculatorApplication extends Application implements AppContainer {

    private CompoundCalculatorService service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = new CompoundCalculatorService();
    }

    @Override
    public CompoundCalculatorService getCompoundCalculatorService() {
        return service;
    }
}
