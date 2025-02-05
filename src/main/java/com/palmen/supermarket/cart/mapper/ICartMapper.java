package com.palmen.supermarket.cart.mapper;

import org.mapstruct.Mapper;
import com.palmen.supermarket.cart.dto.CartDTO;
import com.palmen.supermarket.cart.persistence.entity.Cart;

@Mapper(componentModel = "spring")
public interface ICartMapper {

	Cart cartDTOToCart(CartDTO cartDTO);
}
