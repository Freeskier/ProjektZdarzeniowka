package Controllers;

import Models.EmployeeModel;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewEmployeController implements Initializable {
    @FXML private TableView employeTable;
    @FXML private TableColumn employeColumn;
    @FXML private JFXTextField nameTextfield, userTextfield, emailTextfield, phoneTextfield, lastnameTextfield;

    private EmployeeModel employeeModel = new EmployeeModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmploye();;
    }

    @FXML
    private void loadEmploye() {
        employeTable.getItems().clear();
        Task<Void> fillTableWithEmployeesTask = new Task<Void>() {
            @Override
            protected Void call() {
                employeTable.getItems().addAll(employeeModel.getEmployeList());

                employeColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
                return null;
            }
        };
        Thread fillTableWithEmployeesTread = new Thread(fillTableWithEmployeesTask);
        fillTableWithEmployeesTread.start();
    }

    @FXML
    private void tableClicked() {
        if(employeTable != null) {
            Task<Void> fillTableForUpdateTask = new Task<Void>() {
                @Override
                protected Void call() {
                    nameTextfield.setText(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()).getFirstName());
                    userTextfield.setText(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()).getUser());
                    emailTextfield.setText(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()).getMail());
                    phoneTextfield.setText(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()).getPhoneNumber());
                    lastnameTextfield.setText(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()).getLastName());
                    return null;
                }
            };
            Thread fillTableForUpdateThread = new Thread(fillTableForUpdateTask);
            fillTableForUpdateThread.start();
        }
    }

    @FXML
    private void saveEmploye() {
        String user = userTextfield.getText();
        String mail = emailTextfield.getText();
        String firstName = nameTextfield.getText();
        String lastName = lastnameTextfield.getText();
        String thePhone = phoneTextfield.getText();

        Task<Void> updateEmployeeTask = new Task<Void>() {
            @Override
            protected Void call() {
                employeeModel.updateEmployee(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()),user, firstName,thePhone,mail,lastName);
                return null;
            }
        };
        Thread updateEmployeeThread = new Thread(updateEmployeeTask);
        updateEmployeeThread.start();
    }

    @FXML
    private void deleteEmploye() {
        Task<Void> deleteEmployeeFromTableTask = new Task<Void>() {
            @Override
            protected Void call() {
                employeeModel.removeEmploye(employeeModel.getEmployeList().get(employeTable.getSelectionModel().getSelectedIndex()));
                loadEmploye();
                return null;
            }
        };
        Thread deleteEmployeeFromTableThread = new Thread(deleteEmployeeFromTableTask);
        deleteEmployeeFromTableThread.start();



        nameTextfield.setText(" ");
        userTextfield.setText(" ");
        emailTextfield.setText(" ");
        phoneTextfield.setText(" ");
        lastnameTextfield.setText(" ");
    }
}
