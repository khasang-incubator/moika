package io.khasang.moika.util;

/**
 * Общеупотребимые регулярные выражения
 *
 * @author Rostislav Dublin
 * @since 2017-03-05
 */
public class DataValidationPatterns {
    /**
     * Проверка на корректность введённого Email
     */
    public static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PHONE_NUMBER_PATTERN = "^\\d{10}$";
}
