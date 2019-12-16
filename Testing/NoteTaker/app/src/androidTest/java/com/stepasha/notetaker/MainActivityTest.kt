package com.stepasha.notetaker


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_add), withContentDescription("add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.et_Title),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("nmote"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.et_Notes),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), replaceText("note"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_Save), withText("savenote"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.et_Title),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText3.perform(scrollTo(), replaceText("hey"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.et_Notes),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatEditText4.perform(scrollTo(), replaceText("you"), closeSoftKeyboard())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_Save), withText("savenote"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.navigation_view), withContentDescription("view"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val linearLayout = onView(
            childAtPosition(
                allOf(
                    withId(R.id.recyclerview),
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    )
                ),
                1
            )
        )
        linearLayout.perform(scrollTo(), click())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.et_View_Notes), withText("note"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("notes"))

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.et_View_Notes), withText("notes"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(closeSoftKeyboard())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_View_Update), withText("updatenote"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatButton3.perform(scrollTo(), click())

        val linearLayout2 = onView(
            childAtPosition(
                allOf(
                    withId(R.id.recyclerview),
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    )
                ),
                0
            )
        )
        linearLayout2.perform(scrollTo(), click())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.btn_View_Delete), withText("deletenote"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatButton4.perform(scrollTo(), click())

        val bottomNavigationItemView3 = onView(
            allOf(
                withId(R.id.navigation_search), withContentDescription("search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView3.perform(click())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.et_Search),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("you"), closeSoftKeyboard())




    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
