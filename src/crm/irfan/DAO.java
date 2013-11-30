package crm.irfan;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
    private static Connection        currentCon = null;
    private static ResultSet         rs         = null;
    private static PreparedStatement pstmt      = null;

    public static User login(User user) {

        // preparing some objects for connection
        String username = user.getUsername();
        String password = user.getPassword();

        String searchQuery = "select * from login where username=? and password=?";
        // "System.out.println" prints in the console; Normally used to trace
        // the process
        System.out.println("Your user name is " + username);
        System.out.println("Your password is " + password);
        System.out.println("Query: " + searchQuery);

        try {
            // connect to DB
            currentCon = ConnectionManager.getConnection();
            pstmt = currentCon.prepareStatement(searchQuery);
            pstmt.setString(1, username);
            pstmt.setString(2, md5Dao(password));
            rs = pstmt.executeQuery();
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                user.setValid(false);
            }

            // if user exists set the isValid variable to true
            else if (more) {
                String firstName = rs.getString("name");
                String lastName = rs.getString("surname");

                System.out.println("Welcome " + firstName);
                user.setName(firstName);
                user.setSurname(lastName);
                user.setValid(true);
            }
        }
        catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        // some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e) {
                }
                rs = null;
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception e) {
                }
                pstmt = null;
            }
            /*
             * if (currentCon != null) { try { currentCon.close(); } catch
             * (Exception e) { } currentCon = null; }
             */
        }
        return user;
    }

    public static String md5Dao(String temp) {
        String digest = temp;
        if (temp != null) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(temp.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                digest = hash.toString(16);
                while (digest.length() < 32) { // 40 for SHA-1
                    digest = "0" + digest;
                }
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return digest;
    }
}
