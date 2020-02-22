package me.uquark.barrymore;

import me.uquark.barrymore.avatar.Barrymore;
import me.uquark.barrymore.avatar.IAvatar;
import me.uquark.barrymore.internal.DatabaseProvider;
import me.uquark.barrymore.internal.User;
import me.uquark.barrymore.ui.ConsoleUserInterface;

public class Application {
    public static void halt() {
        DatabaseProvider.stopServer();
        Logger.info("Shutting down.");
    }

    public static void main(String[] args) {
        try {
            DatabaseProvider.startServer();
            Logger.info("Database server started.");
            IAvatar barrymore = new Barrymore(new ConsoleUserInterface(User.getUserById(User.ROOT_UID)));
            Logger.info("Barrymore initialized.");
            barrymore.run();
            Logger.info("Barrymore ran.");
        } catch (Exception e) {
            DatabaseProvider.stopServer();
            e.printStackTrace();
            Logger.error("Fatal error. Shutting down.");
        }
    }
}
