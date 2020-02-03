package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class EmployeeController {
    @FXML private StackPane spEmployeContent;
    @FXML public BorderPane bpContent;
    @FXML private Label employeLabel;

    @FXML
    public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
        employeLabel.setText("Lista pracowników");

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/ViewEmploye.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spEmployeContent.getStylesheets().add(getClass().getResource("/FXML/viewEmployeStyle.css").toExternalForm());
        spEmployeContent.getChildren().clear();
        spEmployeContent.getChildren().add(acPane);
    }

    @FXML
    private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
        employeLabel.setText("Dodaj pracownika");

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/AddEmploye.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spEmployeContent.getChildren().clear();
        spEmployeContent.getChildren().add(acPane);

    }
}
