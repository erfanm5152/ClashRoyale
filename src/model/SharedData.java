package Model;

public class SharedData {
    private static SharedData sharedDataInstance;
    private User user;
    private UserLibrary userLibrary;


    private SharedData() {
        this.user =null;
        this.userLibrary = FileUtils.readUsers();
    }

    public static SharedData getInstance() {
        if (sharedDataInstance == null) {
            sharedDataInstance = new SharedData();
        }
        return sharedDataInstance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserLibrary getUserLibrary() {
        return userLibrary;
    }

    public void setUserLibrary(UserLibrary userLibrary) {
        this.userLibrary = userLibrary;
    }
}
