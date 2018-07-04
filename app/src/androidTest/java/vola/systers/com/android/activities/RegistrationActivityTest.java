package vola.systers.com.android.activities;

import android.os.SystemClock;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import vola.systers.com.android.R;
import vola.systers.com.android.data.model.Event;
import vola.systers.com.android.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;


public class RegistrationActivityTest {
        @Rule
        public ActivityTestRule<MainActivity> RegistrationActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

        private static Matcher<Object> getEventItemName(final Matcher<String> itemMatcher){
            return new BoundedMatcher<Object, Event>(Event.class) {
                @Override
                public void describeTo(org.hamcrest.Description description) {
                    description.appendText("Event with name: ");
                    itemMatcher.describeTo(description);
                }

                @Override
                protected boolean matchesSafely(Event event) {
                    return itemMatcher.matches(event.getName());
                }
            };
        }

    @Test
    public void testRegistrationActivity(){
        SystemClock.sleep(3000);
        onData(getEventItemName(equalTo
                ("Event Gsoc Testing"))).inAdapterView(withId(R.id.list)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        onView(withId(R.id.radioStudent)).check(matches(isDisplayed()));
    }

    @Test
        public void testCheckRadioButtons()
        {
            SystemClock.sleep(3000);
            onData(getEventItemName(equalTo
                    ("Event Gsoc Testing"))).inAdapterView(withId(R.id.list)).perform(click());
            onView(withId(R.id.btn_register)).perform(click());
            onView(withId(R.id.radioStudent)).check(matches(isDisplayed()));
            onView(withId(R.id.radioVolunteer))
                    .perform(click());

            onView(withId(R.id.radioVolunteer))
                    .check(matches(isChecked()));

            onView(withId(R.id.radioStudent))
                    .check(matches(not(isChecked())));

            onView(withId(R.id.radioOthers))
                    .check(matches(not(isChecked())));
        }

        @Test
        public void testPerformRegisterButtonClick()
        {
            SystemClock.sleep(3000);
            onData(getEventItemName(equalTo
                    ("Event Gsoc Testing"))).inAdapterView(withId(R.id.list)).perform(click());
            onView(withId(R.id.btn_register)).perform(click());
            onView(withId(R.id.radioStudent)).check(matches(isDisplayed()));
            onView(withId(R.id.btn_register)).perform(click());
            onView(withId(R.id.list)).check(matches(isDisplayed()));
        }
    }
