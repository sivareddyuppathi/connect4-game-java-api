package com.connect4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "GAME_DETAILS")
@JsonInclude(Include.NON_NULL)
public class GameDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	@JsonProperty("Game-UniqueId")
	private String uniqueGameId;
	@JsonProperty("Status")
	private String status;
	private String winner;

	public GameDetails() {

	}

	public GameDetails(Long id, String uniqueGameId, String status) {
		super();
		this.id = id;
		this.uniqueGameId = uniqueGameId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueGameId() {
		return uniqueGameId;
	}

	public void setUniqueGameId(String uniqueGameId) {
		this.uniqueGameId = uniqueGameId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "GameDetails [id=" + id + ", uniqueGameId=" + uniqueGameId + ", status=" + status + ", winner=" + winner
				+ "]";
	}

}
