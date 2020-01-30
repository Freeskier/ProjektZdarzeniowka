package Controllers;

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

public class ViewSellController {


    @FXML
    public void addSell() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/NewSell.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
