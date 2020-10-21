package hu.annalog.tagHandlers;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ControllerTag extends SimpleTagSupport {

	private StringWriter content = new StringWriter();
	private String selector = "body";
	private String event = "";
	private String target = "body";

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut().print("<al-controller data-selector=\"" + selector + "\" data-event=\"" + event
				+ "\" data-target=\"" + target + "\">" + content.toString() + "</al-controller>");
	}

}
