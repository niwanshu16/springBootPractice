package com.dailycode.PaymentService.Entity;

import java.time.Instant;

import com.dailycode.PaymentService.Model.PaymentMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ORDER_ID")
	private Long orderID;
	
	@Column(name = "PAYMENT_MODE")
	private PaymentMode paymentMode;
	
	@Column(name = "PAYMENT_DATE")
	private Instant paymentDate;
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name = "REFERENCE_NUMBER")
	private String referenceNumber;
	
	@Column(name = "AMOUNT")
	private Long amount;
}
