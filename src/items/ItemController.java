package items;

import dbUtil.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utils.StringUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ItemController implements Initializable {

    // 布局控件
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_unit;
    @FXML
    private TextField tf_packing;
    @FXML
    private TextField tf_des;
    @FXML
    private TextField tf_order_id;
    @FXML
    private TextField tf_order_des;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_clr;
    @FXML
    private Button btn_display;
    @FXML
    private Button btn_order_add;
    @FXML
    private Button btn_add2Order;
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
    @FXML
    private Label label_order_id;
    @FXML
    private Label label_order_unit;
    @FXML
    private Label label_order_packing;
    @FXML
    private Label label_order_des;

    @FXML
    private Tab tab_item;
    @FXML
    private Tab tab_order;

    private DBConnection dbconnection;
    private ObservableList<Items> data;

    private String sql = "select * from Items";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dbconnection = new DBConnection();
    }

    /**
     * 查询数据库并显示在表格中
     * @param event
     */
    @FXML
    private void loadItemData(ActionEvent event){
        try {
            Connection conn = DBConnection.getConnection();
            this.data = FXCollections.observableArrayList();

//            PreparedStatement pr = conn.prepareStatement(sql);
//            pr.setString(1, "B204S");
//
//            ResultSet rs = pr.executeQuery();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                this.data.add(new Items(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
         // 把查到的数据显示在表格中
        this.column_id.setCellValueFactory(new PropertyValueFactory<Items, String>("ID"));
        this.column_unit.setCellValueFactory(new PropertyValueFactory<Items, String>("UNIT"));
        this.column_packing.setCellValueFactory(new PropertyValueFactory<Items, String>("UNIT_PACKING"));
        this.column_des.setCellValueFactory(new PropertyValueFactory<Items, String>("DESCRIPTION"));

        this.tableview_item.setItems(null);
        this.tableview_item.setItems(this.data);
    }

    /**
     * 添加商品至数据库中
     * @param event
     */
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

            this.clearField();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void constarintDigitType(KeyEvent event){
        Character c = event.getCharacter().charAt(0);
        if (!(Character.isDigit(c))){
            event.consume();
        }
    }

    /**
     * 清除输入添加商品详情区域
     * @param event
     */
    private void clearField(){
        this.tf_id.setText("");
        this.tf_unit.setText("");
        this.tf_packing.setText("");
        this.tf_des.setText("");
    }

    @FXML
    private void selectTable(MouseEvent event){
//        System.out.println("get it");
        Items items = tableview_item.getSelectionModel().getSelectedItem();
        System.out.println(items.getID());
    }

    /**
     * 添加选中商品至订单中
     * @param event
     */
    @FXML
    private void addItemToOrder(ActionEvent event){
        Items items = tableview_item.getSelectionModel().getSelectedItem();
        label_order_id.setText(label_order_id.getText().toString()+"\n"+items.getID());
        label_order_unit.setText(label_order_unit.getText().toString()+"\n"+items.getUNIT());
        label_order_packing.setText(label_order_packing.getText().toString()+"\n"+items.getUNIT_PACKING());
        label_order_des.setText(label_order_des.getText().toString()+"\n"+items.getDESCRIPTION());

    }

}
