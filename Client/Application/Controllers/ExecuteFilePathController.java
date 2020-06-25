package Client.Application.Controllers;

import Code8.Command;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExecuteFilePathController {
    private Stage dialogStage;

    @FXML
    private TextField textFieldPath;

    @FXML
    private void clickOk() {
        if(textFieldPath.getText() != null || !textFieldPath.getText().equals("")) {
            Command.ExecuteScript.getExecuteScript().setFilePath(textFieldPath.getText());
            dialogStage.close();
        }
    }

    @FXML
    private void clickCancel() {
        Command.ExecuteScript.getExecuteScript().setFilePath("");
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
