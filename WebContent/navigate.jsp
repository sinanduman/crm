<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.User" %>
<%
User user = (User) request.getSession().getAttribute("user");
%>
<div class="container">

	<div class="container text-center" style="margin:10px 30px 20px 30px;">
		<a href="/irfanpls/"><img alt="İrfan Plastik" src="img/ip_logo_mini.png"></a>
	</div>


	<div class="row">

		<div class="col-sm-10">
			<!-- /btn-group -->
			<div class="btn-group">
				<button type="button" class="btn btn-primary">Üretim</button>
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					<span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/irfanpls/uretimplan">
							<span class="fa fa-dot-circle-o text-primary"></span> Üretim Planlama</a>
					</li>
					<li>
						<a href="/irfanpls/uretimtakip">
							<span class="fa fa-dot-circle-o text-primary"></span> Üretim Takibi</a>
					</li>
					<!--<li class="divider"></li>
					<li>
						<a href="#">
							<span class="fa fa-dot-circle-o text-primary"></span> Malzeme Planlama</a>
					</li>-->
				</ul>
			</div>
			<!-- /btn-group -->			
			<div class="btn-group">
				<button type="button" class="btn btn-info">Sipariş</button>
				<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					<span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/irfanpls/siparis"><span class="fa fa-dot-circle-o text-info"></span> Yeni Sipariş</a>
					</li>
					<li>
						<a href="/irfanpls/bekleyensiparis">
							<span class="fa fa-dot-circle-o text-info"></span> Üretimde/Bekleyen Siparişler</a>
					</li>
					<li>
						<a href="/irfanpls/tamamlanansiparis">
							<span class="fa fa-dot-circle-o text-info"></span> Tamamlanan Siparişler</a>
					</li>
				</ul>
			</div>
			<!-- /btn-group -->
			<div class="btn-group">
				<button type="button" class="btn btn-success">Stok</button>
				<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					<span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/irfanpls/hammaddestok"><span class="fa fa-dot-circle-o text-success"></span> Hammadde</a>
					</li>					
					<li>
						<a href="/irfanpls/yarimamulstok"><span class="fa fa-dot-circle-o text-success"></span> Yarı Mamül</a>
					</li>
					<li>
						<a href="/irfanpls/mamulstok"><span class="fa fa-dot-circle-o text-success"></span> Mamül</a>
					</li>
				</ul>
			</div>			
			<!-- /btn-group -->
			<div class="btn-group">
				<button type="button" class="btn btn-warning">Tanımlar</button>
				<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					<span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/irfanpls/hammadde">
							<span class="fa fa-dot-circle-o text-warning"></span> Hammadde</a>
					</li>
					<li>
						<a href="/irfanpls/yarimamul">
							<span class="fa fa-dot-circle-o text-warning"></span> Yarı Mamül</a>
					</li>
					<li>
						<a href="/irfanpls/mamul">
							<span class="fa fa-dot-circle-o text-warning"></span> Mamül</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="/irfanpls/calisan">
							<span class="fa fa-dot-circle-o text-warning"></span> Çalışan</a>
					</li>
					<li>
						<a href="/irfanpls/makina">
							<span class="fa fa-dot-circle-o text-warning"></span> Makina/Bant</a>
					</li>
					<li>
						<a href="/irfanpls/depo">
							<span class="fa fa-dot-circle-o text-warning"></span> Depo</a>
					</li>
					<li>
						<a href="/irfanpls/firma">
							<span class="fa fa-dot-circle-o text-warning"></span> Firma</a>
					</li>
				</ul>
			</div>
			<!-- /btn-group -->
			<div class="btn-group">
				<button type="button" class="btn btn-danger">Raporlar</button>
				<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					<span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="#">
							<span class="fa fa-dot-circle-o text-danger"></span> Üretim</a>
					</li>
					<li>
						<a href="#">
							<span class="fa fa-dot-circle-o text-danger"></span> Stok</a>
					</li>
				</ul>
			</div>
			<!-- /btn-group -->
			<div class="btn-group">
				<button type="button" class="btn btn-sevk">Sevk</button>
				<button type="button" class="btn btn-sevk dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
					<span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/irfanpls/irsaliye">
							<span class="fa fa-dot-circle-o text-sevk"></span> İrsaliye Hazırla</a>
					</li>
					<li>
						<a href="/irfanpls/paket">
							<span class="fa fa-dot-circle-o text-sevk"></span> Paketleri Onayla</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="col-sm-2">
			<div class="btn-group">
				<button type="button" class="btn btn-info dropdown-toggle"
						style="background-color:#E1E1C8;border:1px solid darkkhaki; color:darkkhaki;"
						data-toggle="dropdown">
					<%= user.getFullName()  %> <span class="caret" style="border-top-color:darkkhaki;"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="/irfanpls/logout">
							<span class="fa fa-sign-out" style="color:darkkhaki;"></span> Çıkış</a>
					</li>
				</ul>

			</div>
		</div>

	</div>

</div>

<!-- container -->
<hr/>
