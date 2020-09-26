package com.connect4.model;

import com.connect4.entity.Connect4;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GameResponse {

	private String status;
	private String message;
	private Connect4 connect;
	private String rMoves;
	private String yMoves;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Connect4 getConnect() {
		return connect;
	}

	public void setConnect(Connect4 connect) {
		this.connect = connect;
	}

	public String getrMoves() {
		return rMoves;
	}

	public void setrMoves(String rMoves) {
		this.rMoves = rMoves;
	}

	public String getyMoves() {
		return yMoves;
	}

	public void setyMoves(String yMoves) {
		this.yMoves = yMoves;
	}

}
