package Client.Application;

import Client.Application.Controllers.*;
import Client.ClientClass;
import Code8.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.Color;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;

public class Main extends Application implements Serializable {
    private static final long serialVersionUID = 33L;
    private static Locale loc = new Locale("ru", "RU");
    private Stage st;
    private Parent root;
    private FXMLLoader fxmlLoader;
    private static RootController rootController;
    private Stage primaryStage;
    private ObservableList<OrganizationProperties> collectionOrganizationApp = FXCollections.observableArrayList();
    private boolean isAllCollection;
    private Canvas canvas;
    private Pane pane;
    private TableView<OrganizationProperties> tv;


    public boolean isAllCollection() {
        return isAllCollection;
    }

    public void setAllCollection(boolean allCollection) {
        isAllCollection = allCollection;
    }

    public Main(){
        //collectionOrganizationApp.add(new OrganizationProperties(12L, "Name1", new Coordinates(123, 43), LocalDateTime.now(), 23, 21, OrganizationType.TRUST, new Address("Home, 15")));
        //collectionOrganizationApp.add(new OrganizationProperties(13L, "Name2", new Coordinates(23, 4), LocalDateTime.now(), 2, 1, OrganizationType.PUBLIC, new Address("Home, 19")));
        //collectionOrganizationApp.add(new OrganizationProperties(14L, "Name2", new Coordinates(13, 3), LocalDateTime.now(), 3, 2, OrganizationType.COMMERCIAL, new Address("Home, 21")));
    }

    public ObservableList<OrganizationProperties> getCollectionOrganizationApp() {
        return collectionOrganizationApp;
    }

    public void updateCollectionOrganizationApp() {
        collectionOrganizationApp.removeAll(collectionOrganizationApp);
        TreeSet<Organization> colO = CollectionOrganization.getCollectionOrganization().getCollection();
        TreeSet<UserInformation> colU = CollectionOrganization.getCollectionOrganization().getUserCollection();
        int access = -1, ac = -1;
        for (UserInformation ui: colU) {
            access = ClientClass.getLogin().equals(ui.getLogin()) ? ui.getAccess() : access;
            if(ui.getAccess() != ac){
                ui.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
            }
            ac = ui.getAccess();
        }

        canvas.getGraphicsContext2D().clearRect(0, 0, 1000, 1000);
        Platform.runLater(() ->  pane.getChildren().clear());


        if(!isAllCollection) {
            for (Organization or : colO) {
                if (or.getAccess() == access) {
                    Color color = null;
                    for (UserInformation o : colU) {
                        if (o.getAccess() == access) {
                            color = o.getColor();
                            break;
                        }
                    }
                    OrganizationProperties op = new OrganizationProperties(or.getId(),
                            or.getName(),
                            or.getCoordinates(),
                            or.getCreationDate(),
                            or.getAnnualTurnover(),
                            or.getEmployeesCount(),
                            or.getType(),
                            or.getOfficialAddress(),
                            color, this);
                    collectionOrganizationApp.add(op);
                    op.getOrganizationCircle().draw();
                }
            }
        }
        else {
            for (Organization or : colO) {
                Color color = null;
                for (UserInformation o : colU) {
                    if (o.getAccess() == or.getAccess()) {
                        color = o.getColor();
                        break;
                    }
                }
                OrganizationProperties op = new OrganizationProperties(or.getId(),
                        or.getName(),
                        or.getCoordinates(),
                        or.getCreationDate(),
                        or.getAnnualTurnover(),
                        or.getEmployeesCount(),
                        or.getType(),
                        or.getOfficialAddress(),
                        color, this);
                collectionOrganizationApp.add(op);
                op.getOrganizationCircle().draw();
            }
        }

        initializeTable(rootController.getTableOrganization(), rootController.getTextFieldFilter());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        this.primaryStage = primaryStage;
        primaryStage.setTitle("Lab8");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(750);

        showServerWindow();
        showUserWindow();

        showRootWindow();
    }

    public void showRootWindow() throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("Fxml/RootStage.fxml"));
        root = fxmlLoader.load();
        rootController = fxmlLoader.getController();
        primaryStage.setScene(new Scene(root));
        rootController.setMain(this);
        canvas = rootController.getCanvas();
        pane = rootController.getPaneCanvas();
        tv = rootController.getTableOrganization();
        new Thread(() -> {
            Message msg = ClientClass.SendAndReceiveAnswerByServer("start");
            if (msg != null) {
                CollectionOrganization.getCollectionOrganization().setAllCollection(msg.getAllCollection());
                CollectionOrganization.getCollectionOrganization().setUserCollection(msg.getUserCollection());
                updateCollectionOrganizationApp();
                initializeTable(rootController.getTableOrganization(), rootController.getTextFieldFilter());
            }
        }).start();

        primaryStage.show();
    }

    public void showCreateNewOrganizationWindow(Organization org) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/CreateNewOrganization.fxml"));
        Parent base = loader.load();
        CreateNewOrganizationController createNewOrganizationController = loader.getController();
        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(base));
        dialogStage.setMinWidth(750);
        dialogStage.setMinHeight(700);
        dialogStage.setMaxWidth(750);
        dialogStage.setMaxHeight(700);
        dialogStage.setTitle("CreateNewOrganization");
        createNewOrganizationController.setDialogStage(dialogStage);
        createNewOrganizationController.setOrganization(org);

        dialogStage.showAndWait();
    }

    public void showUserWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/EnterUser.fxml"));
        Parent base = loader.load();
        EnterUserController enterUserController = loader.getController();
        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(base));
        dialogStage.setMinWidth(350);
        dialogStage.setMinHeight(250);
        dialogStage.setMaxWidth(350);
        dialogStage.setMaxHeight(250);
        dialogStage.setTitle("User");
        enterUserController.setDialogStage(dialogStage);

        dialogStage.showAndWait();
    }

    public void showServerWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/EnterServer.fxml"));
        Parent base = loader.load();
        EnterServerController enterServerController = loader.getController();
        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(base));
        dialogStage.setMinWidth(350);
        dialogStage.setMinHeight(250);
        dialogStage.setMaxWidth(350);
        dialogStage.setMaxHeight(250);
        dialogStage.setTitle("Server");
        enterServerController.setDialogStage(dialogStage);

        dialogStage.showAndWait();
    }

    public void showFilePathWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/ExecuteFilePath.fxml"));
        Parent base = loader.load();
        ExecuteFilePathController executeFilePathController = loader.getController();
        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(base));
        dialogStage.setMinWidth(350);
        dialogStage.setMinHeight(250);
        dialogStage.setMaxWidth(350);
        dialogStage.setMaxHeight(250);
        dialogStage.setTitle("ExecuteFilePath");
        executeFilePathController.setDialogStage(dialogStage);

        dialogStage.showAndWait();
    }

    public void showTypeSelectionWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxml/TypeSelection.fxml"));
        Parent base = loader.load();
        TypeSelectionController typeSelectionController = loader.getController();
        Stage dialogStage = new Stage();
        dialogStage.setScene(new Scene(base));
        dialogStage.setMinWidth(350);
        dialogStage.setMinHeight(250);
        dialogStage.setMaxWidth(350);
        dialogStage.setMaxHeight(250);
        dialogStage.setTitle("TypeSelection");
        typeSelectionController.setDialogStage(dialogStage);

        dialogStage.showAndWait();
    }

    public void initializeTable(TableView tableOrganization, TextField textFieldFilter){
        FilteredList<OrganizationProperties> filteredList = new FilteredList<>(tableOrganization.getItems(), p -> true);
        textFieldFilter.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredList.setPredicate(org -> {
                if(newVal == null || newVal.isEmpty())
                    return true;

                String lowerCaseFilter = newVal.toLowerCase();
                if(org.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if(org.getOfficialAddress().toString().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(org.getType().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else
                return false;
            });
        });

        SortedList<OrganizationProperties> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableOrganization.comparatorProperty());
        tableOrganization.setItems(sortedList);
    }

    public static RootController getRootController() {
        return rootController;
    }

    public static void main(String[] args) {
        launch();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public static Locale getLoc() {
        return loc;
    }

    public static void setLoc(Locale loc) {
        Main.loc = loc;
    }
}
