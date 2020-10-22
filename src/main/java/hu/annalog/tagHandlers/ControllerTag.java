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
	private boolean removePreviousEvent = false;

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setRemovePreviousEvent(boolean removePreviousEvent) {
		this.removePreviousEvent = removePreviousEvent;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut()
				.print("<al-controller data-selector=\"" + selector + "\" data-event=\"" + event + "\" data-target=\""
						+ target + "\"" + (removePreviousEvent ? " data-removepreviousevent" : "") + ">"
						+ content.toString() + "</al-controller>");
	}

}
