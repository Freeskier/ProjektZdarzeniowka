package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;


public class EmployeeController {

    @FXML
    private MenuItem btnViewEmployee;
    @FXML
    private MenuItem btnAddEmployee;

    private String userId;

    @FXML
    private StackPane spEmployeContent;
    @FXML
    public BorderPane bpContent;
    @FXML
    private Label lblView;
    @FXML
    private ImageView ivEmployeIcon;



    @FXML
    public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
        lblView.setText("Employee");

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/ViewEmploye.fxml").openStream());



        AnchorPane acPane = fXMLLoader.getRoot();

        spEmployeContent.getChildren().clear();

        spEmployeContent.getChildren().add(acPane);
    }

    @FXML
    private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
        lblView.setText("Add Employee");


        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/AddEmploye.fxml").openStream());


        AnchorPane acPane = fXMLLoader.getRoot();

        spEmployeContent.getChildren().clear();

        spEmployeContent.getChildren().add(acPane);

    }
}
