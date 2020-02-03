package Controllers;

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

public class AddEmployeeController {
    @FXML private JFXTextField tfUserName;
    @FXML private JFXTextField tfPassword;
    @FXML private JFXTextField tfEmail;
    @FXML private JFXTextField tfFirstName;
    @FXML private JFXTextField tfLastName;
    @FXML private JFXTextField tfPhoneNumber;
    public static String e;

    @FXML
    private void addEmployee() throws IOException {
        String user = tfUserName.getText();
        String password = tfPassword.getText();
        String mail = tfEmail.getText();
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String thePhone = tfPhoneNumber.getText();

        Task<Void> addEmployeeTask = new Task<Void>() {
            @Override
            protected Void call() {
                EmployeeModel employee = new EmployeeModel();
                employee.createEmployee(user, password, mail, firstName, lastName, thePhone);
                return null;
            }
        };
        addEmployeeTask.setOnSucceeded(e ->{
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Sucess.fxml"));
                Stage stage = new Stage(StageStyle.TRANSPARENT);
                Scene scene = new Scene(parent);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Thread addEmployeeThread = new Thread(addEmployeeTask);
        addEmployeeThread.start();
    }
}
