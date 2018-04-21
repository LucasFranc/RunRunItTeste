package br.com.franco.lucas.teste.testerunrunit;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.franco.lucas.teste.testerunrunit.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TasksInstrumentedTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickBtnStartWork() throws Exception {
        Thread.sleep(5000);
        onView(withId(R.id.recycler_tasks)).perform(
                actionOnItemAtPosition(0,click()));
        Thread.sleep(5000);
        onView(withId(R.id.btn_start_work)).perform(click());
        Thread.sleep(5000);
        if(!viewWithTextExist(R.string.working) && !viewWithTextExist(R.string.start_work)){
            throw new Exception();
        }
    }

    public boolean viewWithTextExist(int text){
        try {
            onView(withText(text)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }


}
