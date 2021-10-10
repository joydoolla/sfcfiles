package com.sfc.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@Getter @Setter @ToString
@EntityListeners(AuditingEntityListener.class)
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="email")
	private String userName;
	
	@Column(name="password")
    @JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="status")
    private String status;
	
	@OneToMany
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonBackReference
	private List<Product> productlist;
	
	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name="last_modified")
	private LocalDateTime lastModified;

}
