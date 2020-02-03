package Controllers;

import Models.ProductModel;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
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

public class StoreController implements Initializable {
    @FXML private TableView storeTable;
    @FXML private TableColumn columnID, columnName, columnPrice, columnCategory, columnBrand, columnQuantity;
    private Stage stage;
    private ProductModel productModel = new ProductModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColNames();
        loadStore();
    }

    @FXML
    public void btnAddNew(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AddProduct.fxml"));
        Parent parent = loader.load();
        stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadStore() {
        storeTable.getItems().clear();

        setColNames();

        Task<Void> fillTableWithProductsTask = new Task<Void>() {
            @Override
            protected Void call() {
                storeTable.getItems().addAll(productModel.getProducts());
                return null;
            }
        };
        Thread fillTableWithProductsThread = new Thread(fillTableWithProductsTask);
        fillTableWithProductsThread.start();
    }

    @FXML
    public void deleteStore() {
        Task<Void> deleteProductFromTableTask = new Task<Void>() {
            @Override
            protected Void call() {
                productModel.removeProduct(productModel.getProducts().get(storeTable.getSelectionModel().getSelectedIndex()));
                loadStore();
                return null;
            }
        };
        Thread deleteProductFromTableThread = new Thread(deleteProductFromTableTask);
        deleteProductFromTableThread.start();


    }

    public void setColNames(){
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void close() {

    }

    public StoreController()  {

    }
}
