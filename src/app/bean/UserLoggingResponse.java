package app.bean;

/**
 * Created by Ivan on 12.10.2016.
 */
public class UserLoggingResponse extends Response {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
