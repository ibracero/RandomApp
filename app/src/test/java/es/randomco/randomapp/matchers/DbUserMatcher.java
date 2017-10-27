package es.randomco.randomapp.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import es.randomco.randomapp.data.datasource.db.models.DbUser;

public class DbUserMatcher {
    public static Matcher<DbUser> isEqualTo(final DbUser dbUser) {
        return new TypeSafeMatcher<DbUser>() {

            @Override
            public void describeTo(final Description description) {
                description.appendText("expected result: ")
                        .appendValue(dbUser.toString());
            }

            @Override
            public boolean matchesSafely(final DbUser testDbRreport) {
                return dbUser.equals(testDbRreport);
            }

            @Override
            public void describeMismatchSafely(final DbUser testDbUser,
                                               final Description mismatchDescription) {
                mismatchDescription.appendText("was ").appendValue(testDbUser.toString());
            }
        };
    }
}