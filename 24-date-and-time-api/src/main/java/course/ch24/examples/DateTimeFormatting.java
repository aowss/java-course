package course.ch24.examples;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Demonstrates formatting and parsing with {@link DateTimeFormatter}.
 */
public class DateTimeFormatting {

    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter CUSTOM =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Formats a date using ISO-8601 ({@code yyyy-MM-dd}).
     *
     * @param date the date to format
     * @return the formatted string
     */
    public static String formatIsoDate(LocalDate date) {
        return date.format(ISO_DATE);
    }

    /**
     * Formats a date-time using a custom pattern.
     *
     * @param dateTime the date-time to format
     * @return the formatted string in {@code dd/MM/yyyy HH:mm} format
     */
    public static String formatCustom(LocalDateTime dateTime) {
        return dateTime.format(CUSTOM);
    }

    /**
     * Formats a date using a locale-specific medium style.
     *
     * @param date   the date to format
     * @param locale the locale for formatting
     * @return the localized date string
     */
    public static String formatLocalized(LocalDate date, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(locale);
        return date.format(formatter);
    }

    /**
     * Parses a date-time string using the custom pattern.
     *
     * @param text the text to parse
     * @return the parsed date-time
     */
    public static LocalDateTime parseCustom(String text) {
        return LocalDateTime.parse(text, CUSTOM);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 3, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, java.time.LocalTime.of(9, 0));
        System.out.println("ISO:        " + formatIsoDate(date));
        System.out.println("Custom:     " + formatCustom(dateTime));
        System.out.println("Localized:  " + formatLocalized(date, Locale.US));
        System.out.println("Parsed:     " + parseCustom("15/03/2024 09:00"));
    }
}
