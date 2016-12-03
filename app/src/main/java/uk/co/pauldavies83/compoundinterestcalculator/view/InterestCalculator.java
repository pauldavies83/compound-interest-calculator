package uk.co.pauldavies83.compoundinterestcalculator.view;

import java.util.List;

public interface InterestCalculator {

    interface View {
        void initView();
        void resultsChanged(List<Double> results);
    }

    interface Interactions {
        void onCalculateClicked(String s, String toString);
    }
}
