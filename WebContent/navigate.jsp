<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.User" %>
<%
User user = (User) request.getSession().getAttribute("user");
%>
<div class="container" style="margin-top:20px;">

	<div class="row">
		<!-- /btn-group -->
		<div class="btn-group">
			<button type="button" class="btn btn-primary">Üretim</button>
			<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="uretimtakip">
						<span class="fa fa-dot-circle-o text-primary"></span> Üretim Takibi</a>
				</li>
				<li>
					<a href="uretimplan">
						<span class="fa fa-dot-circle-o text-primary"></span> Üretim Planlama</a>
				</li>
			</ul>
		</div>
		<!-- /btn-group -->			
		<div class="btn-group" style="display:none;">
			<button type="button" class="btn btn-info">Sipariş</button>
			<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
				<span class="sr-only">Toggle Dropdown</span>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="siparis"><span class="fa fa-dot-circle-o text-info"></span> Yeni Sipariş</a>
				</li>
				<li>
					<a href="bekleyensiparis">
						<span class="fa fa-dot-circle-o text-info"></span> Üretimde/Bekleyen Siparişler</a>
				</li>
				<li>
					<a href="tamamlanansiparis">
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
			<ul class="dropdown-menu">
				<li>
					<a href="hammaddestok"><span class="fa fa-dot-circle-o text-success"></span> Hammadde ve Yarı Mamül</a>
				</li>
				<!-- li>
					<a href="yarimamulstok"><span class="fa fa-dot-circle-o text-success"></span> Yarı Mamül</a>
				</li-->
				<li>
					<a href="mamulstok"><span class="fa fa-dot-circle-o text-success"></span> Mamül</a>
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
			<ul class="dropdown-menu">
				<li>
					<a href="hammadde">
						<span class="fa fa-dot-circle-o text-warning"></span> Hammadde ve Yarı Mamül</a>
				</li>
				<li>
					<a href="mamul">
						<span class="fa fa-dot-circle-o text-warning"></span> Mamül</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="calisan">
						<span class="fa fa-dot-circle-o text-warning"></span> Çalışan</a>
				</li>
				<li>
					<a href="makina">
						<span class="fa fa-dot-circle-o text-warning"></span> Makina/Bant</a>
				</li>
				<li>
					<a href="firma">
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
			<ul class="dropdown-menu">
				<li>
					<a href="stokrapor">
						<span class="fa fa-dot-circle-o text-danger"></span> Stok</a>
				</li>				
				<li>
					<a href="uretimrapor">
						<span class="fa fa-dot-circle-o text-danger"></span> Üretim</a>
				</li>
				<li>
					<a href="sevkrapor">
						<span class="fa fa-dot-circle-o text-danger"></span> Sevk</a>
				</li>
				<li>
					<a href="gkrliste">
						<span class="fa fa-dot-circle-o text-danger"></span> GKR Listesi</a>
				</li>
				<li>
					<a href="izlemeno">
						<span class="fa fa-dot-circle-o text-danger"></span> Mamül İzleme No</a>
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
			<ul class="dropdown-menu">
				<li>
					<a href="irsaliye">
						<span class="fa fa-dot-circle-o text-sevk"></span> İrsaliye Hazırla</a>
				</li>
				<li>
					<a href="irsaliyegonder">
						<span class="fa fa-dot-circle-o text-sevk"></span> Gönderilen İrsaliyeler</a>
				</li>
			</ul>
		</div>
		
		<div class="btn-group" style="width:10px;">&nbsp;</div>

		<div class="btn-group">
			<button type="button" class="btn btn-info dropdown-toggle"
					style="background-color:#E1E1C8;border:1px solid darkkhaki; color:darkkhaki;"
					data-toggle="dropdown">
				<%= user.getFullName()  %> <span class="caret" style="border-top-color:darkkhaki;"></span>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="logout">
						<span class="fa fa-sign-out" style="color:darkkhaki;"></span> Çıkış</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<hr/>
<!-- container -->
