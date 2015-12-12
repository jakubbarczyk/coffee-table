package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jakub Barczyk
 */
final class Column<T> {
	private String header;
	private List<Cell<T>> cells;

	/**
	 * @return this column's header
	 */
	public final String getHeader() {
		return header;
	}

	/**
	 * @return this column's cells
	 */
	public final List<Cell<T>> getCells() {
		return cells;
	}

	/**
	 * @param rows the rows to be processed by the column
	 * @param columnDef optional values to fill the column's cells
	 */
	@SafeVarargs
	private final void populateCells(List<Row<T>> rows, T... columnDef) {
		Cell<T> newCell = null;

		for (int i = 0, j = rows.size(); i < j; ++i) {
			if (columnDef.length > i)
				newCell = new Cell<T>(columnDef[i]);
			else
				newCell = new Cell<T>(null);

			cells.add(newCell);
			
			List<Cell<T>> rowCells = rows.get(i).getCells();
			
			if (rowCells.size() > 0)
				rowCells.add(newCell);
		}
	}

	/**
	 * @param rows the rows to be processed by the column
	 * @param header the header of the column
	 * @param columnDef optional values to fill the column's cells
	 */
	@SafeVarargs
	public Column(List<Row<T>> rows, String header, T... columnDef) {
		this.header = header;
		cells = new ArrayList<Cell<T>>();
		populateCells(rows, columnDef);
	}
}
