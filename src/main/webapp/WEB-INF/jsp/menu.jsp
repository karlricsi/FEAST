<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%><!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST Menü</title>
		<script src='js/annalogJs.js'></script>
		<script src='js/annalogJsTags.js'></script>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<header>
					<h1>Tesztfeladat - FEAST - Factory Eating Administration SysTem</h1>
					<h2>Főoldal</h2>
				</header>
				<nav>
					<fieldset>
						<legend>Oldalak</legend>
						<ul>
							<li><a href='/order'>Fogyasztás rögzítés</a></li>
							<li><a href='/workerconsumptionreport'>Dolgozó fogyasztás riport</a></li>
							<li><a href='/productconsumptionreport'>Termék fogyás riport</a></li>
						</ul>
					</fieldset>
				</nav>
			</annalog:view>
		</annalog:application>
	</body>
</html>