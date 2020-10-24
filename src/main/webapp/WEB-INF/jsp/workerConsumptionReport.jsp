<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%><!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST Dolgozó fogyasztás riport</title>
		<script src='js/annalogJs.js'></script>
		<script src='js/annalogJsTags.js'></script>
		<style>
.disabled {
	display: none;
}
		</style>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<annalog:model name='users' type='array'>${users}</annalog:model>
				<annalog:model name='user' type='number'>0</annalog:model>
				<annalog:model name='years' type='array'>${years}</annalog:model>
				<annalog:model name='year' type='number'>0</annalog:model>
				<annalog:model name='months' type='array'>${years}</annalog:model>
				<annalog:model name='month' type='number'>0</annalog:model>
				<header>
					<h1>Tesztfeladat - FEAST - Factory Eating Administration SysTem</h1>
				</header>
				<nav>
					<fieldset>
						<legend>Oldalak</legend>
						<ul>
							<li><a href='/'>Főoldal</a></li>
							<li><a href='/order'>Fogyasztás rögzítés</a></li>
							<li><a href='/productconsumptionreport'>Termék fogyás riport</a></li>
						</ul>
					</fieldset>
				</nav>
				<main>
					<label for='userselect'>Dolgozó: </label>
					<select id='userselect' data-model='user'>
						<option disabled selected value='0'>- Válassz -</option>
					</select>
					<label for='yearselect'>Év: </label>
					<select id='yearselect' data-model='year'>
						<option disabled selected value='0'>- Válassz -</option>
					</select>
					<label for='monthselect'>Hónap: </label>
					<select id='monthselect' data-model='month'>
						<option disabled selected value='0'>- Válassz -</option>
					</select>
					<button id='query' class='disabled'>Lekérdez</button>
					<div id='table'></div>
				</main>
				<annalog:fill model='years' counter='counter' view='year' target='#yearselect' />
				<annalog:fill model='users' counter='counter' view='user' target='#userselect' />
				<annalog:controller selector='select' event='change'>
					<annalog:concondition model='user' value='0' term='notequal'>
						<annalog:concondition model='year' value='0' term='notequal'>
							<annalog:concondition model='month' value='0' term='notequal'>
								<annalog:setClass selector='#query' className='disabled' doIt='remove'/>
							</annalog:concondition>
						</annalog:concondition>
					</annalog:concondition>
				</annalog:controller>
				<annalog:controller selector='#yearselect' event='change'>
					<annalog:ajax url='process/yearselect' data='year:%model.year' responseModel='months' subjectModel='message'>
						<annalog:concondition model='message' value='OK'>
							<annalog:setClass selector='#query' className='disabled' doIt='add'/>
							<annalog:removeElements selector='.monthoption' />
							<annalog:fill model='months' counter='counter' view='month' target='#monthselect' />
							<annalog:setAttribute selector='#monthselect' attribute='value' value='0' />
						</annalog:concondition>
					</annalog:ajax>
				</annalog:controller>
			</annalog:view>
			<annalog:view name='user'>
				<option value='%model' data-attributemodel='users' data-number='%model.counter' data-element='id' data-textelement='name'></option>
			</annalog:view>
			<annalog:view name='year'>
				<option value='%model' data-attributemodel='years' data-number='%model.counter' data-element='year' data-textelement='year'></option>
			</annalog:view>
			<annalog:view name='month'>
				<option class='monthoption' value='%model' data-attributemodel='months' data-number='%model.counter' data-element='month' data-textelement='month'></option>
			</annalog:view>
		</annalog:application>
	</body>
</html>