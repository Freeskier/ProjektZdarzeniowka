package Controllers;

import Entities.Cart;
import Entities.Customer;
import Entities.Employee;
import Entities.Product;
import Models.CartModel;
import Models.CustomerModel;
import Models.OrderModel;
import Models.ProductModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class NewSellController implements Initializable {
    @FXML private JFXTextField nameProduct, quantity;
    @FXML private JFXComboBox pickerCustomer;
    @FXML private Label emptyBase, categoryLabel, costLabel, brandLabel, customerLabel, orderID, available, available1, totalPrice;
    @FXML private TableView basketTable;
    @FXML private TableColumn idColumn, categoryColumn, brandColumn, nameColumn, priceColumn, quantityColumn;

    private static Employee employee;
    private ProductModel productModel;
    private OrderModel orderModel;
    private CartModel cartModel;
    private int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderID();
        getCustomerList();
        cartModel = new CartModel();
        orderModel = new OrderModel();
        productModel = new ProductModel();
        System.out.println(orderID.getText());
    }

    @FXML
    public void pickerAction() {
        customerLabel.setText(pickerCustomer.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    public void search() {
        Task<Integer> searchForProductsTask = new Task<Integer>() {
            @Override
            protected Integer call() {
                Integer size = productModel.findProduct(nameProduct.getText()).size();
                return size;
            }
        };
        searchForProductsTask.setOnSucceeded(e ->{
            try {
                if(searchForProductsTask.getValue() == 0){
                    emptyBase.setVisible(true);
                    nameProduct.setFocusColor(Color.RED);
                    nameProduct.setUnFocusColor(Color.RED);
                    available.setVisible(false);
                    available1.setVisible(false);
                }
                else{
                    emptyBase.setVisible(false);
                    nameProduct.setFocusColor(Color.GREEN);
                    nameProduct.setUnFocusColor(Color.GREEN);
                    categoryLabel.setText(productModel.findProduct(nameProduct.getText()).get(0).getCategory());
                    costLabel.setText(Integer.toString(productModel.findProduct(nameProduct.getText()).get(0).getPrice()));
                    brandLabel.setText(productModel.findProduct(nameProduct.getText()).get(0).getBrand());
                    available1.setVisible(true);
                    available.setVisible(true);
                    available.setTextFill(Color.GREEN);
                    available1.setTextFill(Color.GREEN);
                    available.setText(Integer.toString(productModel.findProduct(nameProduct.getText()).get(0).getQuantity()));
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Thread searchForProductsThread = new Thread(searchForProductsTask);
        searchForProductsThread.start();
    }

    @FXML
    public void addBasket(){
        search();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //basketTable.getItems().addAll(productModel.findProduct(nameProduct.getText()).get(0));
        //productModel.removeProduct(productModel.findProduct(nameProduct.getText()).get(0));
        setOrderID();
        int currentOrder = Integer.parseInt(orderID.getText());
        String productName = nameProduct.getText();
        String productCategory = categoryLabel.getText();
        String productBrand = brandLabel.getText();
        int productPrice = Integer.parseInt(costLabel.getText());
        int productQuantity = productModel.findProduct(nameProduct.getText()).get(0).getQuantity() -  Integer.parseInt(quantity.getText());
        productModel.setQuantity(productModel.findProduct(nameProduct.getText()).get(0), productQuantity);

        cartModel.createCart(currentOrder, productName, productCategory, productBrand, productPrice, Integer.parseInt(quantity.getText()));


            basketTable.getItems().clear();
            int total = 0;
            for(int i = 0; i < cartModel.getCartList().size(); i++) {
                if(cartModel.getCartList().get(i).getOrder_id() == currentOrder) {
                    basketTable.getItems().addAll(cartModel.getCartList().get(i));
                    total += cartModel.getCartList().get(i).getPrice() * cartModel.getCartList().get(i).getQuantity();
                }
            }
            totalPrice.setText(Integer.toString(total));


    }

    @FXML
    public void backProduct() {
        if(productModel.findProduct(((Cart)basketTable.getSelectionModel().getSelectedItem()).getName()).size() > 0)
            productModel.setQuantity(productModel.findProduct(((Cart)basketTable.getSelectionModel().getSelectedItem()).getName()).get(0), productModel.findProduct(((Cart)basketTable.getSelectionModel().getSelectedItem()).getName()).get(0).getQuantity() + cartModel.findCartByName(nameProduct.getText()).get(0).getQuantity());
        else
            productModel.createProduct(((Cart)basketTable.getSelectionModel().getSelectedItem()).getName(), ((Cart)basketTable.getSelectionModel().getSelectedItem()).getCategory(), ((Cart)basketTable.getSelectionModel().getSelectedItem()).getBrand(),((Cart)basketTable.getSelectionModel().getSelectedItem()).getPrice(),((Cart)basketTable.getSelectionModel().getSelectedItem()).getQuantity());

        cartModel.removeCart(cartModel.findCartByName(nameProduct.getText()).get(0));
        basketTable.getItems().remove(basketTable.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void addOrder(ActionEvent e) {
        String customerName = customerLabel.getText();
        String employeeName = employee.getUser();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        Task<Void> createOrderTask = new Task<Void>() {
            @Override
            protected Void call() {
                orderModel.createOrder(customerName, employeeName, strDate);
                return null;
            }
        };
        Thread createOrderThread = new Thread(createOrderTask);
        createOrderThread.start();
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void closeClickedd(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

    public void setOrderID(){
        Task<Void> getHighestOrderIdTask = new Task<Void>() {
            @Override
            protected Void call() {
                orderID.setText(String.valueOf(orderModel.getHighestOrderValue()));
                return null;
            }
        };
        Thread getHighestOrderIdThread = new Thread(getHighestOrderIdTask);
        getHighestOrderIdThread.start();
    }

    public void getCustomerList(){
        Task<ObservableList> getCustomerListTask = new Task<ObservableList>() {
            @Override
            protected ObservableList call() {
                CustomerModel customerModel = new CustomerModel();
                ObservableList<Customer> list = FXCollections.observableArrayList(customerModel.getCustomerName());
                return list;
            }
        };
        getCustomerListTask.setOnSucceeded(e ->{
            try {
                pickerCustomer.setItems(getCustomerListTask.getValue());
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Thread getCustomerListThread = new Thread(getCustomerListTask);
        getCustomerListThread.start();
    }

    public void setEmployee(Employee empl) {
        employee = empl;
    }
}
