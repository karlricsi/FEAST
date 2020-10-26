<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="annalog" uri="/WEB-INF/annalogTags.tld"%><!DOCTYPE html>
<html lang='hu'>
	<head>
	<meta charset="UTF-8">
		<title>FEAST Fogyasztás rögzítés</title>
		<script src='js/annalogJs.js'></script>
		<script src='js/annalogJsTags.js'></script>
		<style>
#menudiv {
	width: 70%;
	margin: 0;
	padding: 5px;
}
#basketdiv {
	margin: 0;
	padding: 5px;
}
.inlineblock {
	display: inline-table;
}
.disabled {
	display: none;
}
main {
	display: table;
	width: 100%;
}
#table-row {
	display: table-row;
	width: 100%;
}
.cell {
	display: table-cell;
}
		</style>
	</head>
	<body>
		<annalog:application>
			<annalog:view>
				<annalog:model name='users' type='array'>${users}</annalog:model>
				<annalog:model name='user' type='number'>0</annalog:model>
				<annalog:model name='categories' type='array'>${categories}</annalog:model>
				<annalog:model name='foods' type='array'>${foods}</annalog:model>
				<annalog:model name='basket' type='array'>[]</annalog:model>
				<header>
					<h1>Tesztfeladat - FEAST - Factory Eating Administration SysTem</h1>
					<h2>Fogyasztás rögzítés</h2>
				</header>
				<nav>
					<fieldset>
						<legend>Oldalak</legend>
						<ul>
							<li><a href='/FEAST/'>Főoldal</a></li>
							<li><a href='/FEAST/workerconsumptionreport'>Dolgozó fogyasztás riport</a></li>
							<li><a href='/FEAST/productconsumptionreport'>Termék fogyás riport</a></li>
						</ul>
					</fieldset>
				</nav>
				<main>
					<div id='table-row'>
						<div class='cell' id='menudiv'>
							<fieldset>
								<legend>Étlap</legend>
								<ul id='menu'>
								</ul>
							</fieldset>
						</div>
						<div class='cell' id='basketdiv'>
							<label for='userselect'>Dolgozó: </label>
							<select id='userselect' data-model='user'>
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
									<button id='closebutton' class='disabled'>Rendelést lezár</button>
								</div>
								<div class='inlineblock'>
									<button id='removebutton' class='disabled'>Rendelést töröl</button>
								</div>
							</div>
							<fieldset>
								<legend>Kosár</legend>
								<div id='basket'></div>
							</fieldset>
						</div>
					</div>
				</main>
				<annalog:fill model='users' counter='counter' view='user' target='#userselect' />
				<annalog:fill model='categories' counter='counter' view='food-category' target='#menu' />
				<annalog:fill model='foods' counter='counter' view='food' target='#menu ul' groupNumberElement='category' />
				<annalog:controller selector='.addbutton button' event='click'>
					<annalog:ajax url='process/addfood' data='userId:%model.user,foodId:%model.al_eventElement[value]' responseModel='basket' subjectModel='message'
						removedReferencesTarget='.basketRef'>
						<annalog:removeElements selector='.basketItem' />
						<annalog:concondition model='message' value='OK'>
							<annalog:fill model='basket' counter='counter' view='basketitem' target='#basket' />
							<annalog:setClass selector='#closebutton' className='disabled' doIt='remove' />
							<annalog:setClass selector='#removebutton' className='disabled' doIt='remove' />
						</annalog:concondition>
					</annalog:ajax>
				</annalog:controller>
				<annalog:controller selector='#userselect' event='change'>
					<annalog:setClass selector='.addbutton button' className='disabled' doIt='remove' />
					<annalog:ajax url='process/userselect' data='userId:%model.user' responseModel='basket' subjectModel='message' removedReferencesTarget='.basketRef'>
						<annalog:removeElements selector='.basketItem' />
						<annalog:concondition model='message' value='OK'>
							<annalog:fill model='basket' counter='counter' view='basketitem' target='#basket' />
							<annalog:setClass selector='#closebutton' className='disabled' doIt='remove' />
							<annalog:setClass selector='#removebutton' className='disabled' doIt='remove' />
						</annalog:concondition>
						<annalog:concondition model='message' value='Empty'>
							<annalog:setClass selector='#closebutton' className='disabled' doIt='add' />
							<annalog:setClass selector='#removebutton' className='disabled' doIt='add' />
						</annalog:concondition>
					</annalog:ajax>
				</annalog:controller>
				<annalog:controller selector='#closebutton' event='click'>
					<annalog:ajax url='process/closeorder' data='userId:%model.user' responseModel='basket' subjectModel='message' removedReferencesTarget='.basketRef'>
						<annalog:removeElements selector='.basketItem' />
						<annalog:concondition model='message' value='OK'>
							<annalog:setClass selector='#closebutton' className='disabled' doIt='add' />
							<annalog:setClass selector='#removebutton' className='disabled' doIt='add' />
							<annalog:setAttribute selector='#userselect' attribute='value' value='0' />
						</annalog:concondition>
					</annalog:ajax>
				</annalog:controller>
				<annalog:controller selector='#removebutton' event='click'>
					<annalog:ajax url='process/removeorder' data='userId:%model.user' responseModel='basket' subjectModel='message' removedReferencesTarget='.basketRef'>
						<annalog:removeElements selector='.basketItem' />
						<annalog:concondition model='message' value='OK'>
							<annalog:setClass selector='#closebutton' className='disabled' doIt='add' />
							<annalog:setClass selector='#removebutton' className='disabled' doIt='add' />
						</annalog:concondition>
					</annalog:ajax>
				</annalog:controller>
			</annalog:view>
			<annalog:view name='user'>
				<option value='%model' data-attributemodel='users' data-number='%model.counter' data-element='id' data-textelement='name'></option>
			</annalog:view>
			<annalog:view name='food-category'>
				<span data-model='categories' data-number='%model.counter' data-element='name'></span>
				<ul id='category-%model' data-attributemodel='categories' data-number='%model.counter' data-element='id'></ul>
			</annalog:view>
			<annalog:view name='food'>
				<li>
					<div data-model='foods' data-number='%model.counter' data-element='name'></div>
					<div class='inlineblock'><span data-model='foods' data-number='%model.counter' data-element='price'></span>Ft</div>
					<div class='inlineblock addbutton'>
						<button class='disabled' name='foodId' value='%model' data-attributemodel='foods' data-number='%model.counter' data-element='id'>Hozzáad</button>
					</div>
				</li>
			</annalog:view>
			<annalog:view name='basketitem'>
				<div class='basketItem'>
					<div class='basketRef' data-model='basket' data-number='%model.counter' data-element='foodName'></div>
					<div class='inlineblock'><span class='basketRef' data-model='basket' data-number='%model.counter' data-element='quantity'></span>db </div>
					<div class='inlineblock'><span class='basketRef' data-model='basket' data-number='%model.counter' data-element='price'></span>Ft</div>
					<div class='inlineblock removebutton'>
						<button name='foodId' value='%model' data-attributemodel='basket' data-number='%model.counter' data-element='foodId'>Töröl</button>
					</div>
				</div>
				<annalog:controller selector='.removebutton button' event='click' removePreviousEvent='true'>
					<annalog:ajax url='process/removefood' data='userId:%model.user,foodId:%model.al_eventElement[value]' responseModel='basket' subjectModel='message'
						removedReferencesTarget='.basketRef'>
						<annalog:removeElements selector='.basketItem' />
						<annalog:concondition model='message' value='OK'>
							<annalog:fill model='basket' counter='counter' view='basketitem' target='#basket' />
						</annalog:concondition>
					</annalog:ajax>
				</annalog:controller>
			</annalog:view>
		</annalog:application>
	</body>
</html>