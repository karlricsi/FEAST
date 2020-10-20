<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%>
<!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST Menü</title>
		<script src='js/annalogJs.js'></script>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<h1>Tesztfeladat - FEAST - Factory Eating Administration SysTem</h1>
				<fieldset>
					<legend>Menü</legend>
					<ul>
						<li><a href='order'>Fogyasztás rögzítés</a></li>
						<li><a href=''>Dolgozói fogyasztás riport</a></li>
						<li><a href=''>Termék fogyás riport</a></li>
					</ul>
				</fieldset>
			</annalog:view>
		</annalog:application>
	</body>
</html>