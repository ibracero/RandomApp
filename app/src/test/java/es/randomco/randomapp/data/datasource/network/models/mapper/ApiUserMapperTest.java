package es.randomco.randomapp.data.datasource.network.models.mapper;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import es.randomco.randomapp.DataHelper;
import es.randomco.randomapp.data.datasource.network.models.ApiUser;
import es.randomco.randomapp.domain.entities.User;

import static es.randomco.randomapp.matchers.UserMatcher.isEqualTo;
import static es.randomco.randomapp.matchers.ApiUserMatcher.isEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiUserMapperTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldTransformApiUserToUser() throws Exception {
        ApiUserMapper apiUserMapper = getApiUserMapper();

        ApiUser apiUser = DataHelper.getApiUser("1");
        User user = DataHelper.getUser("1");

        User mappedUser = apiUserMapper.map(apiUser);

        assertThat(mappedUser, isEqualTo(user));
    }

    @Test
    public void shouldTransformUserToDbUser() throws Exception {
        ApiUserMapper apiUserMapper = getApiUserMapper();

        ApiUser apiUser = DataHelper.getApiUser("1");
        User user = DataHelper.getUser("1");

        ApiUser mappedUser = apiUserMapper.inverseMap(user);

        assertThat(mappedUser, isEqualTo(apiUser));
    }

    private ApiUserMapper getApiUserMapper() {
        return new ApiUserMapper();
    }
}