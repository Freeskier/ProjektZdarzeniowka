package Controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrganizeController implements Initializable {
    @FXML private JFXTextField tfOrgName;
    @FXML private JFXTextField tfOrgPhone;
    @FXML private JFXTextField tfWWW;
    private static String name;
    private static String phone;
    private static String address;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeOrg(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Home.fxml"));
        loader.load();

        name = tfOrgName.getText();
        phone = tfOrgPhone.getText();
        address = tfWWW.getText();

        HomeController homeController = loader.getController();
        homeController.setFlag(1);
        homeController.setOrgName(name);
        homeController.setTxtorgPhoneNumber(phone);
        homeController.setTxtOrgAddress(address);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
