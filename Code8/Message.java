package Code8;

import java.io.Serializable;
import java.util.TreeSet;

public class Message implements Serializable {
    private static final long serialVersionUID = 30L;
    private Workable work;
    private TreeSet<Organization> allCollection = null;
    private TreeSet<UserInformation> userCollection = null;
    private String login = null;
    private String password = null;
    private String answerServer = null;

    public Message (Workable work, String login, String password) {
        this.work = work;
        this.login = login;
        this.password = password;
    }

    public Message (Workable work) {
        this.work = work;
    }

    public Workable getWork() {
        return work;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public TreeSet<Organization> getAllCollection() {
        return allCollection;
    }

    public void setAllCollection(TreeSet<Organization> collection) {
        this.allCollection = collection;
    }

    public TreeSet<UserInformation> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(TreeSet<UserInformation> userCollection) {
        this.userCollection = userCollection;
    }
    public String getAnswerServer() {
        return answerServer;
    }

    public void setAnswerServer(String answerServer) {
        this.answerServer = answerServer;
    }
}
