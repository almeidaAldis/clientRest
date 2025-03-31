package com.aldis.clientRest.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class UtilHelper {

    public static boolean isValidAge(Date birthDate, int userAge) {
        if (Objects.isNull(birthDate) || userAge < 0) {
            return false;
        }
        LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int calculatedAge = Period.between(birthLocalDate, LocalDate.now()).getYears();
        return calculatedAge == userAge;
    }

    public static Date calculateDateTeacherRetirement(Date birthDate) {
        if (birthDate == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        calendar.add(Calendar.YEAR, 57);

        return calendar.getTime();
    }
}
