package su.yuk1chan.socksorder.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class OrderNumberGenerator {
    public static final String ORDER_NUMBER_PATTERN = "SO%s-%d";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");

    public static String generate(LocalDateTime createdAt, Long id) {
        String dateTimeString = createdAt.format(DATE_TIME_FORMATTER);
        return String.format(ORDER_NUMBER_PATTERN, dateTimeString, id);
    }
}
