package Controllers;

import Entities.Employee;
import Models.EmployeeModel;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AccountController {
    @FXML public JFXTextField tfUser;
    @FXML public JFXTextField tfName;
    @FXML public JFXTextField tfPhone;
    @FXML public JFXTextField tfMail;
    @FXML public JFXTextField tfLastName;

    static Employee employee;

    @FXML public void updateUser() {
        String user = tfUser.getText();
        String name = tfName.getText();
        String phone = tfPhone.getText();
        String mail = tfMail.getText();
        String lastName = tfLastName.getText();

        Task<Void> updateUserTask = new Task<Void>() {
            @Override
            protected Void call() {
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.updateEmployee(employee, user, name, phone, mail, lastName);
                return null;
            }
        };
        Thread updateUserThread = new Thread(updateUserTask);
        updateUserThread.start();
    }
    @FXML
    public void changePass() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/PassChange.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void setEmployee(Employee empl) {
        employee = empl;
    }
}
