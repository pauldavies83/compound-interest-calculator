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
    public void onCalculateClicked() {
        try {
            view.resultsChanged(service.getFiveYearProjection(100.00, 5.00));
        } catch (CompoundCalculatorService.NegativeNumberArgumentExeption negativeNumberArgumentExeption) {
            negativeNumberArgumentExeption.printStackTrace();
        }
    }
}
