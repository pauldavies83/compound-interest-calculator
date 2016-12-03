package uk.co.pauldavies83.compoundinterestcalculator;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.pauldavies83.compoundinterestcalculator.view.MainActivity;

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
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void specification_ui_components_are_visible() throws Exception {
        onView(withId(R.id.deposit_amount)).check(matches(isDisplayed()));
        onView(withId(R.id.interest_rate)).check(matches(isDisplayed()));
        onView(withId(R.id.calculate_button)).check(matches(isDisplayed()));
    }

    @Test
    public void when_entering_provided_example_values_expected_results_are_output() {
        onView(withId(R.id.results_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.deposit_amount)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.interest_rate)).perform(typeText("5"), closeSoftKeyboard());

        onView(withId(R.id.calculate_button)).perform(click());

        onView(withText("After 1 year")).check(matches(isDisplayed()));
        for (int i = 2; i <= 5; i++) {
            onView(withText("After " + i + " years")).check(matches(isDisplayed()));
        }

        onView(withText("£105.00")).check(matches(isDisplayed()));
        onView(withText("£110.25")).check(matches(isDisplayed()));
        onView(withText("£115.76")).check(matches(isDisplayed()));
        onView(withText("£121.55")).check(matches(isDisplayed()));
        onView(withText("£127.62")).check(matches(isDisplayed()));
    }
}

