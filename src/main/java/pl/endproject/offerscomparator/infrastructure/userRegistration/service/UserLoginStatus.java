package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

public enum UserLoginStatus {
    SUCCESS(1, "Successfully logged in "),
    EMPTY_INPUT(2, "You must fill every input"),
    NOT_ACTIVE(3, "Your account is not active. Check your email."),
    WRONG_LOGIN(4, "Wrong username/email"),
    WRONG_PASS(5, "Wrong password");

    private final int code;
    private final String description;

    UserLoginStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
