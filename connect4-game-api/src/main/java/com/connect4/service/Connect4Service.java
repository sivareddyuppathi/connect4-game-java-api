package com.connect4.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect4.entity.Connect4;
import com.connect4.entity.GameDetails;
import com.connect4.model.GameResponse;
import com.connect4.repository.Connect4repository;
import com.connect4.repository.GameDetailsRepository;
import com.connect4.util.Connect4Util;

@Service
public class Connect4Service {

	@Autowired
	Connect4repository connect4Repo;

	@Autowired
	GameDetailsRepository gmaeDtlsRepo;

	public GameDetails createGame(String start) {
		GameDetails gameDetails = new GameDetails();
		if (start.equalsIgnoreCase("START")) {
			String gameUniqueId = UUID.randomUUID().toString().substring(0, 10) + "-" + Instant.now();
			gameDetails.setUniqueGameId(gameUniqueId);
			gameDetails.setStatus("READY");
			gameDetails.setWinner(".");
			gmaeDtlsRepo.save(gameDetails);
			gameDetails.setWinner(null);
			return gameDetails;
		}
		gameDetails.setStatus("INVALID-- Please start the gmae..");
		return gameDetails;
	}

	public GameResponse playGame(String uniqueGameId, int currentColumnIndex) {
		GameResponse response = new GameResponse();
		GameDetails dtls = gmaeDtlsRepo.findByUniqueGameId(uniqueGameId); // is uniqueGameId exists or not
		if (dtls == null) {
			response.setStatus("INVALID");
			response.setMessage("Invalid unique-game-id");
			return response;
		}
		if (dtls.getStatus().equals("COMPLETED")) {
			response.setStatus("COMPLETED");
			response.setMessage("Game completed on this unique-game-id");
			return response;
		}

		List<Connect4> previousMoves = connect4Repo.findByUniqueGameId(uniqueGameId); // previous moves by UGID
		GameResponse gameresp = new Connect4Util().playGame(uniqueGameId, currentColumnIndex, previousMoves, response);
		if (gameresp.getStatus().equalsIgnoreCase("VALID")) {
			connect4Repo.save(gameresp.getConnect());
		}
		if (gameresp.getMessage() != null) {
			if (gameresp.getMessage().equalsIgnoreCase("RED wins")
					|| gameresp.getMessage().equalsIgnoreCase("YELLOW Wins")
					|| gameresp.getMessage().equalsIgnoreCase("DRAW")) {
				dtls.setStatus("COMPLETED");
				dtls.setWinner(gameresp.getMessage());
				gmaeDtlsRepo.save(dtls);
			}
		}

		gameresp.setConnect(null);
		return gameresp;
	}

}
