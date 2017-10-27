package es.randomco.randomapp.data.datasource.db.models.mapper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import es.randomco.randomapp.DataHelper;
import es.randomco.randomapp.data.datasource.db.models.DbUser;
import es.randomco.randomapp.domain.entities.User;

import static es.randomco.randomapp.matchers.UserMatcher.isEqualTo;
import static es.randomco.randomapp.matchers.DbUserMatcher.isEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DbUserMapperTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldTransformDbUserToUser() throws Exception {
        DbUserMapper dbUserMapper = getDbUserMapper();

        DbUser dbUser = DataHelper.getDbUser("1");
        User user = DataHelper.getUser("1");

        User mappedUser = dbUserMapper.map(dbUser);

        assertThat(mappedUser, isEqualTo(user));
    }

    @Test
    public void shouldTransformUserToDbUser() throws Exception {
        DbUserMapper dbUserMapper = getDbUserMapper();

        User user = DataHelper.getUser("1");
        DbUser dbUser = DataHelper.getDbUser("1");

        DbUser mappedUser = dbUserMapper.inverseMap(user);

        assertThat(mappedUser, isEqualTo(dbUser));
    }

    private DbUserMapper getDbUserMapper() {
        return new DbUserMapper();
    }
}