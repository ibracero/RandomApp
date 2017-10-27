package es.randomco.randomapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.util.Random;

import retrofit2.http.PUT;

public class Utils {

    public static final double MIN_LATITUDE = 39.884444;
    public static final double MAX_LATITUDE = 41.164722;
    public static final double MIN_LONGITUDE = -4.579167;
    public static final double MAX_LONGITUDE = -3.053056;

    public static boolean isOnline(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return false;
        }
        if (info.isRoaming()) {
            return true;
        }
        return true;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static double generateRandomLatitudeInMadrid() {
        //The northernmost point in Comunidad de Madrid     41.164722
        //The southernmost point in Comunidad de Madrid 	39.884444

        Random r = new Random();
        double randomValue = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * r.nextDouble();

        return randomValue;
    }

    public static double generateRandomLongitudeInMadrid() {
        //The westernmost point in Comunidad de Madrid  -4.579167
        //The easternmost point in Comunidad de Madrid 	-3.053056

        Random r = new Random();
        double randomValue = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * r.nextDouble();

        return randomValue;
    }

    public static double distanceBetweenPoints(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c);

        return dist/1000;//km
    }

}
