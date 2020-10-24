package hu.annalog.tagHandlers;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SetClassTag extends SimpleTagSupport {

	private String selector = "";
	private String className = "";
	private String doIt = "";

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDoIt(String doIt) {
		this.doIt = doIt;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut().print("<al-setclass data-selector=\""
				+ selector + "\" data-classname=\"" + className + "\" data-doit=\"" + doIt + "\"></al-setclass>");
	}

}