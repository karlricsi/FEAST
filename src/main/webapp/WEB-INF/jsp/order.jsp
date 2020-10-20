<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%>
<!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST Fogyasztás rögzítés</title>
		<script src='js/annalogJs.js'></script>
		<style>
#menudiv {
	width: calc(70% - 10px);
	margin: 0;
	padding: 5px;
}
#basketdiv {
	width: calc(30% - 10px);
	margin: 0;
	position: absolute;
	padding: 5px;
}
.inlineblock {
	display: inline-table;
}
		</style>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<annalog:model name='users' type='array'>${users}</annalog:model>
				<annalog:model name='categories' type='array'>${categories}</annalog:model>
				<annalog:model name='foods' type='array'>${foods}</annalog:model>
				<annalog:model name='basket' type='array'>${basket}</annalog:model>
				<h1>Tesztfeladat - FEAST - Factory Eating Administration SysTem</h1>
				<div class='inlineblock' id='menudiv'><fieldset>
					<legend>Étlap</legend>
					<ul id='menu'>
					</ul>
				</fieldset></div>
				<div class='inlineblock' id='basketdiv'>
					<label for='user-select'>Dolgozó: </label>
					<select id='user-select'>
						<option disabled selected value='0'>- Válassz -</option>
					</select>
					<div>
						<div class='inlineblock'>
							<div>
								Tételek száma: 
								<span data-model='basket' data-operation='SUM(basket,quantity)'></span>
							</div>
							<div>
								Összesen: 
								<span data-model='basket' data-operation='SUMPRODUCT(basket,price,quantity)'></span>
								Ft
							</div>
						</div>
						<div class='inlineblock'>
							<button>Rendelést lezár</button>
						</div>
					</div>
					<fieldset>
						<legend>Kosár</legend>
						<div id='basket'></div>
					</fieldset>
				</div>
				<annalog:fill model='users' counter='counter' view='user' target='[id=user-select]' />
				<annalog:fill model='categories' counter='counter' view='food-category' target='[id=menu]' />
				<annalog:fill model='foods' counter='counter' view='food' target='[id=menu] ul' groupNumberElement='category'/>
				<annalog:fill model='basket' counter='counter' view='basketitem' target='[id=basket]'></annalog:fill>
			</annalog:view>
			<annalog:view name='user'>
				<option value='%model' data-attributemodel='users' data-number='counter' data-element='id' data-textelement='name'></option>
			</annalog:view>
			<annalog:view name='food-category'>
				<span data-model='categories' data-number='counter' data-element='name'></span>
				<ul id='category-%model' data-attributemodel='categories' data-number='counter' data-element='id'></ul>
			</annalog:view>
			<annalog:view name='food'>
				<li id='food-%model' data-attributemodel='foods' data-number='counter' data-element='id'>
					<div data-model='foods' data-number='counter' data-element='name'></div>
					<div class='inlineblock'><span data-model='foods' data-number='counter' data-element='price'></span>Ft</div>
					<div class='inlineblock'><button>Hozzáad</button></div>
				</li>
			</annalog:view>
			<annalog:view name='basketitem'>
				<div></div>
			</annalog:view>
		</annalog:application>
	</body>
</html>