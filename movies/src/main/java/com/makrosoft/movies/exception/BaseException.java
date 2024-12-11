package com.makrosoft.movies.exception;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public class BaseException extends RuntimeException {

    /**
     * The exceptions resource bundle.
     */
    private static final ResourceBundle messages =
            ResourceBundle.getBundle("exceptions", LocaleContextHolder.getLocale());

    /**
     * Constructs a BaseException with the specified detail key message.
     *
     * @param key the detailed key message
     */
    BaseException(String key) {
        super(getMessage(key));
    }

    /**
     * Gets a string for the given key from the exceptions resource bundle.
     *
     * @param key the key for the desired string
     * @return the string value for the given key
     */
    private static String getMessage(String key) {
        return messages.getString(key);
    }

    /**
     * Formats the message and fills the missing part using the argument.
     *
     * @param message the message to format
     * @param arg     the argument
     * @return the formatted and filled message
     */
    private static String formatMessage(String message, String arg) {
        return message.replace("{}", arg);
    }

}
