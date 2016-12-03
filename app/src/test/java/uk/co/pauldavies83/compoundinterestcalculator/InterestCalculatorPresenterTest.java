package uk.co.pauldavies83.compoundinterestcalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.data.CompoundCalculatorService;
import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculator;
import uk.co.pauldavies83.compoundinterestcalculator.presenter.InterestCalculatorPresenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public final class InterestCalculatorPresenterTest {

    @Mock
    InterestCalculator.View view;

    @Mock
    CompoundCalculatorService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void whenCalculateButtonIsPressedResultsInViewShouldUpdate() throws Exception {
        InterestCalculatorPresenter presenter = new InterestCalculatorPresenter(view, service);
        presenter.onCalculateClicked();

        verify(view).resultsChanged(any(List.class));
    }

    @Test
    public void whenCalculateButtonIsPressedPresenterShouldRequestValuesFromServiceAndProvideToView() throws Exception {
        List<Double> expectedResults = Arrays.asList(1.00, 2.00, 3.00, 4.00, 5.00);
        when(service.getFiveYearProjection(any(Double.class), any(Double.class))).thenReturn(expectedResults);

        InterestCalculatorPresenter presenter = new InterestCalculatorPresenter(view, service);
        presenter.onCalculateClicked();

        verify(view).resultsChanged(expectedResults);
    }
}
