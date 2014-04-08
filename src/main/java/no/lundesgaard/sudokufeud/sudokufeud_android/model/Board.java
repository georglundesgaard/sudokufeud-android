package no.lundesgaard.sudokufeud.sudokufeud_android.model;

import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Board implements Serializable {

	private Field board[];

	public Board() {
		board = new Field[Constants.NUMBER_OF_CELLS];
	}

	public void populateIntitialNumbers(List<Integer> boardNumbers) {
		if (boardNumbers.size() != Constants.NUMBER_OF_CELLS)
			throw new IllegalArgumentException("populateIntitialNumbers kallt med feil antall (" + boardNumbers.size()
					+")");

		for (int y = 0; y < Constants.BOARD_WIDTH; y++)
			for (int x = 0; x < Constants.BOARD_WIDTH; x++) {
				final int location = y * Constants.BOARD_WIDTH + x;
				if (boardNumbers.get(location) != null) {
					board[location] = new Field(boardNumbers.get(location),true, null);
				}
			}
	}

	public Field getCell(int x, int y) {
		if (x < 0 || x >= Constants.BOARD_WIDTH ||
				y < 0 || y >= Constants.BOARD_HEIGHT)
			throw new IllegalArgumentException("Illegal position (" + x + "," + y + ")");

		return board[x + y * Constants.BOARD_WIDTH];
	}

	public Integer getNumber(int x, int y) {
		final Field field = getCell(x,y);
		if (field != null)
			return field.getValue();

		return null;
	}
/*
	public void storeNumber(int x, int y, Integer value, Integer id) {
		validateXY(x,y);

		final Cell cell = getCell(x, y);
		if (cell != null) {
			if (cell.isLocked())
				throw new IllegalArgumentException("Cell (" + x + "," + y + ") is read only");
			cell.setValue(value);
		} else
			board.set(x + y * Constants.BOARD_WIDTH, new Cell(value, false, id));
	}
*/
	private void storeCell(int x, int y, Field newcell) {
		validateXY(x,y);
		final Field field = getCell(x, y);
		if (field != null) {
			if (field.isLocked())
				throw new IllegalArgumentException("Cell (" + x + "," + y + ") is read only");
		}

		board[x + y * Constants.BOARD_WIDTH] = newcell;
	}

	private void validateXY(int x, int y) {
		if (x < 0 || x >= Constants.BOARD_WIDTH ||
				y < 0 || y >= Constants.BOARD_HEIGHT)
			throw new IllegalArgumentException("Illegal position (" + x + "," + y + ")");
    }

	public Field getField(int square, int position) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		return getCell(x,y);
	}

	public Integer getFieldNumber (int square, int position) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		return getNumber(x, y);
	}

	public void storeFieldCell(int square, int position, Field field) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		storeCell(x,y, field);
	}
/*
	public void storeFieldNumber(int square, int position, Integer value, Integer id) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		storeNumber(x,y, value, id);
	}
*/
	public Field findCellById(int id) {
		for (Field field : board)
			if (field != null && field.getId() != null && field.getId() == id)
				return field;

		return null;
	}

	public void freeCell(Field fieldToFree) {
		int pos = getIndex(fieldToFree);
		if (pos >= 0)
			board[pos] = null;
		else
			throw new IllegalArgumentException("Cell (" + fieldToFree + ") is unknown");
	}

	public int getSquare(Field field) {
		int pos = getIndex(field);
		if (pos >= 0) {
			int row = pos / Constants.BOARD_WIDTH;
			int col = pos - row * Constants.BOARD_WIDTH;
			return ((row / 3) * 3 + col / 3);
		} else {
			return -1;
		}
	}

	private int getIndex(Field field) {
		for (int i = 0; i < board.length; i++)
			if (field.equals(board[i])) {
				return i;
			}
		return -1;
	}

	@Override
	public String toString() {
		return "Board{" +
				"board=" + Arrays.toString(board) +
				'}';
	}

}
