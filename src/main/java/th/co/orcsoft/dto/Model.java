package th.co.orcsoft.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Model {
	
	private HeaderDto header;
	private Object body;
	
	public Model() {
		this.header = new HeaderDto();
	}
	
	public HeaderDto getHeader() {
		return header ;
	}

	public void setHeader(HeaderDto header) {
		this.header = header;
	} 
	
	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
}
