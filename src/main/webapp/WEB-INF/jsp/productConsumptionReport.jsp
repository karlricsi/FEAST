<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%><!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST Termék fogyás riport</title>
		<script src='js/annalogJs.js'></script>
		<script src='js/annalogJsTags.js'></script>
		<style>
.disabled {
	display: none;
}
#table {
	display: table;
	border: 1px solid black;
}
.row {
	display: table-row;
}
.cell {
	display: table-cell;
	border: 1px solid black;
	padding: 5px;
}
		</style>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<annalog:model name='month' type='string'>${month}</annalog:model>
				<annalog:model name='consumptions' type='array'>${consumptions}</annalog:model>
				<header>
					<h1>Tesztfeladat - FEAST - Factory Eating Administration SysTem</h1>
					<h2>Termék fogyás riport: <span data-model='month'></span></h2>
				</header>
				<nav>
					<fieldset>
						<legend>Oldalak</legend>
						<ul>
							<li><a href='/'>Főoldal</a></li>
							<li><a href='/order'>Fogyasztás rögzítés</a></li>
							<li><a href='/workerconsumptionreport'>Dolgozói fogyasztás riport</a></li>
						</ul>
					</fieldset>
				</nav>
				<main>
					<div id='table'>
						<div class='row'>
							<div class='cell'><b>Termék</b></div>
							<div class='cell'><b>Fogyasztás</b></div>
						</div>
					</div>
				</main>
				<annalog:fill model='consumptions' counter='counter' view='consumption' target='#table' />
			</annalog:view>
			<annalog:view name='consumption'>
				<div class='row'>
					<div class='cell' data-model='consumptions' data-number='%model.counter' data-element='name'></div>
					<div class='cell'><span data-model='consumptions' data-number='%model.counter' data-element='consumption'></span>db</div>
				</div>
			</annalog:view>
		</annalog:application>
	</body>
</html>