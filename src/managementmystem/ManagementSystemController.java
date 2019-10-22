package managementmystem;

import items.ItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagementSystemController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label db_status;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_pwd;
    @FXML
    private Button btn_login;
    @FXML
    private Label label_loginstatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (this.loginModel.isDatabaseConnected()){
            this.db_status.setText("Connected to Database");
        }else {
            this.db_status.setText("Not connected to Database");
        }
    }

    @FXML
    public void Login(ActionEvent event){
        try {
            if (this.loginModel.isLogin(this.tf_username.getText(), this.tf_pwd.getText())){
                Stage stage = (Stage)this.btn_login.getScene().getWindow();
                stage.close();
                loginInterface();
            }else {
                label_loginstatus.setText("Wrong Credentials!!!");
            }

        }catch (Exception localException){
            localException.printStackTrace();
        }
    }

    private void loginInterface(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/items/itemFXML.fxml"));

            ItemController itemController = (ItemController) loader.getController();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Item dashboard");
            stage.setResizable(false);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
