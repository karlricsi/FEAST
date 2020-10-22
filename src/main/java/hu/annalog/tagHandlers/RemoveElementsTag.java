package hu.annalog.tagHandlers;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class RemoveElementsTag extends SimpleTagSupport {

	private String selector = "";

	public void setSelector(String selector) {
		this.selector = selector;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspContext().getOut()
				.print("<al-removeelements data-selector=\"" + selector + "\"></al-removeelements>");
	}

}