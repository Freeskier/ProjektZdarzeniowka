package Controllers;

import Entities.Order;
import Models.OrderModel;
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

public class ViewSellController implements Initializable {
    @FXML private TableView tblSellView;
    @FXML private TableColumn tblClmSellId;
    @FXML private TableColumn tblClmEmployeeName;
    @FXML private TableColumn tblClmCustomerName;
    @FXML private TableColumn tblClmSoldDate;

    private OrderModel orderModel = new OrderModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshOrderTable();
    }

    @FXML
    public void addSell() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/NewSell.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Preview.fxml"));
        PreviewController previewController = new PreviewController(((Order)tblSellView.getSelectionModel().getSelectedItem()).getId());
        loader.setController(previewController);
        Parent parent = loader.load();
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void updateTable() {
        refreshOrderTable();
    }

    public void refreshOrderTable(){

        Task<Void> fillTableWithOrdersTask = new Task<Void>() {
            @Override
            protected Void call() {
                tblSellView.getItems().clear();

                tblClmSellId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tblClmEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
                tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
                tblClmSoldDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                tblSellView.getItems().addAll(orderModel.getOrders());
                return null;
            }
        };
        Thread fillTableWithOrdersThread = new Thread(fillTableWithOrdersTask);
        fillTableWithOrdersThread.start();
    }

    @FXML
    public void deleteSell() {
        Task<Void> deleteProductFromTableTask = new Task<Void>() {
            @Override
            protected Void call() throws IOException {
                orderModel.removeOrder(orderModel.findOrder(Integer.toString(((Order)tblSellView.getSelectionModel().getSelectedItem()).getId())));
                return null;
            }
        };
        Thread deleteProductFromTableThread = new Thread(deleteProductFromTableTask);
        deleteProductFromTableThread.start();
    }
}
