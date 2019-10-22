package managementmystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.DBConnection;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null){
            System.exit(1);
        }
    }


    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String user, String pwd) throws Exception{

        PreparedStatement pre = null;
        ResultSet resultSet = null;

//        StringBuffer sql = new StringBuffer();
//        sql.append("SELECT * ")
//                .append("FROM user ")
//                .append("WHERE username = :usr ")
//                .append("AND password = :pwd");
        // TODO 优化登录
        String sql = "select * from User where username = ? and password = ?";

        try {
            pre = this.connection.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pwd);

            resultSet = pre.executeQuery();

            if (resultSet.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            return false;
        }finally {
            pre.close();
            resultSet.close();
        }

    }
}
