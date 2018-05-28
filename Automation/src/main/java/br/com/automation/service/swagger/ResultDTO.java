package br.com.automation.service.swagger;

public class ResultDTO {

	public ResultDTO(String method, String code, String url, String body) {
		super();
		this.method = method;
		this.code = code;
		this.url = url;
		this.body = body;
	}

	private String method;

	private String code;

	private String url;

	private String body;

	@Override
	public String toString() {

		return method + " - " + url + "\r\n" + " - " + code + "\r\n" + body
				+ "\r\n --------------------------------------------------------------------------------------------";
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
