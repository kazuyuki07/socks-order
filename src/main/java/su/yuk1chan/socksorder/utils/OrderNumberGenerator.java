package su.yuk1chan.socksorder.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

@UtilityClass
public class OrderNumberGenerator {
    private static final Random random = new Random();
    public static final String ORDER_NUMBER_PATTERN = "SO%s-%d";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");

    public static String generate(LocalDateTime createdAt) {
        String dateTimeString = createdAt.format(DATE_TIME_FORMATTER);
        int hash = Objects.hash(createdAt, random.nextLong());
        int hash1 = Objects.hash(createdAt, random.nextLong());
        int hash2 = Objects.hash(createdAt, random.nextLong());
        int hash3 = Objects.hash(createdAt, random.nextLong());

        return String.format(ORDER_NUMBER_PATTERN, dateTimeString, Math.abs(hash + hash1 + hash2 + hash3));
    }
}
