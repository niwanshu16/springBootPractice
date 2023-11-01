package com.dailycode.PaymentService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.dailycode.PaymentService.Entity.TransactionDetails;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope; 

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails,Long> {
	
	TransactionDetails findByOrderID(Long orderId);
}
