package hu.annalog.tagHandlers;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ConditionTag extends SimpleTagSupport {

	private StringWriter content = new StringWriter();
	private String model = "";
	private String term = "equal";
	private String value = "";
	private String target = "body";

	public void setModel(String model) {
		this.model = model;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut()
				.print("<al-condition data-model=\"" + model + "\" data-term=\"" + term + "\" data-value=\"" + value
						+ "\" data-target=\"" + target + "\">" + content.toString() + "</al-condition>");
	}

}