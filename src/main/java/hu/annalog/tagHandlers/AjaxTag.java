package hu.annalog.tagHandlers;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AjaxTag extends SimpleTagSupport {

	private StringWriter content = new StringWriter();
	private String method = "POST";
	private String url = "";
	private String data = "";

	public void setMethod(String method) {
		this.method = method;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut().print("<al-ajax data-method=\"" + method + "\" data-url=\"" + url
				+ "\" data-data=\"" + data + "\">" + content.toString() + "</al-ajax>");
	}

}