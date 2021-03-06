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
	private String responseModel = "";
	private String subjectModel = "";
	private String target = "body";
	private String removedReferencesTarget = "";

	public void setMethod(String method) {
		this.method = method;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setResponseModel(String responseModel) {
		this.responseModel = responseModel;
	}

	public void setSubjectModel(String subjectModel) {
		this.subjectModel = subjectModel;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setRemovedReferencesTarget(String removedReferencesTarget) {
		this.removedReferencesTarget = removedReferencesTarget;
	}

	@Override
	public void doTag() throws JspException, IOException {
		this.getJspBody().invoke(content);
		this.getJspContext().getOut()
				.print("<al-ajax data-method=\"" + method + "\" data-url=\"" + url + "\" data-data=\"" + data
						+ "\" data-responsemodel=\"" + responseModel + "\" data-subjectmodel=\"" + subjectModel
						+ "\" data-target=\"" + target + "\" data-removedreferencestarget=\"" + removedReferencesTarget
						+ "\">" + content.toString() + "</al-ajax>");
	}

}