package items;

import dbUtil.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ItemController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_unit;
    @FXML
    private TextField tf_packing;
    @FXML
    private TextField tf_des;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_clr;
    @FXML
    private Button btn_display;
    @FXML
    private TableView<Items> tableview_item;
    @FXML
    private TableColumn<Items, String> column_id;
    @FXML
    private TableColumn<Items, String> column_unit;
    @FXML
    private TableColumn<Items, String> column_packing;
    @FXML
    private TableColumn<Items, String> column_des;

    private DBConnection dbconnection;
    private ObservableList<Items> data;

    private String sql = "select * from Items where id = ?";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dbconnection = new DBConnection();
    }

    @FXML
    private void loadItemData(ActionEvent event){
        try {
            Connection conn = DBConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "B204S");

            ResultSet rs = pr.executeQuery();

            while (rs.next()){
                this.data.add(new Items(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }

            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        this.column_id.setCellValueFactory(new PropertyValueFactory<Items, String>("ID"));
        this.column_id.setCellValueFactory(new PropertyValueFactory<Items, String>("UNIT"));
        this.column_id.setCellValueFactory(new PropertyValueFactory<Items, String>("UNIT_PACKING"));
        this.column_id.setCellValueFactory(new PropertyValueFactory<Items, String>("DESCRIPTION"));

        this.tableview_item.setItems(null);
        this.tableview_item.setItems(this.data);
    }

    @FXML
    private void addItem(ActionEvent event) {
        String sqlInsert = "INSERT INTO ITEMS(id, unit, unit_packing, description) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlInsert);

            statement.setString(1, this.tf_id.getText());
            statement.setString(2, this.tf_unit.getText());
            statement.setString(3, this.tf_packing.getText());
            statement.setString(4, this.tf_des.getText());

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearField(ActionEvent event){
        this.tf_id.setText("");
        this.tf_unit.setText("");
        this.tf_packing.setText("");
        this.tf_des.setText("");
    }
}
