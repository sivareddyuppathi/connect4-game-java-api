package com.connect4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONNECT4_GAME")
public class Connect4 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String uniqueGameId;
	private Integer columnIndex;
	private Integer rowIndex;
	private Character player;

	public Connect4() {

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

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Character getPlayer() {
		return player;
	}

	public void setPlayer(Character player) {
		this.player = player;
	}

}
