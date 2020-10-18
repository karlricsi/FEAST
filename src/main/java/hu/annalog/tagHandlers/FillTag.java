package hu.annalog.tagHandlers;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FillTag extends SimpleTagSupport {

	private String model = "";
	private String counter = "";
	private String view = "";
	private String target = "";

	public void setModel(String model) {
		this.model = model;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public void setView(String view) {
		this.view = view;
	}

	public void setTarget(String targetId) {
		this.target = targetId;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("<al-fill data-model=\"" + model + "\" data-counter=\"" + counter + "\" data-view=\""
				+ view + "\" data-target=\"" + target + "\"></al-fill>");
	}

}
