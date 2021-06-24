package classes;

public class UserSession {

    private static UserSession instance;
    private static String userName;
    private static String displayName;

    public UserSession(String userName, String displayName) {
        this.userName = userName;
        this.displayName = displayName;
    }

    public static UserSession getInstance(String userName, String displayName) {
        if(instance == null) {
            instance = new UserSession(userName, displayName);
        }
        return instance;
    }

    public static String getUserName() {
        return userName;
    }
    public static String getDisplayName() {
        return displayName;
    }
    public static void setDisplayName(String newDisplayname) {
        displayName = newDisplayname;
    }

    public static void removeInstance() {
        instance = null;
    }

    public void cleanUserSession() {
        userName = null;
        displayName = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                "displayName='" + displayName + '\'' +
                '}';
    }
}