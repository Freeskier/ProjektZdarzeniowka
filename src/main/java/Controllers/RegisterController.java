package Controllers;

import Models.EmployeeModel;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML private JFXTextField user;
    @FXML private JFXTextField password;
    @FXML private JFXTextField mail;
    @FXML private JFXTextField firstName;
    @FXML private JFXTextField lastName;
    @FXML private JFXTextField phone;
    public static String e;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void addMamba(ActionEvent actionEvent) {
        String theUser = user.getText();
        String thePassword = password.getText();
        String theMail = mail.getText();
        String theFirstName = firstName.getText();
        String theLastName = lastName.getText();
        String thePhone = phone.getText();

        Task<Void> createEmployeeTask = new Task<Void>() {
            @Override
            protected Void call() {
                EmployeeModel employee = new EmployeeModel();
                employee.createEmployee(theUser, thePassword, theMail, theFirstName, theLastName, thePhone);
                return null;
            }
        };
        createEmployeeTask.setOnSucceeded(e ->{
            try {
                ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
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
        Thread createEmployeeThread = new Thread(createEmployeeTask);
        createEmployeeThread.start();
    }

    @FXML
    public void cancel(ActionEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
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
