package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private Text txtOrgAddress;
    @FXML private Text txtorgPhoneNumber;
    @FXML private Label orgName, sellValue, stockValue, employeValue, totalItem;
    private static int flag = 0;

    private OrganizeController organizeController = new OrganizeController();
    private EmployeeModel employeeModel = new EmployeeModel();
    private ProductModel productModel = new ProductModel();
    private CartModel cartModel = new CartModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(flag == 0){
            this.orgName.setText("E-bay");
            this.txtOrgAddress.setText("www.e-bay.com");
            this.txtorgPhoneNumber.setText("0 700 880 774");
        }
        else {
            this.txtOrgAddress.setText(organizeController.getAddress());
            this.txtorgPhoneNumber.setText(organizeController.getPhone());
            this.orgName.setText(organizeController.getName());
        }

        int totalValue = 0;
        for(int i = 0; i< productModel.getProducts().size(); i++) {
            totalValue += productModel.getProducts().get(i).getPrice();
        }

        int totalValue1 = 0;
        for(int i = 0; i< cartModel.getCartList().size(); i++) {
            totalValue1 += cartModel.getCartList().get(i).getPrice();
        }
        stockValue.setText(Integer.toString(totalValue));
        sellValue.setText(Integer.toString(totalValue1));
        employeValue.setText(Integer.toString(employeeModel.getEmployeList().size()));
        totalItem.setText(Integer.toString(productModel.getProducts().size()));
    }

    public void setTxtOrgAddress(String address) {
        this.txtOrgAddress.setText(address);
    }

    public void setTxtorgPhoneNumber(String phone) {
        this.txtorgPhoneNumber.setText(phone);
    }

    public void setOrgName(String name) {
        this.orgName.setText(name);
    }

    public void setFlag(int f) {
        flag = f;
    }
}
