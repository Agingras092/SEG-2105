package com.example.simplecalculator;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityUITest {

    /*
    getActivity();
    startActivitySync();
    */

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Ensures that the positive/negative button
     * is fully functional.
     */
    @Test
    public void testPositiveToNegativeNumber() {

        //Clear previous content
        onView(withId(R.id.buAC)).perform(click());

        //Add an integer and make it negative. Check
        onView(withId(R.id.bu1)).perform(click());
        onView(withId(R.id.buPlusMinus)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("-1")));

        //Add decimal numbers and make positive. Check
        onView(withId(R.id.buDot)).perform(click());
        onView(withId(R.id.bu3)).perform(click());
        onView(withId(R.id.buPlusMinus)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("1.3")));
    }

    /**
     * Ensures that the application doesn't crash when
     * the user presses "=" while the text box
     * is clear.
     */
    @Test
    public void testEqualOnClearedScreen() {
        //Clear previous content.
        onView(withId(R.id.buAC)).perform(click());

        //Check if text is still 0.
        onView(withId(R.id.buEqual)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("0")));
    }

    /**
     * If two decimal numbers are supposed to make an integer,
     * ensure that the output is an integer.
     */
    @Test
    public void testTwoDecimalsMakeInteger() {
        //Clear previous content
        onView(withId(R.id.buAC)).perform(click());

        //Input two decimal values with an addition
        onView(withId(R.id.bu1)).perform(click());
        onView(withId(R.id.buDot)).perform(click());
        onView(withId(R.id.bu3)).perform(click());
        onView(withId(R.id.buPlus)).perform(click());
        onView(withId(R.id.bu2)).perform(click());
        onView(withId(R.id.buDot)).perform(click());
        onView(withId(R.id.bu7)).perform(click());
        onView(withId(R.id.buEqual)).perform(click());

        //Check output
        onView(withId(R.id.editText)).check(matches(withText("4")));
    }


    /**
     * Ensure that the percentage function is operational.
     */
    @Test
    public void testPercentageFunction() {
        //Clear previous content
        onView(withId(R.id.buAC)).perform(click());

        //Add an integer and check percentage
        onView(withId(R.id.bu5)).perform(click());
        onView(withId(R.id.bu2)).perform(click());
        onView(withId(R.id.buPercent)).perform(click());

        //Check output
        onView(withId(R.id.editText)).check(matches(withText("0.52")));
    }
}
