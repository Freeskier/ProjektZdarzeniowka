package Controllers;

import Models.CustomerModel;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCustomerController {
    @FXML private JFXTextField tfFirstName;
    @FXML private JFXTextField tfLastName;
    @FXML private JFXTextField tfPhone;
    @FXML private JFXTextField tfMail;
    @FXML private JFXTextField tfAddress;
    public static String e;

    @FXML
    public void saveCustomer(ActionEvent actionEvent) {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String phone = tfPhone.getText();
        String mail = tfMail.getText();
        String address = tfAddress.getText();

        Task<Void> createCustomerTask = new Task<Void>() {
            @Override
            protected Void call() {
                CustomerModel customer = new CustomerModel();
                customer.createCustomer(firstName, lastName, phone, mail, address);
                return null;
            }
        };
        createCustomerTask.setOnSucceeded(e ->{
            try {
                ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            });
        Thread createCustomerThread = new Thread(createCustomerTask);
        createCustomerThread.start();
    }

    @FXML
    public void closeClickedd(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }
}
