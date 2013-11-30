<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>My First Application</title>
</head>
<body>

<form action="login">
    <p><input type="text" name="username">

    <p><input type="password" name="password">
</form>

Hello World!
<%="Şakır"%>

<br/> 1+2+3 = ${1+2+3}

<%
    Connection con = null;
    PreparedStatement stmt = null;
    String url = "jdbc:mysql://localhost:3306/dekotekdb";
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "nana");
        stmt = con.prepareStatement("SELECT * from Category;");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            out.println("Name: " + rs.getString("Name") + "<br/>");
        }
        rs.close();
    } catch (Exception ex) {
        out.println("Unable to connect to database.");
        ex.printStackTrace();
    } finally {
        try {
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
        }
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
        }
    }
%>

</body>
</html>