package uk.co.pauldavies83.compoundinterestcalculator.data;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.co.pauldavies83.compoundinterestcalculator.concurrency.ThreadScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public final class LocalCompoundCalculatorServiceTest {

    private LocalCompoundCalculatorService compoundCalculatorService;
    private ThreadScheduler threadScheduler;
    private ExecutorService executorService;

    @Mock
    private CompoundCalculatorService.AsyncResult callback;

    @Captor
    private ArgumentCaptor<List<Double>> captor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        executorService = Executors.newSingleThreadExecutor();
        threadScheduler = new TestThreadScheduler();
        compoundCalculatorService = new LocalCompoundCalculatorService(threadScheduler);
    }

    @Test
    public void forADepositAmountAndInterestRateACollectionOfFiveValuesShouldBeReturned() throws Exception {
        compoundCalculatorService.getFiveYearProjection(0, 0, callback);

        Thread.sleep(10);

        verify(callback).onFiveYearProjectionResultFinished(captor.capture());
        assertThat(captor.getValue(), IsCollectionWithSize.hasSize(5));
    }

    @Test
    public void forSpecificationADepositAmountAndInterestRateACollectionOfFiveValuesDefinedInSpecificationShouldBeReturned() throws Exception {
        double deposit = 100.00;
        double percentageRate = 5.00;

        List<Double> expexctedFromSpecification = Arrays.asList(105.00, 110.25, 115.76, 121.55, 127.63);

        performFiveYearProjectionAssertions(deposit, percentageRate, expexctedFromSpecification);
    }

    @Test
    public void forADepositAmountAndInterestRateACollectionOfFiveCorrectProjectionValuesShouldBeReturned() throws Exception {
        double deposit = 200.00;
        double percentageRate = 2.00;

        List<Double> expected = Arrays.asList(204.00, 208.08, 212.24, 216.49, 220.82);

        performFiveYearProjectionAssertions(deposit, percentageRate, expected);
    }

    @Test
    public void forAnotherDepositAmountAndInterestRateACollectionOfFiveCorrectProjectionValuesShouldBeReturned() throws Exception {
        double deposit = 100000.24;
        double percentageRate = 9.99;

        List<Double> expected = Arrays.asList(109990.26, 120978.29, 133064.02, 146357.12, 160978.19);

        performFiveYearProjectionAssertions(deposit, percentageRate, expected);
    }

    @Test(expected = LocalCompoundCalculatorService.NegativeNumberArgumentExeption.class)
    public void providingANegativeDepositIsInvalid() throws Exception {
        double deposit = -1.00;
        double percentageRate = 1.00;

        compoundCalculatorService.getFiveYearProjection(deposit, percentageRate, null);
    }

    @Test(expected = LocalCompoundCalculatorService.NegativeNumberArgumentExeption.class)
    public void providingANegativeInterstRateIsInvalid() throws Exception {
        double deposit = 1.00;
        double percentageRate = -1.00;

        compoundCalculatorService.getFiveYearProjection(deposit, percentageRate, null);
    }

    private void performFiveYearProjectionAssertions(double deposit, double percentageRate, List<Double> expected) throws CompoundCalculatorService.NegativeNumberArgumentExeption, InterruptedException {
        compoundCalculatorService.getFiveYearProjection(deposit, percentageRate, callback);

        Thread.sleep(10);

        verify(callback).onFiveYearProjectionResultFinished(captor.capture());
        assertThat(captor.getValue(), is(expected));
    }


    private class TestThreadScheduler implements ThreadScheduler {
        @Override
        public void executeOnBackgroundThread(Runnable runnable) {
            executorService.execute(runnable);
        }

        @Override
        public void executeOnUiThread(Runnable runnable) {
            executorService.execute(runnable);
        }
    }
}
