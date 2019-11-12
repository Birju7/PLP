package com.cg.pizzaordering.dto;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners({ AuditingEntityListener.class })
public class Store {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long storeId;
	@Column(name="store_name",unique = true)
	private String storeName;
	@Column(name="delete_flag")
	private int deleteFlag=0;
	@JsonIgnore 
	@OneToMany(mappedBy = "store" , cascade=CascadeType.ALL)
	private List<Pizza> pizzaList=new LinkedList<Pizza>();
	
	@Column(name="store_address")
	private String storeAddress;
	@Column(name="store_phone_number")
	private String storePhoneNumber;
	
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
	
	
	public Store() {

	}
	
	
	public Store(Long storeId, String storeName, int deleteFlag, List<Pizza> pizzaList, String storeAddress,
			String storePhoneNumber, Date createdDate, Date modifiedDate, String createdBy, String modifiedBy) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.deleteFlag = deleteFlag;
		this.pizzaList = pizzaList;
		this.storeAddress = storeAddress;
		this.storePhoneNumber = storePhoneNumber;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}


	public Long getStoreId() {
		return storeId;
	}


	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public int getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public List<Pizza> getPizzaList() {
		return pizzaList;
	}


	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}


	public String getStoreAddress() {
		return storeAddress;
	}


	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}


	public String getStorePhoneNumber() {
		return storePhoneNumber;
	}


	public void setStorePhoneNumber(String storePhoneNumber) {
		this.storePhoneNumber = storePhoneNumber;
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
	
	
	
	
	
	

	

}