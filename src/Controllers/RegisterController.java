package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegisterController {


    @FXML
    public void cancel(ActionEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void minClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void closeClicked(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void addMamba(ActionEvent e) throws IOException {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Sucess.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
