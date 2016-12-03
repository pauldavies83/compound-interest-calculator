package uk.co.pauldavies83.compoundinterestcalculator;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public final class CompoundCalculatorServiceTest {

    private CompoundCalculatorService compoundCalculatorService;

    @Before
    public void setUp() throws Exception {
        compoundCalculatorService = new CompoundCalculatorService();
    }

    @Test
    public void forADepositAmountAndInterestRateACollectionOfFiveValuesShouldBeReturned() throws Exception {
        double amount = 100.00;
        double percentageRate = 5.00;

        assertThat(compoundCalculatorService.getFiveYearProjection(amount, percentageRate), IsCollectionWithSize.hasSize(5));
    }

    private class CompoundCalculatorService {
        public List<String> getFiveYearProjection(double amount, double percentageRate) {
            return Arrays.asList("1", "2", "3", "4", "5");
        }
    }
}
