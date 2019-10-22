package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String SQLITECONN = "jdbc:sqlite:\\ExportManagementSystem\\db\\managementsystem.sqlite";

    public static Connection getConnection() throws SQLException {

        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLITECONN);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}

