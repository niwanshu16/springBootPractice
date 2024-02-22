package com.dailycode.PaymentService.Service;

import com.dailycode.PaymentService.Model.PaymentRequest;
import com.dailycode.PaymentService.Model.PaymentResponse;

public interface PaymentService {

	Long doPayment(PaymentRequest request);

	PaymentResponse getPaymentDetailsByOrderId(Long id);

}
