package com.cg.pizzaordering.dto;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@EntityListeners({ AuditingEntityListener.class })
@Table(name="Pizza")
public class Pizza {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pizza_id")
	private Long pizzaId;
	
	@NotEmpty(message = "Should not be empty")
	@Column(name="pizza_name")
	private String pizzaName;
	@Column(name="pizza_cost")
	private Long pizzaCost;
	@Column(name="pizza_size")
	private String pizzaSize;
	@Column(name="pizza_type")
	private String pizzaType;
	@Column(name="pizza_description")
	private String pizzaDescription;

	@Column(name="delete_flag")
	private int deleteFlag=0;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	

	
	
	
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

	
	
	public Pizza() {
	}



	public Pizza(Long pizzaId, @NotEmpty(message = "Should not be empty") String pizzaName, Long pizzaCost,
			String pizzaSize, String pizzaType, String pizzaDescription, int deleteFlag, Store store, Date createdDate,
			Date modifiedDate, String createdBy, String modifiedBy) {
		super();
		this.pizzaId = pizzaId;
		this.pizzaName = pizzaName;
		this.pizzaCost = pizzaCost;
		this.pizzaSize = pizzaSize;
		this.pizzaType = pizzaType;
		this.pizzaDescription = pizzaDescription;
		this.deleteFlag = deleteFlag;
		this.store = store;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}



	public Long getPizzaId() {
		return pizzaId;
	}



	public void setPizzaId(Long pizzaId) {
		this.pizzaId = pizzaId;
	}



	public String getPizzaName() {
		return pizzaName;
	}



	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}



	public Long getPizzaCost() {
		return pizzaCost;
	}



	public void setPizzaCost(Long pizzaCost) {
		this.pizzaCost = pizzaCost;
	}



	public String getPizzaSize() {
		return pizzaSize;
	}



	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}



	public String getPizzaType() {
		return pizzaType;
	}



	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}



	public String getPizzaDescription() {
		return pizzaDescription;
	}



	public void setPizzaDescription(String pizzaDescription) {
		this.pizzaDescription = pizzaDescription;
	}



	public int getDeleteFlag() {
		return deleteFlag;
	}



	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}



	public Store getStore() {
		return store;
	}



	public void setStore(Store store) {
		this.store = store;
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
		return "Pizza [pizzaId=" + pizzaId + ", pizzaName=" + pizzaName + ", pizzaCost=" + pizzaCost + ", pizzaSize="
				+ pizzaSize + ", pizzaType=" + pizzaType + ", pizzaDescription=" + pizzaDescription + ", deleteFlag="
				+ deleteFlag + ", store=" + store + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}



	
}