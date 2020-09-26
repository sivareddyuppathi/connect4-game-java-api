package com.connect4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connect4.entity.GameDetails;
import com.connect4.model.GameResponse;
import com.connect4.service.Connect4Service;

@RestController
@RequestMapping("/api")
public class Connect4Contorller {

	List<Integer> droppedCoins = new ArrayList<>();

	@Autowired
	Connect4Service service;

	@GetMapping("/start-game/{start}")
	public GameDetails createGame(@PathVariable(value = "start") String start) {
		return service.createGame(start);
	}

	@GetMapping("/play/{uniqueGameId}/{columnIndex}")
	public GameResponse playGame(@NotBlank @PathVariable String uniqueGameId, @NotBlank @PathVariable int columnIndex) {
		return service.playGame(uniqueGameId, columnIndex);
	}

}
