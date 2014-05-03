package crm.irfan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManagerBackup {
    private static Connection con;
    private static String url;
    
    public ConnectionManagerBackup(){
        ConnectionManagerBackup.connectionReady();
    }
    
    private static void connectionReady() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //url = "jdbc:mysql://localhost:3306/irfandb";
            url = "jdbc:postgresql://localhost:10000/d7fd7f2d9538345908607109fc46788a7";
            Class.forName("org.postgresql.Driver");
            try {
                con = DriverManager.getConnection(url, "udb33ed01e2f2466da5f148fe09b6ef58", "pe83fe22dd7904f6c84a285807fbcd2d4");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
