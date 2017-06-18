package com.splendo.sample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ali on 18/06/2017.
 */

public class DateParser {

    public Date parseDate(String part) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return format.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
