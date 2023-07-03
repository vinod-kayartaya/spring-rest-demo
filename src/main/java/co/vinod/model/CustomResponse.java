package co.vinod.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomResponse {
	private String message;
	private String timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date());

	public CustomResponse(String message) {
		this.message = message;
	}

}
