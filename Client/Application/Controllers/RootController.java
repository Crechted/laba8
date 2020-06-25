package Client.Application.Controllers;

import Client.Application.Animation;
import Client.Application.OrganizationCircle;
import Client.Application.Main;
import Client.Application.OrganizationProperties;
import Client.ClientClass;
import Code8.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;


public class RootController implements Serializable {
    private static final long serialVersionUID = 35L;
    private static Main main;

    @FXML
    private ComboBox<Locale> comboBox;

    @FXML
    private TextField textFieldFilter;

    public TextField getTextFieldFilter() {
        return textFieldFilter;
    }

    @FXML
    private Label labelTextSpace, labelFilter;

    public void addLabelTextSpace(String string) {

        Platform.runLater(() -> {
            labelTextSpace.setText(labelTextSpace.getText() + "\n" + string);
        });

    }

    @FXML
    private TableView<OrganizationProperties> tableOrganization;

    public void selectRowInTable(OrganizationProperties op) {
        Platform.runLater(() -> {
            tableOrganization.getSelectionModel().select(op);
        });
    }

    public TableView<OrganizationProperties> getTableOrganization() {
        return tableOrganization;
    }

    @FXML
    private CheckBox checkBoxAllTable;

    @FXML
    private Canvas canvas;

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @FXML
    private Pane paneCanvas;

    public Pane getPaneCanvas() {
        return paneCanvas;
    }

    public void setPaneCanvas(Pane PaneCanvas) {
        this.paneCanvas = PaneCanvas;
    }

    public void addCircleInPane(OrganizationCircle oc) {
        Platform.runLater(() -> {
            paneCanvas.getChildren().add(oc);
        });
    }

    private void standardWork(String command){
        ResourceBundle rb =ResourceBundle.getBundle("GuiRoot", Main.getLoc());
        labelTextSpace.setText(rb.getString("sRoot1"));
        labelTextSpace.setText(labelTextSpace.getText() + "\n" + rb.getString("sRoot2"));
        labelTextSpace.setText(labelTextSpace.getText() + "\n" + rb.getString("sRoot3"));
        AtomicReference<Message> msg = new AtomicReference<>();
        new Thread(() -> {
            msg.set(ClientClass.SendAndReceiveAnswerByServer(command));
            if (msg.get() == null || msg.get().getAnswerServer().equals("Server is not responding")) {
                addLabelTextSpace(rb.getString("sRoot4"));
                return;
            } else
                addLabelTextSpace(msg.get().getAnswerServer());
            CollectionOrganization.getCollectionOrganization().getUserCollection().clear();
            CollectionOrganization.getCollectionOrganization().getCollection().clear();
            CollectionOrganization.getCollectionOrganization().setAllCollection(msg.get().getAllCollection());
            CollectionOrganization.getCollectionOrganization().setUserCollection(msg.get().getUserCollection());
            main.updateCollectionOrganizationApp();
            //tableOrganization.setItems(main.getCollectionOrganizationApp());
        }).start();
    }

    @FXML
    private void clickShow(){
       standardWork("show");
    }

    @FXML
    private void clickHelp(){
        standardWork("help");
    }

    @FXML
    private void clickInfo(){
        standardWork("info");
    }

    @FXML
    private void clickAdd() throws IOException {
        Organization organization = new Organization();
        main.showCreateNewOrganizationWindow(organization);
        if(organization == null)
            return;
        Command.Add.getAdd().setOrganization(organization);
        standardWork("add");
    }

    @FXML
    private void clickUpdate() throws IOException {
        OrganizationProperties org = tableOrganization.getSelectionModel().getSelectedItem();
        if (org == null)
            return;
        Command.UpdateElement.getUpdateElement().setId(org.getId());
        Organization organization = new Organization();
        main.showCreateNewOrganizationWindow(organization);
        if(organization == null)
            return;
        Command.UpdateElement.getUpdateElement().setOrg(organization);
        standardWork("update_id");
    }

    @FXML
    private void clickAddIfMin() throws IOException {
        Organization organization = new Organization();
        main.showCreateNewOrganizationWindow(organization);
        if(organization == null)
            return;
        Command.AddIfMin.getAddIfMin().setOrganization(organization);
        standardWork("add_if_min");
    }

    @FXML
    private void clickRemove(){
        OrganizationProperties org = tableOrganization.getSelectionModel().getSelectedItem();
        if (org == null)
            return;
        Command.Remove.getRemove().setId(org.getId());
        new Thread(() -> Animation.startAnimationRemove(org.getOrganizationCircle())).start();
        standardWork("remove_by_id");
    }

    @FXML
    private void clickClear(){
        standardWork("clear");
    }

    @FXML
    private void clickRemoveGreater(){
        OrganizationProperties org = tableOrganization.getSelectionModel().getSelectedItem();
        if (org == null)
            return;
        Command.RemoveGreater.getRemoveGreater().setAnnualTurnover(org.getAnnualTurnover());
        Command.RemoveGreater.getRemoveGreater().setElementId(org.getId());
        standardWork("remove_greater");
    }

    @FXML
    private void clickRemoveLower(){
        OrganizationProperties org = tableOrganization.getSelectionModel().getSelectedItem();
        if (org == null)
            return;
        Command.RemoveLower.getRemoveLower().setAnnualTurnover(org.getAnnualTurnover());
        Command.RemoveLower.getRemoveLower().setElementId(org.getId());
        standardWork("remove_lower");
    }

    @FXML
    private void clickCountGreaterThanType() throws IOException {
        main.showTypeSelectionWindow();
        standardWork("count_greater_than_type");
    }

    @FXML
    private void clickPrintDescending(){
        standardWork("print_descending");
    }

    @FXML
    private void clickPrintFieldAscendingOfficialAddress(){
        standardWork("print_field_ascending_official_address");
    }

    @FXML
    private void clickExecuteScript() throws IOException {
        main.showFilePathWindow();
        standardWork("execute_script");
    }

    @FXML
    private void F(){
        labelTextSpace.setText("");
        new Thread(()->{
            Client.F.setRootController(this);
            Client.F.getF();
        }).start();
    }

    @FXML
    private void clickChangeUser() throws IOException {
        main.showUserWindow();
    }

    @FXML
    private TableColumn<OrganizationProperties, String> organizationNameColumn;

    @FXML
    private TableColumn<OrganizationProperties, Number> employeesCountColumn;

    @FXML
    private TableColumn<OrganizationProperties, Number> annualTurnoverColumn;

    @FXML
    private TableColumn<OrganizationProperties, OrganizationType> typeColumn;

    @FXML
    private TableColumn<OrganizationProperties, String> dateColumn;

    @FXML
    private TableColumn<OrganizationProperties, Address> addressColumn;

    @FXML
    private TableColumn<OrganizationProperties, Number> idColumn;

    @FXML
    private TableColumn<OrganizationProperties, Coordinates> coordinatesColumn;

    @FXML
    private Button buttonShow, buttonHelp, buttonInfo, buttonAdd, buttonUpdate, buttonAddIfMin, buttonRemove, buttonClear, buttonRemoveGreater, buttonRemoveLower,
            buttonCountGreaterThanType, buttonPrintDescending, buttonPrintFieldAscendingOfficialAddress, buttonExecuteScript, buttonChangeUser;



    @FXML
    private void initialize() {
        organizationNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        employeesCountColumn.setCellValueFactory(cellData -> cellData.getValue().employeesCountProperty());
        annualTurnoverColumn.setCellValueFactory(cellData -> cellData.getValue().annualTurnoverProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().officialAddressProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        coordinatesColumn.setCellValueFactory(cellData -> cellData.getValue().coordinatesProperty());

        checkBoxAllTable.setOnAction(event -> {
            if(checkBoxAllTable.isSelected())
                main.setAllCollection(true);
            else
                main.setAllCollection(false);
        });

        tableOrganization.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if(labelTextSpace == null)
                return;
            labelTextSpace.setText(newSel.toString());
        });



        // ****LOCALE****

        ArrayList<Locale> listLocale = new ArrayList<>(4);
        listLocale.add(new Locale("ru", "RU")); //русский, Россия
        listLocale.add(new Locale("cs", "CZ")); //чешский, Чехия
        listLocale.add(new Locale("lt", "LT")); //литовский, Литва
        listLocale.add(new Locale("en", "NZ")); //английский, Новая зеландия
        comboBox.setItems(FXCollections.observableArrayList(listLocale));
        Main.setLoc(new Locale("ru","RU"));
        Locale.setDefault(new Locale("ru", "RU"));

        comboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if(newSel != null)
                Main.setLoc(newSel);
            setL10nRoot(Main.getLoc());
        });

       setL10nRoot(new Locale("ru", "RU"));
    }

    public void setL10nRoot(Locale loc){
        ResourceBundle rbB = ResourceBundle.getBundle("GuiRoot", loc);
        buttonShow.setText(rbB.getString("bShow")); buttonShow.getTooltip().setText(rbB.getString("tShow"));
        buttonHelp.setText(rbB.getString("bHelp")); buttonHelp.getTooltip().setText(rbB.getString("tHelp"));
        buttonInfo.setText(rbB.getString("bInfo")); buttonInfo.getTooltip().setText(rbB.getString("tInfo"));
        buttonAdd.setText(rbB.getString("bAdd")); buttonAdd.getTooltip().setText(rbB.getString("tAdd"));
        buttonUpdate.setText(rbB.getString("bUpdate")); buttonUpdate.getTooltip().setText(rbB.getString("tUpdate"));
        buttonAddIfMin.setText(rbB.getString("bAddIfMin")); buttonAddIfMin.getTooltip().setText(rbB.getString("tAddIfMin"));
        buttonRemove.setText(rbB.getString("bRemove")); buttonRemove.getTooltip().setText(rbB.getString("tRemove"));
        buttonClear.setText(rbB.getString("bClear")); buttonClear.getTooltip().setText(rbB.getString("tClear"));
        buttonRemoveGreater.setText(rbB.getString("bRemoveGreater")); buttonRemoveGreater.getTooltip().setText(rbB.getString("tRemoveGreater"));
        buttonRemoveLower.setText(rbB.getString("bRemoveLower")); buttonRemoveLower.getTooltip().setText(rbB.getString("tRemoveLower"));
        buttonCountGreaterThanType.setText(rbB.getString("bCGTT")); buttonCountGreaterThanType.getTooltip().setText(rbB.getString("tCGTT"));
        buttonPrintDescending.setText(rbB.getString("bPD")); buttonPrintDescending.getTooltip().setText(rbB.getString("tPD"));
        buttonPrintFieldAscendingOfficialAddress.setText(rbB.getString("bPFAOA")); buttonPrintFieldAscendingOfficialAddress.getTooltip().setText(rbB.getString("tPFAOA"));
        buttonExecuteScript.setText(rbB.getString("bExecuteScript")); buttonExecuteScript.getTooltip().setText(rbB.getString("tExecuteScript"));
        checkBoxAllTable.setText(rbB.getString("cShowAllOrganizations")); checkBoxAllTable.getTooltip().setText(rbB.getString("tShowAllOrganizations"));
        comboBox.getTooltip().setText(rbB.getString("tChoiceLanguage"));
        buttonChangeUser.setText(rbB.getString("bChangeUser")); buttonChangeUser.getTooltip().setText(rbB.getString("tChangeUser"));
        labelFilter.setText(rbB.getString("lFilter"));

        organizationNameColumn.setText(rbB.getString("organizationNameColumn"));
        employeesCountColumn.setText(rbB.getString("employeesCountColumn"));
        annualTurnoverColumn.setText(rbB.getString("annualTurnoverColumn"));
        coordinatesColumn.setText(rbB.getString("coordinatesColumn"));
        typeColumn.setText(rbB.getString("typeColumn"));
        dateColumn.setText(rbB.getString("dateColumn"));
        addressColumn.setText(rbB.getString("addressColumn"));
        idColumn.setText(rbB.getString("idColumn"));

        dateColumn.setCellValueFactory(org -> {
            SimpleStringProperty property = new SimpleStringProperty();
            SimpleDateFormat dataFormat = new SimpleDateFormat(rbB.getString("dataFormat"));
            property.setValue(dataFormat.format(org.getValue().getCreationDate()));
            return property;
        });

    }

    public void setMain(Main main) {
        this.main = main;
        tableOrganization.setItems(main.getCollectionOrganizationApp());
    }

    public static Main getMain() {
        return main;
    }
}
