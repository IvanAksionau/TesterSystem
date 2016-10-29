package bean;

/**
 * Created by Ivan on 12.10.2016.
 */
public class UserRegistrationResponse extends Response {
    private boolean registrationStatus;

    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
