package uk.co.pauldavies83.compoundinterestcalculator;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.pauldavies83.compoundinterestcalculator.view.InterestCalculatorActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InitialSpecificationTest {

    @Rule
    public ActivityTestRule<InterestCalculatorActivity> mActivityRule = new ActivityTestRule<>(
            InterestCalculatorActivity.class);

    @Test
    public void specification_ui_components_are_visible() throws Exception {
        onView(withId(R.id.deposit_amount)).check(matches(isDisplayed()));
        onView(withId(R.id.interest_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.calculate_button)).check(matches(isDisplayed()));
    }

    @Test
    public void when_entering_provided_example_values_expected_results_are_output() {
        executeInputAndCalculationSteps("100", "5");

        onView(withText("£105.00")).check(matches(isDisplayed()));
        onView(withText("£110.25")).check(matches(isDisplayed()));
        onView(withText("£115.76")).check(matches(isDisplayed()));
        onView(withText("£121.55")).check(matches(isDisplayed()));
        onView(withText("£127.63")).check(matches(isDisplayed()));
    }

    @Test
    public void when_entering_new_values_expeted_results_are_output() {
        executeInputAndCalculationSteps("200", "2");

        onView(withText("£204.00")).check(matches(isDisplayed()));
        onView(withText("£208.08")).check(matches(isDisplayed()));
        onView(withText("£212.24")).check(matches(isDisplayed()));
        onView(withText("£216.49")).check(matches(isDisplayed()));
        onView(withText("£220.82")).check(matches(isDisplayed()));
    }

    private void executeInputAndCalculationSteps(String deposit, String interest) {
        onView(withId(R.id.results_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.deposit_amount)).perform(typeText(deposit), closeSoftKeyboard());
        onView(withId(R.id.interest_rate)).perform(typeText(interest), closeSoftKeyboard());

        onView(withId(R.id.calculate_button)).perform(click());

        onView(withText("After 1 year")).check(matches(isDisplayed()));
        for (int i = 2; i <= 5; i++) {
            onView(withText("After " + i + " years")).check(matches(isDisplayed()));
        }
    }
}

