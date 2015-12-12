package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jakub Barczyk
 */
public final class Table<T> {
	private List<Column<T>> columns;
	private List<Row<T>> rows;

	/**
	 * @return number of columns in the table
	 */
	public final int getColumnsCount() {
		return this.columns.size();
	}
	
	/**
	 * @param index index of the column to get the header of
	 * @return the header of the column
	 */
	public final String getColumnHeader(int index) {
		String header = columns.get(index).getHeader();
		
		return header;
	}
	
	/**
	 * @param index index of the column to get values from
	 * @return array of the column's values
	 */
	public final List<T> getColumnValues(int index) {
		List<Cell<T>> columnCells = this.columns.get(index).getCells();
		List<T> columnValues = new ArrayList<T>();

		for (Cell<T> cell : columnCells)
			columnValues.add(cell.getValue());

		return columnValues;
	}
	
	/**
	 * @param header the header of the column
	 * @param columnDef optional values to fill the column's cells
	 * @return this table's instance
	 */
	@SafeVarargs
	public final Table<T> addColumn(String header, T... columnDef) {
		columns.add(new Column<T>(rows, header, columnDef));
		
		return this;
	}

	/**
	 * @param index index of the column to be removed
	 * @return this table's instance
	 */
	public final Table<T> removeColumn(int index) {
		if (columns.get(index) != null) {
			columns.remove(index);
			
			for (Row<T> row : rows)
				row.getCells().remove(index);
		}
		
		return this;
	}
	
	/**
	 * @return number of rows in the table
	 */
	public final int getRowsCount() {
		return rows.size();
	}

	/**
	 * @param index index of the row to get values from
	 * @return array of the row's values
	 */
	public final List<T> getRowValues(int index) {
		List<Cell<T>> rowCells = rows.get(index).getCells();
		List<T> rowValues = new ArrayList<T>();

		for (Cell<T> cell : rowCells)
			rowValues.add(cell.getValue());

		return rowValues;
	}

	/**
	 * @param rowDef optional values to fill the row's cells
	 * @return this table's instance
	 */
	@SafeVarargs
	public final Table<T> addRow(T... rowDef) {
		rows.add(new Row<T>(columns, rowDef));
		
		return this;
	}

	/**
	 * @param index index of the row to be removed
	 * @return this table's instance
	 */
	public final Table<T> removeRow(int index) {		
		if (rows.get(index) != null) {
			rows.remove(index);
			
			for (Column<T> column : columns)
				column.getCells().remove(index);
		}
		
		return this;
	}

	/**
	 * @param column index of the column to get the value from
	 * @param row index of the row to get the value from
	 * @return the value of the specified cell
	 */
	public final T getCellValue(int column, int row) {
		return columns.get(column).getCells().get(row).getValue();
	}
	
	/**
	 * @param column index of the column to set the value on
	 * @param row index of the row to set the value on
	 * @param value the value to be set
	 * @return this table's instance
	 */
	public final Table<T> setCellValue(int column, int row, T value) {
		columns.get(column).getCells().get(row).setValue(value);
		
		return this;
	}

	public Table() {
		columns = new ArrayList<Column<T>>();
		rows = new ArrayList<Row<T>>();
	}
}
