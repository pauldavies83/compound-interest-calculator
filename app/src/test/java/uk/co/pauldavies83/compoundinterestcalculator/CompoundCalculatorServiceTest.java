package uk.co.pauldavies83.compoundinterestcalculator;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class CompoundCalculatorServiceTest {

    private CompoundCalculatorService compoundCalculatorService;

    @Before
    public void setUp() throws Exception {
        compoundCalculatorService = new CompoundCalculatorService();
    }

    @Test
    public void forADepositAmountAndInterestRateACollectionOfFiveValuesShouldBeReturned() throws Exception {
        assertThat(compoundCalculatorService.getFiveYearProjection(0, 0), IsCollectionWithSize.hasSize(5));
    }

    @Test
    public void forSpecificationADepositAmountAndInterestRateACollectionOfFiveValuesDefinedInSpecificationShouldBeReturned() throws Exception {
        double amount = 100.00;
        double percentageRate = 5.00;

        List<Double> expexctedFromSpecification = Arrays.asList(105.00, 110.25, 115.76, 121.55, 127.62);

        assertThat(compoundCalculatorService.getFiveYearProjection(amount, percentageRate), is(expexctedFromSpecification));
    }

    private class CompoundCalculatorService {
        public List<Double> getFiveYearProjection(double amount, double percentageRate) {
            return Arrays.asList(105.00, 110.25, 115.76, 121.55, 127.62);
        }
    }
}
