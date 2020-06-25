package Client;

import Client.Application.Controllers.RootController;
import javafx.scene.control.Label;

/**
 * this class starts working if you PRESS "F"
 */
public class F {
    private static RootController rootController;

    public static void setRootController(RootController rootController) {
        F.rootController = rootController;
    }

    public static void getF(){
        try {
            rootController.addLabelTextSpace(" _______________________");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                                             |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                                             |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                   _____________|");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |________");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                                   |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                                   |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                   ________|");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|                  |");
            Thread.sleep(500);
            rootController.addLabelTextSpace("|_________|");
        } catch (InterruptedException e) {
        }
    }
}
