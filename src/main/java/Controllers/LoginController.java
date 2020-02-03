package Controllers;

import Entities.Employee;
import Models.EmployeeModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML public JFXTextField username;
    @FXML public JFXPasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loginButtonAction(ActionEvent actionEvent) {
        String theUser = username.getText();
        String thePassword = password.getText();

        Task<Employee> loadEmployeeTask = new Task<Employee>() {
                @Override
            protected Employee call() {
                EmployeeModel employee = new EmployeeModel();
                return employee.getEmployeeCredentials(theUser, thePassword);
            }
        };
        loadEmployeeTask.setOnSucceeded(e ->{
                try {
                    ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Application.fxml"));
                    Pane root = loader.load();
                    ApplicationController applicationController = loader.getController();
                    applicationController.setLogged(loadEmployeeTask.getValue());

                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXML/MyAccount.fxml"));
                    loader2.load();
                    AccountController accountController = loader2.getController();
                    accountController.setEmployee(loadEmployeeTask.getValue());

                    FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/FXML/NewSell.fxml"));
                    loader3.load();
                    NewSellController newSellController = loader3.getController();
                    newSellController.setEmployee(loadEmployeeTask.getValue());

                    Stage stage = new Stage(StageStyle.TRANSPARENT);
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
        });
        Thread loadEmployeeThread = new Thread(loadEmployeeTask);
        loadEmployeeThread.start();
    }

    @FXML
    public void registerClicked() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Register.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void minClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void closeClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }
}
