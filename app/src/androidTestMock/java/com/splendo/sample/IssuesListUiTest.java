package com.splendo.sample;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ali on 18/06/2017.
 */
@RunWith(AndroidJUnit4.class)
public class IssuesListUiTest {

    @Rule
    public ActivityTestRule<MainActivity> rule
            = new ActivityTestRule(MainActivity.class, true, false);

    @Test
    public void check_AppContextAndPackageName() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.splendo.sample.mock", appContext.getPackageName());
    }

    @Test
    public void check_IssuesListShown() {
        Intent intent = new Intent();
        rule.launchActivity(intent);

        // 1. check that Issues List is present in the view
        onView(withId(R.id.issues_list)).check(matches(isDisplayed()));
    }

    @Test
    public void clickItem_showIssueDetail() {
        Intent intent = new Intent();
        rule.launchActivity(intent);

        // 1. click on the 1st Issue in the list
        onView(withId(R.id.issues_list))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );

        // 2. check that the detail view layout is present in the hierarchy
        onView(withId(R.id.detail_layout)).check(matches(isDisplayed()));
    }
}
