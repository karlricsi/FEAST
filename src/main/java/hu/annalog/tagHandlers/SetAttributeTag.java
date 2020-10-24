package hu.annalog.tagHandlers;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SetAttributeTag extends SimpleTagSupport {

	private String selector = "body";
	private String attribute = "";
	private String value = "";

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("<al-setattribute data-selector=\"" + selector + "\" data-attribute=\""
				+ attribute + "\" data-value=\"" + value + "\"></al-setclass>");
	}

}