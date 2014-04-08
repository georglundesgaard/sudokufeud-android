package no.lundesgaard.sudokufeud.sudokufeud_android.model;

import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Board implements Serializable {

	private Cell board[];

	public Board() {
		board = new Cell[Constants.NUMBER_OF_CELLS];
	}

	public void populateIntitialNumbers(List<Integer> boardNumbers) {
		if (boardNumbers.size() != Constants.NUMBER_OF_CELLS)
			throw new IllegalArgumentException("populateIntitialNumbers kallt med feil antall (" + boardNumbers.size()
					+")");

		for (int y = 0; y < Constants.BOARD_WIDTH; y++)
			for (int x = 0; x < Constants.BOARD_WIDTH; x++) {
				final int location = y * Constants.BOARD_WIDTH + x;
				if (boardNumbers.get(location) != null) {
					board[location] = new Cell(boardNumbers.get(location),true, null);
				}
			}
	}

	public Cell getCell(int x, int y) {
		if (x < 0 || x >= Constants.BOARD_WIDTH ||
				y < 0 || y >= Constants.BOARD_HEIGHT)
			throw new IllegalArgumentException("Illegal position (" + x + "," + y + ")");

		return board[x + y * Constants.BOARD_WIDTH];
	}

	public Integer getNumber(int x, int y) {
		final Cell cell = getCell(x,y);
		if (cell != null)
			return cell.getValue();

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
	private void storeCell(int x, int y, Cell newcell) {
		validateXY(x,y);
		final Cell cell = getCell(x, y);
		if (cell != null) {
			if (cell.isLocked())
				throw new IllegalArgumentException("Cell (" + x + "," + y + ") is read only");
		}

		board[x + y * Constants.BOARD_WIDTH] = newcell;
	}

	private void validateXY(int x, int y) {
		if (x < 0 || x >= Constants.BOARD_WIDTH ||
				y < 0 || y >= Constants.BOARD_HEIGHT)
			throw new IllegalArgumentException("Illegal position (" + x + "," + y + ")");
    }

	public Cell getFieldCell (int square, int position) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		return getCell(x,y);
	}

	public Integer getFieldNumber (int square, int position) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		return getNumber(x, y);
	}

	public void storeFieldCell(int square, int position, Cell cell) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		storeCell(x,y,cell);
	}
/*
	public void storeFieldNumber(int square, int position, Integer value, Integer id) {
		int x = (square % 3) * 3 + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		storeNumber(x,y, value, id);
	}
*/
	public Cell findCellById(int id) {
		for (Cell cell : board)
			if (cell != null && cell.getId() != null && cell.getId() == id)
				return cell;

		return null;
	}

	public void freeCell(Cell cellToFree) {
		int pos = getIndex(cellToFree);
		if (pos >= 0)
			board[pos] = null;
		else
			throw new IllegalArgumentException("Cell (" + cellToFree + ") is unknown");
	}

	public int getField(Cell cell) {
		int pos = getIndex(cell);
		if (pos >= 0) {
			int row = pos / Constants.BOARD_WIDTH;
			int col = pos - row * Constants.BOARD_WIDTH;
			return ((row / 3) * 3 + col / 3);
		} else {
			return -1;
		}
	}

	private int getIndex(Cell cell) {
		for (int i = 0; i < board.length; i++)
			if (cell.equals(board[i])) {
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
