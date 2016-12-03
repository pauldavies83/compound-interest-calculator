package uk.co.pauldavies83.compoundinterestcalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculator;
import uk.co.pauldavies83.compoundinterestcalculator.presenter.InterestCalculatorPresenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public final class InterestCalculatorPresenterTest {

    @Mock
    InterestCalculator.View view;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void whenCalculateButtonIsPressedResultsInViewShouldUpdate() throws Exception {
        InterestCalculatorPresenter presenter = new InterestCalculatorPresenter(view);
        presenter.onCalculateClicked();

        verify(view).resultsChanged(any(List.class));
    }
}
