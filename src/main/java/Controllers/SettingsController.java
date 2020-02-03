package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SettingsController {
    @FXML private StackPane spSettingContent;
    @FXML private Label settingsLabel;

    @FXML
    public void accountAction() throws IOException {
        settingsLabel.setText("Ustawienia konta");
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/MyAccount.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spSettingContent.getStylesheets().add(getClass().getResource("/FXML/viewEmployeStyle.css").toExternalForm());
        spSettingContent.getChildren().clear();
        spSettingContent.getChildren().add(acPane);
    }

    @FXML
    public void organizeAction() throws IOException {
        settingsLabel.setText("Ustawienia organizacji");
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/FXML/OrgSetting.fxml").openStream());
        AnchorPane acPane = fXMLLoader.getRoot();
        spSettingContent.getStylesheets().add(getClass().getResource("/FXML/viewEmployeStyle.css").toExternalForm());
        spSettingContent.getChildren().clear();
        spSettingContent.getChildren().add(acPane);
    }
}
