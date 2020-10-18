package hu.annalog.tagHandlers;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ApplicationTag extends SimpleTagSupport {

	private StringWriter content = new StringWriter();

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut()
				.print("<al-application hidden>" + content.toString() + "</al-application>");
	}

}
