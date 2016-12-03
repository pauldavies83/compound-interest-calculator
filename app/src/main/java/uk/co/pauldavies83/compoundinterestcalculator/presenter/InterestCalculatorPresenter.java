package uk.co.pauldavies83.compoundinterestcalculator.presenter;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculator;

public final class InterestCalculatorPresenter implements InterestCalculator.Interactions {

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
            view.resultsChanged(service.getFiveYearProjection(Double.parseDouble(deposit), Double.parseDouble(interestRate)));
        } catch (CompoundCalculatorService.NegativeNumberArgumentExeption negativeNumberArgumentExeption) {
            negativeNumberArgumentExeption.printStackTrace();
        }
    }
}
