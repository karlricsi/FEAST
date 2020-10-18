<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%>
<!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST menü</title>
		<script src='js/annalogJs.js'></script>
		<link rel='stylesheet' href='css/feast.css'>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<annalog:model name='users' type='array' isSynchronized='true'>${users}</annalog:model>
				<h1>Tesztfeladat - FEAST - Üzemi étkezés adminisztrációs rendszer</h1>
				<fieldset>
					<legend>Menü</legend>
					<ul>
						<li>
							<button>Fogyasztás rögzítése</button>
							<label for='user-select'>Dolgozó: </label><select id='user-select'></select>
						</li>
						<li><button>Dolgozói fogyasztás riport</button></li>
						<li><button>Termék fogyás riport</button></li>
					</ul>
				</fieldset>
				<annalog:fill model='users' counter='counter' view='user' target='[id=user-select]' />
			</annalog:view>
			<annalog:view name='user'>
				<option value='%model' data-attributemodel='users' data-number='counter' data-element='id'>
					<span data-model='users' data-number='counter' data-element='name'></span>
				</option>
			</annalog:view>
		</annalog:application>
	</body>
</html>