package app.bean;

public class Response {
	private boolean errorStatus;
	private String errorMessage;
	private String resultMessage;

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage)		 {
		this.resultMessage = resultMessage;
	}

}
