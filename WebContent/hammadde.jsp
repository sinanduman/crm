<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en" ng-app>
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
    <link rel="stylesheet" href="css/font-awesome.css">
    <link rel="shortcut icon" href="img/favicon.ico">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<%@ page
        import="crm.irfan.User, crm.irfan.entity.Birim, crm.irfan.entity.Hammadde, java.util.List" %>

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
        <label class="text-warning">Hammadde Ekleme</label>
    </div>
    <script type="text/javascript">
        function HammaddeEkleCtrl($scope, $http) {
            $scope.hammaddeEkle = function () {
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
    <div class="row" ng-controller="HammaddeEkleCtrl">
        <form class="form-horizontal" role="form" method="post" action="/HelloWorld/hammadde">
            <div class="form-group">
                <label for="hamkod" class="col-xs-3 control-label">Hammadde Kodu: </label>

                <div class="col-xs-8">
                    <input type="text" class="form-control" name="hamkod" ng-model="hamkod" placeholder="Hammadde Kodu">
                </div>
            </div>
            <div class="form-group">
                <label for="hamad" class="col-xs-3 control-label">Hammadde Adı: </label>

                <div class="col-xs-8">
                    <input type="text" class="form-control" name="hamad" ng-model="hamad" placeholder="Hammadde Adı">
                </div>
            </div>
            <div class="form-group">
                <label for="hambirim" class="col-xs-3 control-label">Birim: </label>

                <div class="col-xs-8">
                    <select class="form-control" name="hambirim">
                        <%
                            List<Birim> birim = (List<Birim>) request.getAttribute("birim");
                            int sayac0 = 0;
                            for (Birim b : birim) {
                        %>
                        <option value='<%=b.getId()%>'><%=b.getAd() %>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="hamfirma" class="col-xs-3 control-label">Tedarikçi: </label>

                <div class="col-xs-8">
                    <input type="text" class="form-control" name="hamfirma" ng-model="hamfirma" placeholder="Tedarikçi">
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
        List<Hammadde> hammadde = (List<Hammadde>) request.getAttribute("hammadde");
        int sayac = 0;
    %>

    <div class="row">
        <div class="container">
            <div class="col-sm-3"></div>
            <div class="col-sm-8">
                <table class="table table-striped table-bordered">
                    <% for (Hammadde h : hammadde) { %>
                    <% if (sayac++ == 0) { %>
                    <tr>
                        <th><label>Hammadde Kodu</label></th>
                        <th><label>Hammadde Adı</label></th>
                        <th><label>Birim</label></th>
                        <th><label>Tedarikçi</label></th>
                        <th><label>Aksiyon</label></th>
                    </tr>
                    <% } %>
                    <tr>
                        <td><%= h.getKod() %>
                        </td>
                        <td><%= h.getAd() %>
                        </td>
                        <td><%= h.getBirimad() %>
                        </td>
                        <td><%= h.getFirmaad() %>
                        </td>
                        <td>
                            <a href="/HelloWorld/hammadde/<%= h.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-ok btn btn-info"></button></a>
                            <a href="/HelloWorld/hammadde/<%= h.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-remove btn btn-danger"></button></a>
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
