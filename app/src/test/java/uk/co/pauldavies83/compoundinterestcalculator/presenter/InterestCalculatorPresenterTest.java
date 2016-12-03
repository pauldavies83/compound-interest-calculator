package uk.co.pauldavies83.compoundinterestcalculator.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public final class InterestCalculatorPresenterTest {

    static final String ANY_VALID_STRING = "0";

    @Mock
    InterestCalculator.View view;

    private List<Double> expectedResult = Collections.emptyList();
    private CompoundCalculatorService stubService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        stubService = new CompoundCalculatorService() {
            @Override
            public void getFiveYearProjection(double deposit, double percentageRate, AsyncResult callback) throws NegativeNumberArgumentExeption {
                callback.onFiveYearProjectionResultFinished(expectedResult);
            }
        };
    }

    @Test
    public void whenCalculateButtonIsPressedResultsInViewShouldUpdate() throws Exception {
        InterestCalculatorPresenter presenter = new InterestCalculatorPresenter(view, stubService);
        presenter.onCalculateClicked(ANY_VALID_STRING, ANY_VALID_STRING);

        verify(view).resultsChanged(any(List.class));
    }

    @Test
    public void whenCalculateButtonIsPressedPresenterShouldUseUserInputValuesAndRequestValuesFromServiceAndProvideResultsToView() throws Exception {
        expectedResult = Arrays.asList(1.00, 2.00, 3.00, 4.00, 5.00);

        InterestCalculatorPresenter presenter = new InterestCalculatorPresenter(view, stubService);
        presenter.onCalculateClicked(ANY_VALID_STRING, ANY_VALID_STRING);

        verify(view).resultsChanged(expectedResult);
    }
}
