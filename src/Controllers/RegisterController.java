package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    public void addMember() {

    }

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
}
