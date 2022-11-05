package Utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class TimeDateUtilities {
    //convert the start hour from est to users time
    public static int userTimeZoneOffset = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()).getTotalSeconds() / 3600;

}
