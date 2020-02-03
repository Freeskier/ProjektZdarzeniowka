package Controllers;

import Models.CustomerModel;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewCustomerController implements Initializable {
    @FXML public TableView tblCustomer;
    @FXML private TableColumn tblClmName, tblClmDate, tblClmContNo, tblClmAddBy, tblClmTotalBuy;

    private CustomerModel customerModel = new CustomerModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColNames();
        loadCustomer();
    }

    @FXML
    public void addCustomer() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/AddCustomer.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void loadCustomer() {
        tblCustomer.getItems().clear();

        setColNames();

        Task<Void> fillTableWithCustomersTask = new Task<Void>() {
            @Override
            protected Void call() {
                tblCustomer.getItems().addAll(customerModel.getCustomers());
                return null;
            }
        };
        Thread fillTableWithCustomersThread = new Thread(fillTableWithCustomersTask);
        fillTableWithCustomersThread.start();
    }

    public void deleteCustomer() {
        Task<Void> dropCustomerFromTableTask = new Task<Void>() {
            @Override
            protected Void call() {
                customerModel.removeCustomers(customerModel.getCustomers().get(tblCustomer.getSelectionModel().getSelectedIndex()));
                loadCustomer();
                return null;
            }
        };
        Thread dropCustomerFromTableThread = new Thread(dropCustomerFromTableTask);
        dropCustomerFromTableThread.start();


    }

    public void setColNames(){
        tblClmName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblClmDate.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblClmContNo.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tblClmTotalBuy.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tblClmAddBy.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}