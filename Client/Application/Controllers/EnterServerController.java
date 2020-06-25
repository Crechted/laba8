package Client.Application.Controllers;

import Client.ClientClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnterServerController {
    private Stage dialogStage;
    @FXML
    private Button buttonEnterServer;

    @FXML
    private TextField port;

    @FXML
    private TextField host;

    @FXML
    private void clickButton() {
        try {
            if (host.getText() != null && port.getText() != null) {
                ClientClass.setHost(host.getText());
                ClientClass.setPort(Integer.parseInt(port.getText()));
                dialogStage.close();
            }
        } catch (Exception e) {
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }
}
