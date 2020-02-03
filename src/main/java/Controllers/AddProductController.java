package Controllers;

import Models.ProductModel;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddProductController {
    @FXML private JFXTextField tfProductName;
    @FXML private JFXTextField tfCategory;
    @FXML private JFXTextField tfBrand;
    @FXML private JFXTextField tfPrice;
    @FXML private JFXTextField tfAmount;
    private Stage stage;
    private AnchorPane pane;

    @FXML
    public void addProduct(ActionEvent actionEvent) {
        String productName = tfProductName.getText();
        String productCategory = tfCategory.getText();
        String productBrand = tfBrand.getText();
        int productPrice = Integer.parseInt(tfPrice.getText());
        int productAmount = Integer.parseInt(tfAmount.getText());

        Task<Void> addProductTask = new Task<Void>() {
            @Override
            protected Void call() {
                ProductModel product = new ProductModel();
                product.createProduct(productName, productCategory, productBrand, productPrice, productAmount);
                return null;
            }
        };
        addProductTask.setOnSucceeded(e ->{
            try {
                ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Thread addProductThread = new Thread(addProductTask);
        addProductThread.start();
    }

    @FXML
    public void closeClickedd(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }
}
