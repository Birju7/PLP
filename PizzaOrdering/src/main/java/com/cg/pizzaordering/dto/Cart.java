//package com.cg.pizzaordering.dto;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityListeners;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//@EntityListeners({ AuditingEntityListener.class })
//public class Cart {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long cartId;
////	// @Temporal(TemporalType.DATE)
////	@DateTimeFormat(pattern = "YYYY-MM-DD")
////	@Column(name = "check_in")
////	private LocalDate checkIn;
////	
////	@DateTimeFormat(pattern = "YYYY-MM-DD")
////	// @Temporal(TemporalType.DATE)
////	@Column(name = "check_out")
////	private LocalDate checkOut;
//	
//	@Column(name = "user_id")
//	private String userId;
//	
//	@Column(name = "store_id")
//	private String storeId;
//	
//	
////	@Column(name = "pizza_id")
////	private String pizzaId;
//	
////	@JsonIgnore
////	@OneToMany(mappedBy = "cart" , cascade=CascadeType.ALL)
////	private List<Pizza> pizzaList=new LinkedList<Pizza>();
//	
//	@Column(name = "delete_flag")
//	private int deleteFlag = 0;
////	@JsonIgnore
////	@OneToOne
////	private Customer customer;
//	
//	@Column(name = "created_date", nullable = false, updatable = false)
//	@CreatedDate
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createdDate;
//	@Column(name = "modified_date")
//	@LastModifiedDate
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date modifiedDate;
//	@Column(name = "created_by")
//	@CreatedBy
//	private String createdBy;
//	@Column(name = "modified_by")
//	@LastModifiedBy
//	private String modifiedBy;
//
//	public Cart() {
//	}
//
//	public Cart(Long cartId, String userId, String storeId, List<Pizza> pizzaList, int deleteFlag, Date createdDate,
//			Date modifiedDate, String createdBy, String modifiedBy) {
//		super();
//		this.cartId = cartId;
//		this.userId = userId;
//		this.storeId = storeId;
//		this.pizzaList = pizzaList;
//		this.deleteFlag = deleteFlag;
//		this.createdDate = createdDate;
//		this.modifiedDate = modifiedDate;
//		this.createdBy = createdBy;
//		this.modifiedBy = modifiedBy;
//	}
//
//	public Long getCartId() {
//		return cartId;
//	}
//
//	public void setCartId(Long cartId) {
//		this.cartId = cartId;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getStoreId() {
//		return storeId;
//	}
//
//	public void setStoreId(String storeId) {
//		this.storeId = storeId;
//	}
//
//	public List<Pizza> getPizzaList() {
//		return pizzaList;
//	}
//
//	public void setPizzaList(List<Pizza> pizzaList) {
//		this.pizzaList = pizzaList;
//	}
//
//	public int getDeleteFlag() {
//		return deleteFlag;
//	}
//
//	public void setDeleteFlag(int deleteFlag) {
//		this.deleteFlag = deleteFlag;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public Date getModifiedDate() {
//		return modifiedDate;
//	}
//
//	public void setModifiedDate(Date modifiedDate) {
//		this.modifiedDate = modifiedDate;
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	@Override
//	public String toString() {
//		return "Cart [cartId=" + cartId + ", userId=" + userId + ", storeId=" + storeId + ", pizzaList=" + pizzaList
//				+ ", deleteFlag=" + deleteFlag + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
//				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
//	}
//	
//
//
//
//	
//
//}