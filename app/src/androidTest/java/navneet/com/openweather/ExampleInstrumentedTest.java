package navneet.com.openweather;

import android.app.Dialog;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import navneet.com.openweather.weather.WeatherActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("navneet.com.openweather", appContext.getPackageName());
    }
    @Rule
    public ActivityTestRule<WeatherActivity> mNotesActivityTestRule =
            new ActivityTestRule<>(WeatherActivity.class);

    @Test
    public void clickSearch_opensSearchUi() throws Exception {

        onView(withId(R.id.edittext)).perform(click());

        onView(withId(R.id.et_city)).check(matches(isDisplayed()));
    }

    @Test
    public void AddSearchText() throws Exception {

        onView(withId(R.id.edittext)).perform(click());

        onView(withId(R.id.et_city)).check(matches(isDisplayed())).perform(typeText("Kochi"), closeSoftKeyboard());


    }

}
