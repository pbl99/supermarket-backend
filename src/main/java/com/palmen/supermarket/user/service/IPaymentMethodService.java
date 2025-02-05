package com.palmen.supermarket.user.service;

import com.palmen.supermarket.user.dto.PaymentMethodDTO;

public interface IPaymentMethodService {

	Boolean createPaymentMethod(PaymentMethodDTO paymentMethodDTO);
}
