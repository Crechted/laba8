package Client.Application.Controllers;

import Client.Application.Main;
import Code8.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateNewOrganizationController {
    private Stage dialogStage;
    private Organization organization;
    private ResourceBundle rb;

    @FXML
    private TextField textFieldName, textFieldAnnualTurnover, textFieldEmployeesCount, textFieldX, textFieldY, textFieldAddress;

    @FXML
    private Label labelErrorName, labelErrorX, labelErrorY, labelErrorAnnualTurnover, labelErrorEmployeesCount, labelErrorType, labelErrorAddress,
    labelName, labelX, labelY, labelAnnualTurnover, labelEmployeesCount, labelType, labelAddress;

    @FXML
    private Slider sliderX, sliderY, sliderAnnualTurnover, sliderEmployeesCount;

    @FXML
    private ComboBox<OrganizationType> comboBoxType;

    @FXML
    private Button buttonCreate, buttonCancel;


    @FXML
    private void clickCreate() {
        labelErrorAddress.setText("");
        labelErrorType.setText("");
        labelErrorEmployeesCount.setText("");
        labelErrorAnnualTurnover.setText("");
        labelErrorX.setText("");
        labelErrorY.setText("");
        labelErrorName.setText("");

        // NAME
        String name = null; //Поле не может быть null, Строка не может быть пустой
        if(textFieldName.getText() != null && !textFieldName.getText().equals("")){
            name = textFieldName.getText();
        } else {
            labelErrorName.setText(rb.getString("leName1"));
        }

        // COORDINATES
        Coordinates coordinates = null; //Поле не может быть null
        Long x = -905L;
        int y = 552;

        try {
            x = Long.parseLong(textFieldX.getText());
            if (x<-903)
                labelErrorX.setText(rb.getString("leX1"));
        } catch (Exception e) {
            labelErrorX.setText(rb.getString("leX2"));
        }


        try {
            y = Integer.parseInt(textFieldY.getText());
            if (y>552)
                labelErrorY.setText(rb.getString("leY1"));
        } catch (Exception e) {
            labelErrorY.setText(rb.getString("leY2"));
        }


        if (x>-904 && y < 552)
            coordinates = new Coordinates(x, y);

        // ANNUAL TURNOVER
        float annualTurnover = -1; //Значение поля должно быть больше 0
        try {
            annualTurnover = Float.parseFloat(textFieldAnnualTurnover.getText());
            if (annualTurnover<=0)
                labelErrorAnnualTurnover.setText(rb.getString("leAnnualTurnover1"));
        } catch (Exception e) {
            labelErrorAnnualTurnover.setText(rb.getString("leAnnualTurnover2"));
        }



        //EMPLOYEES COUNT
        Integer employeesCount = -1; //Поле не может быть null, Значение поля должно быть больше 0
        try {
            employeesCount = Integer.parseInt(textFieldEmployeesCount.getText());
            if (annualTurnover<=0)
                labelErrorEmployeesCount.setText(rb.getString("leEmployeesCount1"));
        } catch (Exception e) {
            labelErrorEmployeesCount.setText(rb.getString("leEmployeesCount2"));
        }


        //ORGANIZATION TYPE
        OrganizationType type; //Поле может быть null
        type = comboBoxType.getValue();
        if(type == null)
            labelErrorType.setText(rb.getString("leType1"));

        //ADDRESS
        Address officialAddress = null; //Поле может быть null
        String address = textFieldAddress.getText();
        if (address == null || address.equals("")) {
            labelErrorAddress.setText(rb.getString("leAddress1"));
        } else if (address.length() > 15) {
            labelErrorAddress.setText(rb.getString("leAddress2"));
        } else officialAddress = new Address(address);

        //FINISHED
        if(name != null && coordinates != null && annualTurnover > 0  && employeesCount > 0 && type != null && officialAddress != null) {
            organization.setName(name);
            organization.setCoordinates(coordinates);
            organization.setAnnualTurnover(annualTurnover);
            organization.setEmployeesCount(employeesCount);
            organization.setType(type);
            organization.setOfficialAddress(officialAddress);
            organization.setCreationDate(LocalDateTime.now());
            dialogStage.close();
        }
    }

    @FXML
    private void clickCancel(){
        dialogStage.close();
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @FXML
    private void initialize() {
        sliderX.valueProperty().addListener((observableValue, number, t1) ->
                textFieldX.setText(""+((long)((t1.floatValue()/100)*Long.MAX_VALUE)-903))
        );
        sliderY.valueProperty().addListener((observableValue, number, t1) ->
                textFieldY.setText(""+((int)((t1.floatValue()/100)*Integer.MAX_VALUE-Integer.MAX_VALUE)+551))
        );
        sliderAnnualTurnover.valueProperty().addListener((observableValue, number, t1) ->
                textFieldAnnualTurnover.setText(""+((t1.floatValue()/100)*Float.MAX_VALUE))
        );
        sliderEmployeesCount.valueProperty().addListener((observableValue, number, t1) ->
                textFieldEmployeesCount.setText(""+(int)((t1.floatValue()/100)*Integer.MAX_VALUE))
        );

        ArrayList<OrganizationType> list = new ArrayList<>(3);
        list.add(OrganizationType.COMMERCIAL);
        list.add(OrganizationType.PUBLIC);
        list.add(OrganizationType.TRUST);
        comboBoxType.setItems(FXCollections.observableArrayList(list));

        // LOCALE
        rb = ResourceBundle.getBundle("GuiCreateNewOrganization", Main.getLoc());

        labelName.setText(rb.getString("labelName"));
        labelY.setText(rb.getString("labelX"));
        labelX.setText(rb.getString("labelY"));
        labelAnnualTurnover.setText(rb.getString("labelAnnualTurnover"));
        labelEmployeesCount.setText(rb.getString("labelEmployeesCount"));
        labelType.setText(rb.getString("labelType"));
        labelAddress.setText(rb.getString("labelAddress"));
        buttonCreate.setText(rb.getString("buttonCreate"));
        buttonCancel.setText(rb.getString("buttonCancel"));
    }
}
