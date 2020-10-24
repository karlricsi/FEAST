'use strict';

class Tags {

	constructor(annalog) {

		this.annalog = annalog;

		this.model = node => {
			let value = node.textContent;
			if (node.hasAttribute('data-number'))
				value = +value;
			this.annalog.models.addModel(node.dataset.name, value, node.hasAttribute('data-synchronized'));
		}

		this.controller = node => {
			document.querySelectorAll(node.dataset.selector).forEach(element => {
				if (node.hasAttribute('data-removepreviousevent')) {
					let cloneNode = element.cloneNode(true);
					element.parentNode.replaceChild(cloneNode, element);
					element = cloneNode;
				}
				element.addEventListener(node.dataset.event, event => {
					let eventKeys = {};
					for (let key in event.target)
						if (!(typeof event.target[key] === 'object' || typeof event.target[key] === 'function'))
							eventKeys[key] = event.target[key];
					this.annalog.models.getModel('al_eventElement').setValue(eventKeys);
					Array.from(document.querySelectorAll(node.dataset.target)).forEach(selectedNode =>
						this.annalog.constructDomTree(node, selectedNode)
					);
				})
			});
		}

		this.ajax = node => {
			let data = {};
			node.dataset.data.split(',').forEach(dataElement => {
				let elementName = dataElement.substring(0, dataElement.indexOf(':'));
				let elementValue = dataElement.substring(dataElement.indexOf(':') + 1);
				if (dataElement.includes('%model.')) {
					elementValue = elementValue.substring(7);
					let model = '';
					let number = '';
					let element = '';
					let subElement = '';
					model = elementValue.substring(0, elementValue.indexOf('.')) || elementValue.substring(0, elementValue.indexOf('[')) || elementValue;
					elementValue = elementValue.replace('.', '');
					elementValue = elementValue.includes('.') ? elementValue.substring(elementValue.indexOf('.') - 1) : elementValue.substring(model.length);
					number = elementValue.substring(0, elementValue.indexOf('.')) || '0';
					elementValue = elementValue.replace('.', '').substring(elementValue.indexOf('.'), elementValue.length);
					element = (elementValue.includes('[') ? elementValue.substring(0, elementValue.indexOf('[')) : elementValue) || '0';
					subElement = elementValue.substring(elementValue.indexOf('[') + 1, elementValue.indexOf(']')) || '';
					data[elementName] = subElement ? this.annalog.models.getValue(model, number, element)[subElement]
						: this.annalog.models.getValue(model, number, element);
				} else
					data[elementName] = elementValue;
				if (!isNaN(data[elementName])) data[elementName] = +data[elementName];
			});
			let request = new XMLHttpRequest();
			request.open(node.dataset.method, node.dataset.url, true);
			request.setRequestHeader('Accept', 'application/json');
			request.setRequestHeader('Content-Type', 'application/json');
			this.annalog.models.getModel('al_ajax').setValue(request);
			request.send(JSON.stringify(data));
			request.addEventListener('load', _ => {
				let response = JSON.parse(request.responseText);
				if (node.dataset.removedreferencestarget) {
					let indexes = [];
					this.annalog.models.getReferences(node.dataset.responsemodel).forEach((reference, i) => {
						if (reference.matches(node.dataset.removedreferencestarget))
							indexes.push(i);
					});
					indexes.reverse().forEach(index =>
						this.annalog.models.getModel(node.dataset.responsemodel).removeReference(index)
					);
				}
				this.annalog.models.addModel(node.dataset.responsemodel, JSON.stringify(response.model));
				this.annalog.models.addModel(node.dataset.subjectmodel, JSON.stringify(response.subject));
				Array.from(document.querySelectorAll(node.dataset.target)).forEach(selectedNode =>
					this.annalog.constructDomTree(node, selectedNode)
				);
			});
		}

		this.condition = node => {
			let condition = false;
			switch (node.dataset.term) {
				case 'equal':
					if (this.annalog.models.getValue(node.dataset.model) == node.dataset.value)
						condition = true;
					break;
				case 'notequal':
					if (this.annalog.models.getValue(node.dataset.model) != node.dataset.value)
						condition = true;
					break;
			}
			if (condition)
				Array.from(document.querySelectorAll(node.dataset.target)).forEach(selectedNode =>
					this.annalog.constructDomTree(node, selectedNode)
				);
		}

		this.fill = node => {
			let view = this.annalog.alDom.querySelector('al-view[data-name="' + node.dataset.view + '"]');
			this.annalog.models.addModel(node.dataset.counter, 0);
			for (let i = 0; i < this.annalog.models.getModel(node.dataset.model).length(); i++) {
				this.annalog.models.setValue(i, node.dataset.counter);
				Array.from(document.querySelectorAll(node.dataset.target)).forEach((selectedNode, j) => {
					if (node.hasAttribute('data-groupnumberelement')) {
						if (this.annalog.models.getValue(node.dataset.model, i, node.dataset.groupnumberelement) == j + 1)
							this.annalog.constructDomTree(view, selectedNode);
					} else
						this.annalog.constructDomTree(view, selectedNode);
				});
			}
			this.annalog.models.removeModel(node.dataset.counter);
		}

		this.removeelements = node => {
			Array.from(document.querySelectorAll(node.dataset.selector)).forEach(selectedNode =>
				selectedNode.remove()
			);
		}

		this.setattribute = node => {
			document.querySelectorAll(node.dataset.selector).forEach(selectedNode =>
				selectedNode[node.dataset.attribute] = node.dataset.value
			);
		}

		this.setclass = node => {
			document.querySelectorAll(node.dataset.selector).forEach(selectedNode => {
				switch (node.dataset.doit) {
					case 'add':
						selectedNode.classList.add(node.dataset.classname);
						break;
					case 'remove':
						selectedNode.classList.remove(node.dataset.classname);
						break;
				}
			});
		}

	}

}