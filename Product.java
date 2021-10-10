package com.sfc.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name="products")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "product_id")
	private int productId;

	@Column(name= "barcode_number")
	private String barcodeNumber;

	@Column(name= "sugar_content")
	private String sugarContent;

	@Column(name= "product_category")
	private String productCategory;

	@Column(name= "sub_category")
	private String subCategory;

	@Column(name= "city")
	private String city;
	
	@Column(name= "state")
	private String state;

	@Column(name= "zip_code")
	private String zipCode;

	@Column(name= "prod_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
	private Date prodDate;
	
	@Transient
	private String location;
	
	@Column(name="time_zone")
	private String timeZoneLocation;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private Users user;
	
	@Column(name="user_id", insertable = false, updatable = false)
	private int userId; 

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name="last_modified")
	private LocalDateTime lastModified;
	
	
	public String getLocation() {
	    return this.city+","+this.state;
	  }
}
