package Code8;

import java.awt.*;
import java.io.Serializable;

public class UserInformation implements Serializable, Comparable<UserInformation> {
    private static final long serialVersionUID = 31L;
    private int access;
    private String login;
    private Color color;

    public UserInformation(int access, String login) {
        this.access = access;
        this.login = login;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int compareTo(UserInformation o) {
        return new String(this.getLogin()).compareTo(o.getLogin());
    }

    @Override
    public String toString() {
        return "Login: " + login;
    }
}
