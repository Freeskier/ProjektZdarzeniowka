package Controllers;

import Models.CartModel;
import Models.EmployeeModel;
import Models.OrderModel;
import Models.ProductModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviewController implements Initializable {
    @FXML private Label madeFor, orderValue, date, ID, employe;
    @FXML private TableColumn colProductId, colQuantity, colProductName, colProductCat, colProductBrand, colProductPrice;
    @FXML private TableView tblFaktura;
    private ProductModel productModel;
    private OrderModel orderModel;
    private CartModel cartModel;
    private EmployeeModel employeeModel;
    private int order;

    public PreviewController(int order) {
        this.order = order;
    }

    @FXML
    public void close(ActionEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductCat.setCellValueFactory(new PropertyValueFactory<>("category"));
        colProductBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        cartModel = new CartModel();
        orderModel = new OrderModel();
        productModel = new ProductModel();
        employeeModel = new EmployeeModel();

        madeFor.setText(orderModel.findOrder(Integer.toString(order)).getCustomerName());
        date.setText(orderModel.findOrder(Integer.toString(order)).getDate());
        employe.setText(orderModel.findOrder(Integer.toString(order)).getEmployeeName());
        ID.setText(Integer.toString(order));
        tblFaktura.getItems().addAll(cartModel.findCartByOrderId(Integer.toString(order)));

        int value = 0;
        for(int i = 0; i < cartModel.findCartByOrderId(Integer.toString(order)).size(); i++) {
            value = cartModel.findCartByOrderId(Integer.toString(order)).get(i).getPrice() * cartModel.findCartByOrderId(Integer.toString(order)).get(i).getQuantity();
        }

        orderValue.setText(Integer.toString(value));

    }

}
