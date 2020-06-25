package Client.Application.Controllers;

import Client.Application.Main;
import Client.ClientClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class EnterUserController {
    private Stage dialogStage;
    @FXML
    private Button buttonEnterUser;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Label labelLogin, labelPassword;

    @FXML
    private void clickButton() {
        if (login.getText() != null && password.getText() != null) {
            ClientClass.setLogin(login.getText());
            ClientClass.setPassword(password.getText());
            dialogStage.close();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize(){

        ResourceBundle rb = ResourceBundle.getBundle("GuiUser", Main.getLoc());

        labelLogin.setText(rb.getString("labelLogin"));
        labelPassword.setText(rb.getString("labelPassword"));
    }
}
