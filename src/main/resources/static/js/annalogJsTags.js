'use strict';

class Tags {

	constructor(annalog) {

		this.annalog = annalog;

		this.model = function (node) {
			let value = node.textContent;
			if (node.hasAttribute('data-number'))
				value = +value;
			this.annalog.models.addModel(node.dataset.name, value, node.hasAttribute('data-synchronized'));
		}

		this.fill = function (node) {
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

	}

}