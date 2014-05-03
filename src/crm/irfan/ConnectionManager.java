package crm.irfan;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class ConnectionManager {
    @Resource(name="jdbc/postgres")
    
    private static Connection con;
    private static DataSource ds;
    private static Context    ctx;
    
    public ConnectionManager() {
        ConnectionManager.connectionReady();
    }
    
    private static void connectionReady() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/postgres");
            try {
                con = ds.getConnection();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                System.out.println("Baglanti aciliyor!.. ");
                connectionReady();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
