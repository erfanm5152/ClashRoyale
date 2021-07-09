package Model;

public class SharedData {
    private static SharedData sharedDataInstance;
    private User player;
    private UserLibrary userLibrary;


    private SharedData() {
        this.player =null;
        this.userLibrary = FileUtils.readUsers();
    }

    public static SharedData getInstance() {
        if (sharedDataInstance == null) {
            sharedDataInstance = new SharedData();
        }
        return sharedDataInstance;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public UserLibrary getUserLibrary() {
        return userLibrary;
    }

    public void setUserLibrary(UserLibrary userLibrary) {
        this.userLibrary = userLibrary;
    }
}
