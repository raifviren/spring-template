package com.example.demo.commons.utils.validation;

import com.example.demo.commons.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Slf4j
public class DateValidationUtil {
	
	/**
   * check if difference between 2 dates in less than n financial years
   *
   * @param start
   * @param end
   * @return
   */
  public static boolean isDiffLessThanNFinancialYear(LocalDateTime start, LocalDateTime end ,long n) {
      long diffInYears = DateUtil.getDiffInDateTime(start, end, ChronoUnit.YEARS);
      if (diffInYears > n) {
          return false;
      }
      else if (diffInYears == n) {
          return end.getMonth().getValue() <= Month.MARCH.getValue() && start.getMonth().getValue() >= Month.APRIL.getValue();
      }
      else if (diffInYears == n-1) {
          return (end.getMonth().getValue() <= Month.MARCH.getValue() && start.getMonth().getValue() <= Month.MARCH.getValue()) || (end.getMonth().getValue() >= Month.APRIL.getValue() && start.getMonth().getValue() >= Month.APRIL.getValue());
      }
      return true;
  }

  public static boolean validateStartDateAndEndDate(@Nullable LocalDateTime startDate, @Nullable LocalDateTime endDate) {
      if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
          return true;
      }

      if (startDate.isAfter(endDate)) {
          log.error("Start Date : {} can not be grater than End Date : {}", startDate, endDate);
          return false;
      }
      return true;
  }

	public static boolean isValidDate(String dateStr, String pattern) {
		if(StringUtils.isBlank(dateStr))
			return false;
		try {
			DateUtil.convertStrToLocalDate(dateStr, pattern);
		} catch (DateTimeException e) {
			return false;
		}
		return true;
	}

	public static boolean validateStartDateTimeAndEndDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		if (Objects.isNull(startDateTime) || Objects.isNull(endDateTime)) {
	          return true;
	    }
		
		if (!(LocalTime.from(startDateTime).equals(LocalTime.of(0, 0, 0)) 
        		&& LocalTime.from(endDateTime).equals(LocalTime.of(23, 59, 59)))) {
			log.error("start date time has to be 00:00:00 and end date time has to be 23:59:59");
			return false;
		}
		return true;
	}
}