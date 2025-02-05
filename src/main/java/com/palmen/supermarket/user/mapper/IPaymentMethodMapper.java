package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import com.palmen.supermarket.user.dto.PaymentMethodDTO;
import com.palmen.supermarket.user.persistence.entity.PaymentMethod;

@Mapper(componentModel = "spring")
public interface IPaymentMethodMapper {

	PaymentMethod paymentMethodDTOToPaymentMethodEntity(PaymentMethodDTO paymentMethodDTO);
	PaymentMethodDTO paymentEntityToPaymentMethodDTO(PaymentMethod paymentMethod);
}
