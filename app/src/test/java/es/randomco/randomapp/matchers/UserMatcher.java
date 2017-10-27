package es.randomco.randomapp.matchers;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import es.randomco.randomapp.domain.entities.User;

public class UserMatcher {

    public static Matcher<User> isEqualTo(final User user) {
        return new TypeSafeMatcher<User>() {

            @Override
            public void describeTo(final Description description) {
                description.appendText("expected result: ")
                        .appendValue(user.toString());
            }

            @Override
            public boolean matchesSafely(final User testUser) {
                return user.equals(testUser);
            }

            @Override
            public void describeMismatchSafely(final User testRreport,
                                               final Description mismatchDescription) {
                mismatchDescription.appendText("was ").appendValue(testRreport.toString());
            }
        };
    }
}