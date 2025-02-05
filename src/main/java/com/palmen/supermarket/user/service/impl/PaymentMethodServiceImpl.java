package com.palmen.supermarket.user.service.impl;

import org.springframework.stereotype.Service;
import com.palmen.supermarket.user.dto.PaymentMethodDTO;
import com.palmen.supermarket.user.mapper.IPaymentMethodMapper;
import com.palmen.supermarket.user.persistence.entity.PaymentMethod;
import com.palmen.supermarket.user.persistence.repository.IPaymentMethodRepository;
import com.palmen.supermarket.user.service.IPasswordService;
import com.palmen.supermarket.user.service.IPaymentMethodService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements IPaymentMethodService{

	private final IPaymentMethodRepository paymentMethodRepository;
	private final IPaymentMethodMapper paymentMethodMapper;
	private final IPasswordService passwordService;
	
	public Boolean createPaymentMethod(PaymentMethodDTO paymentMethodDTO) {
		PaymentMethod paymentMethod = paymentMethodMapper.paymentMethodDTOToPaymentMethodEntity(paymentMethodDTO);
		paymentMethod.setDetails(passwordService.encodePassword(paymentMethod.getDetails()));
		
		return paymentMethodRepository.save(paymentMethod) != null;
	}
}
