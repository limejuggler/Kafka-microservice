/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oss.utils;

import com.oss.app.GenericApp;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.UIManager;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author Tore
 */
public class Helper {

    public static Color paleGreen = new Color(152, 251, 152);
    public static Color darkSalmon = new Color(233, 150, 122);
    public static Color lightGreen = new Color(144, 238, 144);
    public static Color ultralightRed = new Color(233, 200, 200);
    public static Color ultralightGreen = new Color(200, 233, 200);
    public static Color jpanelColor = UIManager.getColor("Panel.background");

    /**
     * <p>
     * Checks if two dates are on the same day ignoring time.</p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>
     * Checks if two calendars represent the same day ignoring time.</p>
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * <p>
     * Checks if a date is today.</p>
     *
     * @param date the date, not altered, not null.
     * @return true if the date is today.
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    public static ArrayList<? extends Object> gethashMapAsList(ConcurrentHashMap<Integer, Object> map) {
        Set<Map.Entry<Integer, Object>> mapEntrySet = map.entrySet();
        //Set<Integer> keySet = map.keySet();     
        Collection<Object> valueCollection = map.values();
        return new ArrayList(valueCollection);
    }

    public static boolean doubleHasValue(Double doubl) {
        if ((doubl == null) || (doubl == 0)) {
            return false;
        }
        return true;
    }

    // Rounds a double to two dec. 
    public static Double roundDouble(Double in) {
        if (in == null) {
            return null;
        }
        try {
            return Math.round(in * 100) / 100.0d;
        } catch (Exception ex) {
            return in;
        }
    }

    public static int getDayTimeInSecs(Date in) {
        int hours = in.getHours() * 60 * 60;
        int minutes = in.getMinutes() * 60;
        int seconds = in.getSeconds();
        return hours + minutes + seconds;
    }

    public static Date getDateFromString(String in) {
        Date date;
        try {
            date = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).parse(in);
        } catch (ParseException ex) {
            GenericApp.addLogMessage("Unable to parse date from string" + in);
            date = null;
        }
        return date;
    }

    public static Integer getIntegerFromDouble(Double in) {
        if (in == null) {
            return null;
        } else {
            return in.intValue();
        }
    }

    public static Integer getIntegerFromString(String in) {
        if (isStringEmpty(in)) {
            return null;
        }
        Integer result = null;
        try {
            result = Integer.parseInt(in);
        } catch (Exception ex) {
            GenericApp.addLogMessage("Not able to convert " + in + " to a Integer");
        }
        return result;
    }

    public static Double getDoubleFromString(String in) {
        if (isStringEmpty(in)) {
            return null;
        }
        Double result = null;
        try {
            result = Double.parseDouble(in);
        } catch (Exception ex) {
            GenericApp.addLogMessage("Not able to convert " + in + " to a Double");
        }
        return result;
    }

    public static String mapBooleanToString(boolean in) {
        if (in) {
            return "true";
        } else {
            return "false";
        }
    }

    public static boolean isZero(Double in) {
        return ((in == null) || (in == 0) || (in == Double.NaN));
    }

    public static boolean isZero(Integer in) {
        return ((in == null) || (in == 0) || (in == Double.NaN));
    }

    public static boolean lessThan(Double in, double value) {
        return ((in == null) || (in < value));
    }

    public static boolean higherThan(Double in, double value) {
        return ((in == null) || (in > value));
    }

    public static boolean isValidAboveZero(Double in) {
        return ((in != null) && (in > 0));
    }

    public static String formatDateString(Date date) {
        return DateFormat.getDateInstance().format(date);
    }

    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy : HH:mm:ss");

    public static String getDateWithTime(Date date) {
        return dateFormat.format(date);
    }

    public static boolean isStringEmpty(String in) {
        return ((in == null) || (in.equals("")));
    }

    public static String toString(Object in) {
        if (in == null) {
            return null;
        } else {
            return in.toString();
        }
    }

    public static boolean isEmptyString(Object in) {
        if (in == null) {
            return true;
        } else {
            return (in.toString().equals("")) ? true : false;
        }
    }

    public static boolean isEqualWithNull(String in, String toCompareWith) {
        if ((in == null) || (toCompareWith == null)) {
            if ((in == null) & (toCompareWith == null)) {
                return true;
            } else {
                return false;
            }
        } else {
            return (in.toString().equals(toCompareWith)) ? true : false;
        }
    }

    public static boolean hasStringContent(Object in) {
        return !isEmptyString(in);
    }

    public static void out(String in) {
        System.out.println(in);
    }

    public static String getCurrTime() {
        return dateFormat.format(new Date());
    }

    public static boolean isNumeric(String str) {      
        str = str.replace(",", "");
        str = str.replace(".", "");
        str = str.replace("-", "");
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static String isNumericAnswer(String str) {
        if (isNumeric(str)) {
            return "OK "+  str + " is a NUMBER";
        } else {
            return "Error " + str + " is NOT a NUMBER";
        }
    }
}
