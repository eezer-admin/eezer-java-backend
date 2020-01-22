package org.eezer.service.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * Get the current date in a well formatted string.
     *
     * Example output: 2018-10-17T14:41:04.626Z
     *
     * @return the date string
     */
    public static String getWellFormattedNowDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return formatter.format(ZonedDateTime.now());
    }

}
