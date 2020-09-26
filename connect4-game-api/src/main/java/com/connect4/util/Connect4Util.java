package com.connect4.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.connect4.entity.Connect4;
import com.connect4.model.GameResponse;

@Component
public class Connect4Util {

	private static final String VALID_MOVE = "VALID";
	private static final String INVALID_MOVE = "INVALID";
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static char[][] board = new char[ROWS][COLUMNS];
//	private static final char[] PLAYERS = new char[] { 'R', 'Y' };

	public GameResponse playGame(String uniqueGameId, int currentColumnIndex, List<Connect4> previousMoves,
			GameResponse gameResp) {
		StringBuilder red = new StringBuilder();
		StringBuilder yellow = new StringBuilder();

		Connect4 connect4 = new Connect4();

		if (!(0 <= currentColumnIndex && currentColumnIndex < COLUMNS)) {
			gameResp.setStatus(INVALID_MOVE);
			gameResp.setMessage("Column Index between 0-6 only");
			return gameResp;
		}
		connect4.setColumnIndex(currentColumnIndex);
		connect4.setUniqueGameId(uniqueGameId);
		createBoard();

		for (Connect4 move : previousMoves) {
			getRowAndColumn(move);
			if (move.getPlayer() == 'R')
				red.append(move.getColumnIndex()).append(",");
			if (move.getPlayer() == 'Y')
				yellow.append(move.getColumnIndex()).append(",");
		}
		if (previousMoves.size() % 2 == 0) {
			red.append(currentColumnIndex);
		} else {
			yellow.append(currentColumnIndex);
		}
		gameResp.setrMoves(red.substring(0, red.length()-1).toString());
		gameResp.setyMoves(yellow.substring(0, red.length()-1).toString());

		boolean isValidMove = makeMove(currentColumnIndex, previousMoves.size(), connect4);

//		set2PlayersMoves(gameResp);

		if (isValidMove) {
			char winner = checkWin();
			gameResp.setStatus(VALID_MOVE);
			if (winner != '.') {
				String matchWon = winner == 'R' ? "RED wins" : "YELLOW Wins";
				gameResp.setMessage(matchWon);
			}
		} else {
			gameResp.setStatus(INVALID_MOVE);
			gameResp.setMessage("Column full at column index : " + currentColumnIndex);
		}
		gameResp.setConnect(connect4);
		return gameResp;
	}

	public boolean makeMove(int columnIndex, int lastIndex, Connect4 move) {
		boolean isCellEmpty = false;
		for (int row = 0; row < ROWS; row++) {
			if (board[row][columnIndex] == '.') {
				board[row][columnIndex] = lastIndex % 2 == 0 ? 'R' : 'Y';
				move.setPlayer(lastIndex % 2 == 0 ? 'R' : 'Y');
				move.setRowIndex(row);
				isCellEmpty = true;
				break;
			}
		}
		return isCellEmpty;
	}

	private void getRowAndColumn(Connect4 move) {
		board[move.getRowIndex()][move.getColumnIndex()] = move.getPlayer(); // Replacing (.)with the move
	}

	public void createBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = '.';
			}
		}
	}

	public static char checkWin() {
//		final int ROWS = board.length;
//		final int COLUMNS = board[0].length;
		final char EMPTY_SLOT = '.';
		for (int r = 0; r < ROWS; r++) { // iterate rows, bottom to top
			for (int c = 0; c < COLUMNS; c++) { // iterate columns, left to right
				char player = board[r][c];
				if (player == EMPTY_SLOT) {
					continue; // don't check empty slots
				}
				if (c + 3 < COLUMNS && player == board[r][c + 1] && // look right
						player == board[r][c + 2] && player == board[r][c + 3]) {
					return player;
				}
				if (r + 3 < ROWS) {
					if (player == board[r + 1][c] && // look up
							player == board[r + 2][c] && player == board[r + 3][c]) {
						return player;
					}
					if (c + 3 < COLUMNS && player == board[r + 1][c + 1] && // look up & right
							player == board[r + 2][c + 2] && player == board[r + 3][c + 3]) {
						return player;
					}
					if (c - 3 >= 0 && player == board[r + 1][c - 1] && // look up & left
							player == board[r + 2][c - 2] && player == board[r + 3][c - 3]) {
						return player;
					}
				}
			}
		}
		return EMPTY_SLOT; // no winner found
	}

}
