package es.randomco.randomapp.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import es.randomco.randomapp.data.datasource.network.models.ApiUser;

public class ApiUserMatcher {

    public static Matcher<ApiUser> isEqualTo(final ApiUser user) {
        return new TypeSafeMatcher<ApiUser>() {

            @Override
            public void describeTo(final Description description) {
                description.appendText("expected result: ")
                        .appendValue(user.toString());
            }

            @Override
            public boolean matchesSafely(final ApiUser testUser) {
                return user.equals(testUser);
            }

            @Override
            public void describeMismatchSafely(final ApiUser testRreport,
                                               final Description mismatchDescription) {
                mismatchDescription.appendText("was ").appendValue(testRreport.toString());
            }
        };
    }
}
