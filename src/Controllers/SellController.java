package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SellController {

    @FXML
    private StackPane spMainContent;

    @FXML
    public void sellOnAction() throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/ViewSell.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spMainContent.getStylesheets().add(getClass().getResource("/FXML/StoreCSS.css").toExternalForm());
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    public void customerOnAction() throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/ViewCustomer.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spMainContent.getStylesheets().add(getClass().getResource("/FXML/StoreCSS.css").toExternalForm());
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
}
