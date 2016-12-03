package uk.co.pauldavies83.compoundinterestcalculator.presenter;

import java.util.Arrays;

import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculator;

public final class InterestCalculatorPresenter implements InterestCalculator.Interactions {

    private InterestCalculator.View view;

    public InterestCalculatorPresenter(InterestCalculator.View view) {
        this.view = view;
        view.initView();
    }

    @Override
    public void onCalculateClicked() {
        view.resultsChanged(Arrays.asList("£105.00", "£110.25", "£115.76", "£121.55", "£127.62"));
    }
}
