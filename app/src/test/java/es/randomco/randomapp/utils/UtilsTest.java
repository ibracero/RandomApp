package es.randomco.randomapp.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UtilsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeBetweenMaxLatitudeAndMinLatitude() {
        double lat = Utils.generateRandomLatitudeInMadrid();
        assertThat(lat > Utils.MIN_LATITUDE, is(true));
        assertThat(lat < Utils.MAX_LATITUDE, is(true));
    }

    @Test
    public void shouldBeBetweenMaxLongitudeAndMinLongitude() {
        double lon = Utils.generateRandomLongitudeInMadrid();
        assertThat(lon > Utils.MIN_LONGITUDE, is(true));
        assertThat(lon < Utils.MAX_LONGITUDE, is(true));
    }

}