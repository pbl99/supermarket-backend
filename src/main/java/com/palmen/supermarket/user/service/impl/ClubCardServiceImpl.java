package com.palmen.supermarket.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.palmen.supermarket.user.dto.ClubCardDTO;
import com.palmen.supermarket.user.mapper.IClubCardMapper;
import com.palmen.supermarket.user.persistence.entity.ClubCard;
import com.palmen.supermarket.user.persistence.repository.IClubCardRepository;
import com.palmen.supermarket.user.service.IClubCardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubCardServiceImpl implements IClubCardService {

	private final IClubCardRepository clubCardRepository;
	private final IClubCardMapper clubCardMapper;

	@Transactional
	@Override
	public Boolean createClubCard(ClubCardDTO clubCardDTO) {
		return clubCardRepository.save(clubCardMapper.clubCardDTOToClubCard(clubCardDTO)) != null;
	}

	@Transactional
	@Override
	public Boolean addPointsToClubCard(Long customerId) {
		// Se añade 1 punto por cada 15€ de compra, y 1 punto equivale a 1€
		ClubCard clubCard = clubCardRepository.findById(customerId).orElseThrow();

		
		Double accumulatedMoney = clubCard.getAccumulatedMoney();
		
		if(accumulatedMoney >= 15) {
			Double points = accumulatedMoney/15;
			clubCard.setPoints(points);
			clubCard.setAccumulatedMoney(0.0);
		}
		

		return clubCardRepository.save(clubCard) != null;
	}
}
