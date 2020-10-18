'use strict';
class Model {

	constructor(name, value, synchronized) {
		this.name = name;
		this.value = value;
		this.references = [];
		this.synchronized = synchronized == true;
	}

	getValue(number = 0, element = 0) {
		return this.value[number][element];
	}

	setValue(value, number = 0, element = 0, synchronize = false) {
		this.value[number][element] = value;
		if (this.synchronized && !synchronize) {
			localStorage.setItem('synchronize', '{"event":"modelChange","name":"' + this.name +
				'","number":"' + number + '","element":"' + element + '","value":"' + value + '"}');
			localStorage.removeItem('synchronize');
		}
	}

	getValues() {
		return this.value
	}

	getReferences() {
		return this.references;
	}

	addReference(reference) {
		this.references.push(reference);
	}

	length() {
		return this.value.length;
	}

}
class Models {

	constructor() {
		this.models = [];
	}

	getModel(name) {
		return this.models[name];
	}

	addModel(name, value, synchronized) {
		if (isNaN(value)) {
			if (value.substr(0, 2) != '[{')
				value = '[{"0":' + value + '}]';
		}
		else
			value = '[{"0":' + value + '}]';
		this.models[name] = new Model(name, JSON.parse(value), synchronized);
	}

	removeModel(name) {
		delete this.models[name];
	}

	getValue(name, number, element) {
		return this.getModel(name).getValue(number, element);
	}

	setValue(value, name, number, element, synchronize) {
		this.getModel(name).setValue(value, number, element, synchronize);
		if (synchronize)
			document.querySelectorAll('INPUT[data-model="' + name + '"][data-number="' + number +
				'"][data-element="' + element + '"]').forEach(element => {
					if (element.type == 'checkbox')
						element.checked = value == 'true';
					else
						element.value = value;
				});
		this.getReferences(name).forEach(reference =>
			reference.textContent = reference.hasAttribute('data-operation') ? this.interpreter(reference.dataset.operation)[0][0] :
				this.getValue(reference.dataset.model, reference.dataset.number, reference.dataset.element));
	}

	getValues(name) {
		return this.getModel(name).getValues();
	}

	getReferences(name) {
		return this.getModel(name).getReferences();
	}

	addReference(name, reference) {
		this.getModel(name).addReference(reference);
	}

	interpreter(operation) {
		function executor(operators, factors, models) {
			let definedOperators = ['*', '/', '+', '-'];
			let index = -1;
			let i = 0;
			for (i = 0; i < 4; i++) {
				index = operators.indexOf(definedOperators[i]);
				if (index > -1) break;
			}
			if (index > -1) {
				switch (definedOperators[i]) {
					case '*':
						factors[index + 1][0] = (isNaN(factors[index][0]) ?
							models.getValue(factors[index][0], factors[index][1], factors[index][2]) : +factors[index][0]) *
							(isNaN(factors[index + 1][0]) ?
								models.getValue(factors[index + 1][0], factors[index + 1][1], factors[index + 1][2]) : +factors[index + 1][0]);
						break;
					case '/':
						factors[index + 1][0] = (isNaN(factors[index][0]) ?
							models.getValue(factors[index][0], factors[index][1], factors[index][2]) : +factors[index][0]) /
							(isNaN(factors[index + 1][0]) ?
								models.getValue(factors[index + 1][0], factors[index + 1][1], factors[index + 1][2]) : +factors[index + 1][0]);
						break;
					case '+':
						factors[index + 1][0] = (isNaN(factors[index][0]) ?
							models.getValue(factors[index][0], factors[index][1], factors[index][2]) : +factors[index][0]) +
							(isNaN(factors[index + 1][0]) ?
								models.getValue(factors[index + 1][0], factors[index + 1][1], factors[index + 1][2]) : +factors[index + 1][0]);
						break;
					case '-':
						factors[index + 1][0] = (isNaN(factors[index][0]) ?
							models.getValue(factors[index][0], factors[index][1], factors[index][2]) : +factors[index][0]) -
							(isNaN(factors[index + 1][0]) ?
								models.getValue(factors[index + 1][0], factors[index + 1][1], factors[index + 1][2]) : +factors[index + 1][0]);
						break;
					default:
						break;
				}
				operators.splice(index, 1);
				factors.splice(index, 1);
			}
			else {
				if (isNaN(factors))
					factors[0][0] = models.getValue(factors[0][0], factors[0][1], factors[0][2]);
			}
			if (operators.length > 0)
				executor(operators, factors, models);
		}

		let expressions = [/LENGTH\([A-Za-z0-9]*\)/g, /SUMPRODUCT\([A-Za-z0-9]*,[A-Za-z0-9]*,[A-Za-z0-9]*\)/g, /\(.*\)/g];
		let functions = [
			function(models) {
				return models.getModel(operation.slice(7, -1)).length();
			},
			function(models) {
				let properties = operation.slice(11, -1).split(',');
				let sumProduct = 0;
				models.getModel(properties[0]).getValues().forEach(element => {
					sumProduct += element[properties[1]] * element[properties[2]];
				});
				return sumProduct;
			},
			function(models) {
				return models.interpreter(match[0].slice(1, -1))[0][0];
			}];
		expressions.forEach((expression, index) => {
			let match = operation.match(expression);
			if (match != null)
				do {
					operation = operation.replace(match, functions[index](this));
					match = operation.match(expression);
				} while (match != null);
		});
		let factors = [];
		let operators = operation.match(/\+|\-|\*|\//g);
		if (operators == null) {
			operators = [];
			factors.push(operation.split('.'));
		}
		else
			operation.split(/\+|\-|\*|\//g).forEach(model => {
				factors.push(model.split('.'));
			});
		executor(operators, factors, this);
		return factors;
	}

}
class Annalog {

	constructor() {
		this.alDom = document.getElementsByTagName('al-application')[0];
		this.models = new Models();
		document.body.removeChild(document.getElementsByTagName('al-application')[0]);
		this.constructDomTree(this.alDom.querySelector('[data-name="al-mainView"]'), document.body);
		window.addEventListener('storage', event => {
			if (event.key == 'synchronize') {
				let message = JSON.parse(event.newValue);
				if (message != null) {
					switch (message.event) {
						case 'modelChange':
							this.models.setValue(message.value, message.name, message.number, message.element, true);
							break;
						default:
							break;
					}
				}
			}
		});
	}

	constructDomTree(start, target) {
		Array.from(start.childNodes).forEach(node => {
			if (node.nodeName.substring(0, 3) == 'AL-') {
				switch (node.localName) {
					case 'al-model':
						let value = node.textContent;
						if (node.hasAttribute('data-number'))
							value = +value;
						this.models.addModel(node.dataset.name, node.textContent, node.hasAttribute('data-synchronized'));
						break;
					case 'al-fill':
						let view = this.alDom.querySelector('al-view[data-name="' + node.dataset.view + '"]');
						this.models.addModel(node.dataset.counter, 0);
						for (let i = 0; i < this.models.getValues(node.dataset.model).length; i++) {
							this.models.setValue(i, node.dataset.counter);
							Array.from(document.querySelectorAll(node.dataset.target)).forEach(node =>
								this.constructDomTree(view, node));
						}
						this.models.removeModel(node.dataset.counter);
						break;
					default:
						break;
				}
			}
			else {
				let newTarget = target.appendChild(node.cloneNode(false));
				function handler(node, models) {
					if (node.type == 'checkbox')
						models.setValue(node.checked, node.dataset.model, node.dataset.number, node.dataset.element);
					else
						models.setValue(node.value, node.dataset.model, node.dataset.number, node.dataset.element);
					models.getReferences(node.dataset.model).forEach(reference =>
						reference.textContent = reference.hasAttribute('data-operation') ? models.interpreter(reference.dataset.operation)[0][0] :
							models.getValue(reference.dataset.model, reference.dataset.number, reference.dataset.element))
				}
				if (newTarget.nodeType == 1) {
					if (newTarget.hasAttribute('data-number') && isNaN(newTarget.dataset.number))
						newTarget.setAttribute('data-number', this.models.getValue(newTarget.dataset.number));
					if (newTarget.hasAttribute('data-operation'))
						newTarget.setAttribute('data-operation', newTarget.dataset.operation.replace(/%([A-Za-z0-9_-])+/g, match => {
							return this.models.getValue(match.substr(1));
						}));
					if (newTarget.hasAttribute('data-model')) {
						if (newTarget.nodeName == 'INPUT') {
							if (!newTarget.hasAttribute('data-number')) newTarget.setAttribute('data-number', 0);
							if (!newTarget.hasAttribute('data-element')) newTarget.setAttribute('data-element', 0);
							newTarget.value = this.models.getValue(newTarget.dataset.model,
								newTarget.dataset.number, newTarget.dataset.element);
							newTarget.addEventListener('change', _ => handler(newTarget, this.models));
							newTarget.addEventListener('keyup', _ => handler(newTarget, this.models));
						}
						else
							this.models.addReference(newTarget.dataset.model, newTarget);
						newTarget.textContent = newTarget.hasAttribute('data-operation') ? this.models.interpreter(newTarget.dataset.operation)[0][0] :
							this.models.getValue(newTarget.dataset.model, newTarget.dataset.number, newTarget.dataset.element);
					}
					if(newTarget.attributes.length>0)
						Array.from(newTarget.attributes).forEach(attr=>{
							if (attr.value=='%model')
								attr.nodeValue=attr.nodeValue.replace('%model',
									this.models.getValue(newTarget.dataset.attributemodel, newTarget.dataset.number, newTarget.dataset.element));
						});
				}
				this.constructDomTree(node, newTarget);
			}
		});
	}

}
document.addEventListener('DOMContentLoaded', function() {
	new Annalog();
});
