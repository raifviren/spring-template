package com.example.demo.commons.utils;

import com.example.demo.commons.constants.DatePatterns;
import com.example.demo.commons.exceptions.AppInvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;


/**
 * @author Virender Bhargav
 * @version 1.0.0
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static LocalDate convertStrToLocalDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, formatter);
    }
    
    public static String convertStrToLocalDate(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * Converts yyyy-MM-dd to {@link DatePatterns#EMAIL_DATE_FORMAT}.
     * NOTE: Do not make changes in this method as it's being used in velocity templates.
     *
     * @param dateStr
     * @return
     */
    public static String convertToEmailFormat(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePatterns.EMAIL_DATE_FORMAT);
        return LocalDate.parse(dateStr).format(formatter);
    }

    /**
     * Converts yyyy-MM-dd to {@link DatePatterns#REPORT_ENTRY_DATE_FORMAT}.
     * NOTE: Do not make changes in this method as it's being used in velocity templates.
     *
     * @param date
     * @return
     */
    public static String convertToReportEntryFormat(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePatterns.REPORT_ENTRY_DATE_FORMAT);
        return date.format(formatter);
    }

    public static LocalDateTime convertStrToLocalDateTime(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static String convertLocalDateToString(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }


    public static String convertTimeStampToString(Timestamp ts, String pattern) {
    	return convertLocalDateTimeToString(ts.toLocalDateTime(), pattern);
    }

    public static LocalDateTime getFromDateInLocalTimeFormat(String dateStr, String datePattern) {
        try {
            return DateUtil.convertStrToLocalDate(dateStr, datePattern).atStartOfDay();
        }
        catch (Exception e) {
            LOGGER.error("Date parsing Exception in from date",e);
            throw new AppInvalidInputException("Invalid Date passed in from date");
        }

    }

    public static LocalDateTime getTillDateInLocalTimeFormat(String dateStr, String datePattern) {
        try {
            return DateUtil.convertStrToLocalDate(dateStr, datePattern).atTime(23, 59, 59);
        }
        catch (Exception e) {
            LOGGER.error("Date parsing Exception in to date ",e);
            throw new AppInvalidInputException("Invalid Date passed in to date");
        }
    }

    public static LocalDateTime convertStrToLocalDateTimeDefault(String dateStr, String pattern) {

        DateTimeFormatter formatter =
                new DateTimeFormatterBuilder().appendPattern(pattern).parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter();
        return LocalDateTime.parse(dateStr, formatter);
    }


    public static LocalDateTime convertLocalDateToLocalDateTimeDefault(LocalDate localDate) {
        return convertLocalDateToLocalDateTime(localDate, LocalTime.of(0, 0, 0));
    }

    public static LocalDateTime convertLocalDateToLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return LocalDateTime.of(localDate, localTime);
    }

    public static Calendar localDateTimeToCalendar(LocalDateTime localDateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, localDateTime.getDayOfMonth(),
                     localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        return calendar;
    }

    public static String convertLocalDateTimeStrToLocalDateStr(String localDateTimeStr, String datePatterns){
        LocalDate creditNoteLocalDate =
                DateUtil.convertStrToLocalDateTime(localDateTimeStr, datePatterns).toLocalDate();

        return DateUtil.convertLocalDateToString(creditNoteLocalDate, DatePatterns.DATE_FORMAT_YYYYMMDD);
    }

    /**
     * Deprecating on 23-07-20 -> @see {@link #getDiffInDateTime(LocalDateTime, LocalDateTime, ChronoUnit)} 
     * 
     * Get difference between two LocalDateTime in years
     *
     * @param start
     * @param end
     * @return
     */
    @Deprecated
    public static int getDiffYears(LocalDateTime start, LocalDateTime end) {
        Calendar a = localDateTimeToCalendar(start);
        Calendar b = localDateTimeToCalendar(end);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    /**
     * get difference between two LocalDateTime in years
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public static long getDiffInDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                         ChronoUnit chronoUnit) {
        return startDateTime.until(endDateTime, chronoUnit);
    }
    
    public static long getDiffInDate(LocalDate startDate, LocalDate endDate, ChronoUnit chronoUnit) {

        return startDate.until(endDate, chronoUnit);

    }

    /**
     * convert Date instance to that of Calendar
     *
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

//    /**
//     * check if difference between 2 dates in less than n financial years
//     *
//     * @param start
//     * @param end
//     * @return
//     */
//    public static boolean isDiffLessThanNFinancialYear(LocalDateTime start, LocalDateTime end ,long n) {
//        long diffInYears = getDiffInDateTime(start, end, ChronoUnit.YEARS);
//        if (diffInYears > n) {
//            return false;
//        }
//        else if (diffInYears == n) {
//            return end.getMonth().getValue() <= Month.MARCH.getValue() && start.getMonth().getValue() >= Month.APRIL.getValue();
//        }
//        else if (diffInYears == n-1) {
//            return (end.getMonth().getValue() <= Month.MARCH.getValue() && start.getMonth().getValue() <= Month.MARCH.getValue()) || (end.getMonth().getValue() >= Month.APRIL.getValue() && start.getMonth().getValue() >= Month.APRIL.getValue());
//        }
//        return true;
//    }
//
//    public static boolean validateStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
//        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
//            return true;
//        }
//
//        if (startDate.isAfter(endDate)) {
//            LOGGER.error("Start Date : {} can not be grater than End Date : {}", startDate, endDate);
//            return false;
//        }
//        return true;
//    }
//
//	public static boolean isValidDate(String dateStr, String pattern) {
//		if(StringUtils.isBlank(dateStr))
//			return false;
//		try {
//			DateUtil.convertStrToLocalDate(dateStr, pattern);
//		} catch (DateTimeException e) {
//			return false;
//		}
//		return true;
//	}


}
