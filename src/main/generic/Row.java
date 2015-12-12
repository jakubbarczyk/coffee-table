package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jakub Barczyk
 */
final class Row<T> {
	private List<Cell<T>> cells;

	/**
	 * @return this row's cells
	 */
	public final List<Cell<T>> getCells() {
		return cells;
	}
	
	/**
	 * @param columns the columns to be processed by the row
	 * @param rowDef optional values to fill the column's cells
	 */
	@SafeVarargs
	private final void populateCells(List<Column<T>> columns, T... rowDef) {
		if (columns.size() > 0) {
			Cell<T> newCell = null;

			for (int i = 0, j = columns.size(); i < j; ++i) {
				if (rowDef.length > i)
					newCell = new Cell<T>(rowDef[i]);
				else
					newCell = new Cell<T>(null);

				cells.add(newCell);

				List<Cell<T>> columnCells = columns.get(i).getCells();
				columnCells.add(newCell);
			}
		}
	}

	/**
	 * @param columns the columns to be processed by the row
	 * @param rowDef optional values to fill the row's cells
	 */
	@SafeVarargs
	public Row(List<Column<T>> columns, T... rowDef) {
		cells = new ArrayList<Cell<T>>();
		populateCells(columns, rowDef);
	}
}
