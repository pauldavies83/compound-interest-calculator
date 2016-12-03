package uk.co.pauldavies83.compoundinterestcalculator.presenter;

import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.data.LocalCompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculator;

public final class InterestCalculatorPresenter implements InterestCalculator.Interactions, CompoundCalculatorService.AsyncResult {

    private InterestCalculator.View view;
    private CompoundCalculatorService service;

    public InterestCalculatorPresenter(InterestCalculator.View view, CompoundCalculatorService service) {
        this.view = view;
        this.service = service;
        view.initView();
    }

    @Override
    public void onCalculateClicked(String deposit, String interestRate) {
        try {
            service.getFiveYearProjection(Double.parseDouble(deposit), Double.parseDouble(interestRate), this);
        } catch (LocalCompoundCalculatorService.NegativeNumberArgumentExeption negativeNumberArgumentExeption) {
            negativeNumberArgumentExeption.printStackTrace();
        }
    }

    @Override
    public void onFiveYearProjectionResultFinished(List<Double> fiveYearProjection) {
        view.resultsChanged(fiveYearProjection);
    }
}
