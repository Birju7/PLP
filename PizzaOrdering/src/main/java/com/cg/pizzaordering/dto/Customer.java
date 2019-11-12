package com.cg.pizzaordering.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;




@Entity
@EntityListeners({ AuditingEntityListener.class })
public class Customer {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	@Column(name="username",unique=true)

	protected String username;
	@Column(name="email_id")
	@Email(message="Enter valid email")
	protected String emailId;

	@Column(name="user_mobile")
	protected String userMobile;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;

	@Column(name="password")

	private String password;
	@Column(name="role")
	private String role;
	

	@Column(name="user_address")
	private String userAddress;
	@Column(name="delete_flag")
	private int deleteFlag=0;
	
	
	@OneToOne
	@JoinColumn(name="customer_order")
	@JsonIgnore
	private Pizza pizza;
	

	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Cart cart;
	
	
	
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "modified_date")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

	
	//Default role for a new registered user is USER.
	//We can add Admin through database.
	public Customer() {
		this.role="USER";
	}


	public Customer(Long userId, String username, @Email(message = "Enter valid email") String emailId,
			String userMobile, String firstName, String lastName, String password, String role, String userAddress,
			int deleteFlag, Pizza pizza, Date createdDate, Date modifiedDate, String createdBy, String modifiedBy) {
		super();
		this.userId = userId;
		this.username = username;
		this.emailId = emailId;
		this.userMobile = userMobile;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.userAddress = userAddress;
		this.deleteFlag = deleteFlag;
		this.pizza = pizza;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public int getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public Pizza getPizza() {
		return pizza;
	}


	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [userId=");
		builder.append(userId);
		builder.append(", username=");
		builder.append(username);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", userMobile=");
		builder.append(userMobile);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", role=");
		builder.append(role);
		builder.append(", userAddress=");
		builder.append(userAddress);
		builder.append(", deleteFlag=");
		builder.append(deleteFlag);
		builder.append(", pizza=");
		builder.append(pizza);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", modifiedBy=");
		builder.append(modifiedBy);
		builder.append("]");
		return builder.toString();
	}



}