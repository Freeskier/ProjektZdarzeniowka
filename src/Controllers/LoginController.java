package Controllers;

import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private OctIconView badLoginCtr1;

    @FXML
    private OctIconView badLoginCtr2;

    @FXML
    private Label badLoginText;
    int i = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    public void loginButtonAction(ActionEvent e) throws IOException {

        if(i >2) {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Application.fxml"));
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }

        badLoginCtr1.setVisible(true);
        badLoginCtr2.setVisible(true);
        badLoginText.setVisible(true);
        i++;
    }

    @FXML
    public void registerClicked() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Register.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void minClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void closeClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

}
