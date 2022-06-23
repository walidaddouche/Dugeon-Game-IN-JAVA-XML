package views.state;

import java.awt.*;

public interface States {
    void run();
    void runWindow(Window window);
    void paintComponents() ;

    enum STATE{
        MENU,
        GAME,
        CREDITS
    }
}
