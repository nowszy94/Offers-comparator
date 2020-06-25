package pl.endproject.offerscomparator.infrastructure.userRegistration.service;

public enum UserLoginStatus {
    NOT_ACTIVE(1, "Your account is not active. Check your email."),
    WRONG_LOGIN(2, "Wrong username/email"),
    WRONG_PASS(3, "Wrong password");

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
