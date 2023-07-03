package co.vinod.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@Entity
@Table(name="shippers")
@XmlRootElement
public class Shipper {
	@Id
	@Column(name="shipper_id")
	private Integer shipperId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column
	private String phone;
}
