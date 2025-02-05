package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import com.palmen.supermarket.user.dto.WishlistDTO;
import com.palmen.supermarket.user.persistence.entity.Wishlist;

@Mapper(componentModel = "spring")
public interface IWishlistMapper {

	Wishlist wishlistDTOToWishlist(WishlistDTO wishlistDTO);
}
