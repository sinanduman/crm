package crm.irfan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection con;
    private static String url;
    
    public ConnectionManager(){
        ConnectionManager.connectionReady();
    }
    
    private static void connectionReady() {
        try {
            //url = "jdbc:mysql://localhost:3306/irfandb";
            //Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:postgresql://localhost:5432/crm";
            Class.forName("org.postgresql.Driver");
            try {
                //con = DriverManager.getConnection(url, "root", "nana");
                con = DriverManager.getConnection(url, "sinanduman", "nana");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                System.out.println("Hazir Degil!.. " );
                connectionReady();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
