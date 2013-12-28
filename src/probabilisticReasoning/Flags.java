/**
 * Created by talz on 28/12/13.
 */
public class Flags {
    public static final int REPORTED_NOT = -1;
    public static final int REPORTED_KNOWN = 0;
    public static final int REPORTED_KNOWN_NOT = 1;
    public static final int REPORTED_UNKNOWN = 2;

    public static String StatusString(int status) {
        if (status == REPORTED_NOT) return "not reported";
        else if (status == REPORTED_KNOWN) return "reported to be known";
        else if (status == REPORTED_KNOWN_NOT) return "reported to be known not";
        else return "reported unknown";
    }
}
