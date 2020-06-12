package pl.endproject.offerscomparator.infrastructure.userRegistration.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextValidator {
    private Pattern loginPattern;
    private Pattern emailPattern;
    private Matcher matcher;

    private static final String loginRegex = "^[a-z0-9_.-]{3,15}$";
    private static final String emailRegex = "^[a-z0-9_@.-]{3,250}$";

    public TextValidator() {
        loginPattern = Pattern.compile(loginRegex);
        emailPattern = Pattern.compile(emailRegex);
    }

    /**
     * Validate text with regular expression
     *
     * @param plainText username for validation
     * @return true valid username, false invalid username
     */
    public boolean loginValidate(final String plainText) {

        matcher = loginPattern.matcher(plainText);
        return matcher.matches();
    }

    public boolean emailValidate(final String plainText) {
        if (plainText.contains("@") & plainText.contains(".")) {
            matcher = emailPattern.matcher(plainText);
            return matcher.matches();
        }
        return false;
    }
}

