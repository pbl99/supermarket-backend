package com.palmen.supermarket.user.service;

import com.palmen.supermarket.user.dto.ClubCardDTO;

public interface IClubCardService {

	Boolean createClubCard(ClubCardDTO clubCardDTO);
	Boolean addPointsToClubCard(Long customerId);
}
