package Client.Application.Controllers;

import Code8.Command;
import Code8.OrganizationType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TypeSelectionController {
    private Stage dialogStage;

    @FXML
    private ComboBox<OrganizationType> comboBoxType;

    @FXML
    private void clickOk(){
        if(comboBoxType.getValue() != null){
            Command.CountGreaterThanType.getCountGreaterThanType().setType(comboBoxType.getValue().NAME);
            dialogStage.close();
        }
    }

    @FXML
    private void clickCancel(){
        dialogStage.close();
    }

    @FXML
    private void initialize(){
        ArrayList<OrganizationType> list = new ArrayList<>(3);
        list.add(OrganizationType.COMMERCIAL);
        list.add(OrganizationType.PUBLIC);
        list.add(OrganizationType.TRUST);
        comboBoxType.setItems(FXCollections.observableArrayList(list));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
