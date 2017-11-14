package application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author SE-lnwza
 */
public class MyDate {
    
    public static final String[] MONTH = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static Integer[] YEAR = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    
//    private static final Locale LOCALE = new Locale("th", "TH");
    private static final TimeZone TIMEZONE = TimeZone.getTimeZone("Asia/Bangkok");
    private static final String FULLDATE_FORMAT = "dd MMMM yyyy";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    
    private static Calendar cal;
    
    public static void initialize() {
        // set default timezone and locale
        TimeZone.setDefault(TIMEZONE);
//        Locale.setDefault(LOCALE);
        
        // fill year into array using current year
        int currentYear = getCurrentYear();
        for (int year = currentYear - 9, i = 0; year <= currentYear; year++, i++) {
            YEAR[i] = year;
        }
    }
    
    public static String getDate(Date date) {
        SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT);
        return form.format(date);
    }
    
    public static String getCurrentDate() {
        return getDate(new Date());
    }
    
    public static String getFullDate(Date date) {
        SimpleDateFormat form = new SimpleDateFormat(FULLDATE_FORMAT);
        return form.format(date);
    }
    
    public static String getCurrentFullDate() {
        return getFullDate(new Date());
    }
    
    public static String getTime(Date date) {
        SimpleDateFormat form = new SimpleDateFormat(TIME_FORMAT);
        return form.format(date);
    }
    
    public static String getCurrentTime() {
        return getTime(new Date());
    }
    
    public static String getMonthName(int month) {
        return MONTH[month - 1];
    }
    
    public static Calendar getCalendar() {
        return cal;
    }
    
    public static Calendar newCalendar(Date date) {
        Calendar cal = newCalendar();
        cal.setTime(date);
        return cal;
    }
    
    public static Calendar newCalendar() {
        return Calendar.getInstance();
    }
    
    private static void setInstance(Date date) {
        setInstance();
        cal.setTime(date);
    }
    
    private static void setInstance() {
        cal = Calendar.getInstance();
    }
    
    public static int getDay(Date date) {
        setInstance(date);
        return cal.get(Calendar.DATE);
    }
    
    public static int getCurrentDay() {
        setInstance();
        return cal.get(Calendar.DATE);
    }
    
    public static int getMonth(Date date) {
        setInstance(date);
        return cal.get(Calendar.MONTH) + 1;
    }
    
    public static int getCurrentMonth() {
        setInstance();
        return cal.get(Calendar.MONTH) + 1;
    }
    
    public static int getCurrentMonthIndex() {
        return getCurrentMonth() - 1;
    }
    
    public static String getMonthName(Date date) {
        setInstance(date);
        return MONTH[cal.get(Calendar.MONTH)];
    }
    
    public static String getCurrentMonthName() {
        return MONTH[getCurrentMonthIndex()];
    }
    
    public static int getYear(Date date) {
        setInstance(date);
        return cal.get(Calendar.YEAR);
    }
    
    public static int getCurrentYear() {
        setInstance();
        return cal.get(Calendar.YEAR);
    }
    
    public static int getCurrentYearIndex() {
        return 9;
    }
    
}
