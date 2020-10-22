package hu.annalog.entities;

public class AjaxResponse<T> {

	private String subject;
	private T model;

	public AjaxResponse(String subject, T model) {
		super();
		this.subject = subject;
		this.model = model;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}