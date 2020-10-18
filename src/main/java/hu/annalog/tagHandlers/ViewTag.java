package hu.annalog.tagHandlers;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ViewTag extends SimpleTagSupport {

	private StringWriter content = new StringWriter();
	private String name = "al-mainView";

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut().print("<al-view data-name=\"" + name + "\">" + content.toString() + "</al-view>");
	}

}
