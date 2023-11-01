package com.dailycode.OrderService.Entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderID;
	
	@Column(name = "PRODUCT_ID")
	private Long productID;
	
	@Column(name = "QUANTITY")
	private Long quantity;
	
	@Column(name = "ORDER_DATE")
	private Instant OrderDate;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "AMOUNT")
	private long amount;
	
}
