package crm.irfan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm.irfan.entity.Birim;

public class DAOFunctions {
    
    protected static List<Birim> birimListeGetirTum() {
        List<Birim> temp = new ArrayList<Birim>();
        Connection conn = ConnectionManager.getConnection();

        String searchQuery = "select * from birim order by ad ";

        // connect to DB
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(searchQuery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                temp.add(new Birim(rs.getInt("id"), rs.getString("ad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }
    
}
