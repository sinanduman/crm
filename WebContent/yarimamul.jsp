<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en" ng-app="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="img/favicon.ico">
    <title>Irfan Plastik</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/docs.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="css/font-awesome.css">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List" %>

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
    <div class="row text-warning" style="text-align:center;">
        <label class="text-danger">Yarımamül Ekleme</label>
    </div>
    <script type="text/javascript">
        function YariMamulEkleCtrl($scope, $http) {
            $scope.yarimamulEkle = function () {
                return false;
                //alert($scope.adsoy + " : " + $scope.gorev);
                console.log($scope.adsoy + ' : ' + $scope.gorev);
                $.ajax({
                    url: '/HelloWorld/ajaxutils',
                    method: 'post',
                    crossDomain: true,
                    data: {username: $scope.adsoy, password: $scope.gorev},
                    headers: {
                        Accept: "text/plain; charset=utf-8",
                        "Content-Type": "application/x-www-form-urlencoded; charset=utf-8",
                    },
                    success: function (data, textStatus, xhr) {
                        console.log(data);
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.log("Hata Oluştu: " + textStatus + " , " + errorThrown);
                    }
                }).done(function (msg) {
                            if (msg) {
                                console.log(name + " ürünün Stok bilgisi başarıyla GÜNCELLENDİ");
                            }
                            else {
                                console.log("Hata: " + msg);
                            }
                        });
            }
        }
    </script>
    <div class="row" ng-controller="YariMamulEkleCtrl">
        <form class="form-horizontal" role="form" method="post" action="/HelloWorld/yarimamul">
            <div class="form-group">
                <label for="yarimamulkod" class="col-xs-3 control-label">Yarımamül Kodu: </label>

                <div class="col-xs-8">
                    <input type="text" class="form-control" name="yarimamulkod" ng-model="yarimamulkod"
                           placeholder="Yarımamül Kodu">
                </div>
            </div>
            <div class="form-group">
                <label for="yarimamulad" class="col-xs-3 control-label">Yarımamül Adı: </label>

                <div class="col-xs-8">
                    <input type="text" class="form-control" name="yarimamulad" ng-model="yarimamulad"
                           placeholder="Yarımamül Adı">
                </div>
            </div>
            <div class="form-group">
                <label for="yarimamulbirim" class="col-xs-3 control-label">Birim: </label>

                <div class="col-xs-8">
                    <select class="form-control" name="yarimamulbirim">
                        <%
                            List<Birim> birim = (List<Birim>) request.getAttribute("birim");
                            for (Birim b : birim) {
                                if (BirimTip.ADET.value() == b.getId()){
                                    %>
                                    <option value='<%=b.getId()%>' selected><%=b.getAd() %></option>
                                    <%
                                }
                                else{
                                    %>
                                    <option value='<%=b.getId()%>'><%=b.getAd() %></option>
                                    <%
                                }
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="yarimamulfirma" class="col-xs-3 control-label">Tedarikçi: </label>
                
                <div class="col-xs-8">
                    <select class="form-control" name="yarimamulfirma">
                        <%
                            List<Firma> firma = (List<Firma>) request.getAttribute("firma");
                            for (Firma f : firma) {
                        %>
                        <option value='<%=f.getId()%>'><%=f.getAd() %>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">&nbsp;</label>

                <div class="col-xs-8">
                    <button type="submit" class="btn btn-warning">Ekle</button>
                </div>
            </div>
        </form>
    </div>
    <%
        List<Bilesen> yarimamul = (List<Bilesen>) request.getAttribute("yarimamul");
        int sayac = 0;
    %>

    <div class="row">
        <div class="container">
            <div class="col-sm-3"></div>
            <div class="col-sm-8">
                <table class="table table-striped table-bordered">
                    <% for (Bilesen y : yarimamul) { %>
                    <% if (sayac++ == 0) { %>
                    <tr>
                        <th><label>Kod</label></th>
                        <th><label>Ad</label></th>
                        <th><label>Aksiyon</label></th>
                    </tr>
                    <% } %>
                    <tr>
                        <td><%= y.getKod() %>
                        </td>
                        <td><%= y.getAd() %>
                        </td>
                        <td>
                            <a href="/HelloWorld/yarimamul/<%= y.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-ok btn btn-info"></button></a>
                            <a href="/HelloWorld/yarimamul/<%= y.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-remove btn btn-danger"></button></a>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
            <div class="col-sm-1"></div>
        </div>
    </div>


</div>


<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
