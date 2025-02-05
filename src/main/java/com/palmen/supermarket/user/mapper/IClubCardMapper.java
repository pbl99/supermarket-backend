package com.palmen.supermarket.user.mapper;

import org.mapstruct.Mapper;
import com.palmen.supermarket.user.dto.ClubCardDTO;
import com.palmen.supermarket.user.persistence.entity.ClubCard;

@Mapper(componentModel = "spring")
public interface IClubCardMapper {

	ClubCard clubCardDTOToClubCard(ClubCardDTO clubCardDto);
}
