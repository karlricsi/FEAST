package hu.annalog.tagHandlers;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ModelTag extends SimpleTagSupport {

	private StringWriter content = new StringWriter();
	private String name = "unNamed";
	private String type = "number";
	private Boolean isSynchronized = false;

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIsSynchronized(Boolean isSynchronized) {
		this.isSynchronized = isSynchronized;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		if (!(type.equals("string") || type.equals("number") || type.equals("array") || type.equals("boolean"))) {
			type = "number";
		}
		if (type.equals("number") && content.getBuffer().length() == 0) {
			content.append("0");
		}
		if (type.equals("string")) {
			String contentString = content.toString();
			content.getBuffer().setLength(0);
			content.write("\"" + contentString + "\"");
		}
		this.getJspContext().getOut().print("<al-model data-name=\"" + name + "\" data-" + type
				+ (this.isSynchronized ? " data-synchronized" : "") + ">" + content.toString() + "</al-model>");
	}

}