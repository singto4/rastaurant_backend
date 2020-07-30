package th.co.orcsoft.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class HeaderDto {
	
	private String status;
	private String error_code;
	private String error_message;
	
	public HeaderDto() {
		setSuccess();
	}
	
	public HeaderDto(String staus, String error_code, String error_message) {
		this.status = staus;
		this.error_code = error_code;
		this.error_message = error_message;
	}
	
	protected void setSuccess() {
		this.status = "Success";
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorcode() {
		return error_code;
	}
	public void setErrorcode(String error_code) {
		this.error_code = error_code;
	}
	public String getErrormessage() {
		return error_message;
	}
	public void setErrormessage(String error_message) {
		this.error_message = error_message;
	}
}
