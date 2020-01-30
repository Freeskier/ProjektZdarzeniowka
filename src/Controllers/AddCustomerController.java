package Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML
    public void closeClickedd(MouseEvent e) {
        ((Stage)((Node)e.getSource()).getScene().getWindow()).close();
    }
}
