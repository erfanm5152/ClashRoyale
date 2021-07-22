package Model;

/**
 * The Shared data of the game
 * contains main user & userLibrary
 * with singleton design
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class SharedData {
    // singleton field
    private static SharedData sharedDataInstance;
    // main user
    private User user;
    // user library of the game
    private UserLibrary userLibrary;
    // type of bot
    private String nameOfBot;
    /**
     * create new shared data
     */
    private SharedData() {
        this.user = null;
        this.userLibrary = FileUtils.readUsers();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SharedData getInstance() {
        if (sharedDataInstance == null) {
            sharedDataInstance = new SharedData();
        }
        return sharedDataInstance;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user library.
     *
     * @return the user library
     */
    public UserLibrary getUserLibrary() {
        return userLibrary;
    }

    /**
     * Sets user library.
     *
     * @param userLibrary the user library
     */
    public void setUserLibrary(UserLibrary userLibrary) {
        this.userLibrary = userLibrary;
    }

    public String getNameOfBot() {
        return nameOfBot;
    }

    public void setNameOfBot(String nameOfBot) {
        this.nameOfBot = nameOfBot;
    }
}
