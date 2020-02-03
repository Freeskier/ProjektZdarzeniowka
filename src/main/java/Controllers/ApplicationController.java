package Controllers;

import Entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @FXML private MenuButton mbtnUsrInfoBox;
    @FXML private StackPane stackPaneContent;
    @FXML private Label lblUsrNamePopOver;
    @FXML private Label lblFullName;
    @FXML private Label lblRoleAs;
    @FXML private Label lblUsrName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btnHomeOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/FXML/Home.fxml").openStream());
        } catch (IOException e) {

        }
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/HomeCSS.css").toExternalForm());
        stackPaneContent.getChildren().add(root);
    }

    @FXML
    public void btnStoreOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/Stock.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/StoreCSS.css").toExternalForm());
        stackPaneContent.getChildren().add(root);
    }

    @FXML
    public void btnSellOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/Sell.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/SellCSS.css").toExternalForm());
        stackPaneContent.getChildren().add(root);
    }

    @FXML
    public void btnEmployeOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/Employe.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/EmployeCSS.css").toExternalForm());
        stackPaneContent.getChildren().add(root);
    }

    @FXML
    public void btnSettingsOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/Settings.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/viewEmployeStyle.css").toExternalForm());
        stackPaneContent.getChildren().add(root);
    }

    @FXML
    public void btnAboutOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/About.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/viewEmployeStyle.css").toExternalForm());
        stackPaneContent.getChildren().add(root);
    }

    @FXML
    public void minClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void closeClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void logOut() throws IOException {
        Stage stage1 = (Stage) mbtnUsrInfoBox.getScene().getWindow();
        stage1.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void setLogged(Employee employee){
        lblUsrName.setText(employee.getFirstName() +" "+ employee.getLastName());
        lblUsrNamePopOver.setText(employee.getUser());
        lblFullName.setText(employee.getFirstName() +" "+ employee.getLastName());
        lblRoleAs.setText(employee.getMail());
    }

    public StackPane getStackPaneContent() {
        return stackPaneContent;
    }
}
