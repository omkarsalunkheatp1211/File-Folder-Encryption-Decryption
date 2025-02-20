
public class PasswordLockout {

    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCKOUT_TIME = 15 * 60 * 1000;
    private int failedAttempts = 0;
    private long lastFailedAttempt = 0;

    public boolean checkPassword(String enteredPassword, String correctPassword) {
        if (failedAttempts >= MAX_ATTEMPTS && System.currentTimeMillis() - lastFailedAttempt < LOCKOUT_TIME) {
            System.out.println("Locked out.");
            return false;
        }

        if (enteredPassword.equals(correctPassword)) {
            failedAttempts = 0;
            return true;
        } else {
            failedAttempts++;
            lastFailedAttempt = System.currentTimeMillis();
            return false;
        }
    }
}
