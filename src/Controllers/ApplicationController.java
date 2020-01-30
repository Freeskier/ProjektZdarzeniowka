package Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class ApplicationController {



    @FXML
    private MenuButton mbtnUsrInfoBox;

    @FXML
    private StackPane stackPaneContent;

    @FXML
    private AnchorPane acMain;

    private StackPane spEmployeContent;

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
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/glyphStyle.css").toExternalForm());
        stackPaneContent.getChildren().add(root);


    }


    @FXML
    public void btnStoreOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/Stock.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/MainStyle.css").toExternalForm());
        stackPaneContent.getChildren().add(root);

    }

    @FXML
    public void btnSellOnClick() {
        System.out.println("HOME");
    }

    @FXML
    public void btnEmployeOnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.load(getClass().getResource("/FXML/Employe.fxml").openStream());
        AnchorPane root = fxmlLoader.getRoot();
        stackPaneContent.getChildren().clear();
        stackPaneContent.getStylesheets().clear();
        stackPaneContent.getStylesheets().add(getClass().getResource("/FXML/viewEmployeStyle.css").toExternalForm());
        stackPaneContent.getChildren().add(root);

    }

    @FXML
    public void btnSettingsOnClick() {
        System.out.println("HOME");
    }

    @FXML
    public void btnAboutOnClick() {
        System.out.println("HOME");
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
        //((Stage)((Node)e.getSource()).getParent().getScene().getWindow()).close();
        //((Stage)((Node)e.getSource()).getScene().getWindow()).close();

        Stage stage1 = (Stage) mbtnUsrInfoBox.getScene().getWindow();
        stage1.close();

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }

}
