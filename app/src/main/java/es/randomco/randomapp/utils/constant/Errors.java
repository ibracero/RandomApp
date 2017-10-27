package es.randomco.randomapp.utils.constant;

public class Errors {

    public static final String UNKOWN = "UNKOWN";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String INVALID_ACTION = "INVALID_ACTION";
    public static final String INVALID_TTL = "INVALID_TTL";

    private Errors() {
    }

    public static String fromCode(int code) {

        switch (code) {
            case 1:
                return INVALID_ACTION;
            default:
                return UNKOWN;
        }
    }
}
