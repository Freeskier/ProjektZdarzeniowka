package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class SellController {
    @FXML private StackPane spMainContent;
    @FXML private Label sellLabel;

    @FXML
    public void sellOnAction() throws IOException {
        sellLabel.setText("Zamówienia");
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/ViewSell.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spMainContent.getStylesheets().add(getClass().getResource("/FXML/StoreCSS.css").toExternalForm());
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    public void customerOnAction() throws IOException {
        sellLabel.setText("Klienci");
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/ViewCustomer.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spMainContent.getStylesheets().add(getClass().getResource("/FXML/StoreCSS.css").toExternalForm());
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
}
