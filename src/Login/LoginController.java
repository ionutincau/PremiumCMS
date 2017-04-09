package Login;

import domain.User;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginController {

    private LoginProvider loginProvider;
    private User user;

    public LoginController() {
        loginProvider = new LoginProvider();
    }

    public void login (String username, String password) throws Exception {
        if (username.equals("") || password.equals("")) {
            throw new Exception("Please fill required fields!");
        }
        User user = loginProvider.getUser(username);
        if (user == null) {
            throw new Exception("This username doesn't exist!");
        }
        else if (!user.getPassword().equals(password)) {
            throw new Exception("Wrong password!");
        }
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
