<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="img/favicon.ico">
    <link href='http://fonts.googleapis.com/css?family=Dosis:400,500,600,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto:100,400,300,500,700' rel='stylesheet' type='text/css'>
    <title>Irfan Plastik</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/docs.css">
    <link href="css/font-awesome.css" rel="stylesheet">


    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<%@ page import="crm.irfan.User" %>

<%
    Boolean loggedin = (Boolean) session.getAttribute("loggedin");
    if (loggedin == null || !loggedin) {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    User user = (User) session.getAttribute("user");
%>

<jsp:include page="navigate.jsp">
    <jsp:param value="user" name="user"/>
</jsp:include>


<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <span style="text-align:center;color:darkkhaki;font-size:24px;"> {{  Haftalık Faaliyet }}</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <span style="text-align:center;color:darkkhaki;font-size:24px;"> {{  Haftalık Üretim }}</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <span style="text-align:center;color:darkkhaki;font-size:24px;"> {{  Haftalık Sipariş }}</span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <span style="text-align:center;color:darkkhaki;font-size:24px;"> {{  Haftalık Montaj }}</span>
        </div>
    </div>
</div>

<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
