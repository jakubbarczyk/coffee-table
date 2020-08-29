package generic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Barczyk
 */
public final class Table<T> {
	private List<Column<T>> columns;
	private List<Row<T>> rows;
	
	public final void print(){
		StringBuffer result = new StringBuffer();
		//表头
		String header=columns.stream().filter(e->e.getHeader()!=null).map(e->e.getHeader()+",").collect(Collectors.joining());
		result.append(header.substring(0, header.length()-1));
		result.append("\n");
		//行数据
		for(Row<T> row:rows){
			String line=row.getCells().stream().map(c->c.getValue()+",").collect(Collectors.joining());
			result.append(line.substring(0, line.length()-1));
			result.append("\n");
		}
		System.out.println(result.toString());
	}

	public final void clear(){
		for(int i=0;i<rows.size();i++){
			removeRow(i);
		}
		for(int i=0;i<columns.size();i++){
			removeColumn(i);
		}
	}
	public final void importFromCsv(String fileName){
		File inFile = new File("D://"+fileName+".csv"); // 读取的CSV文件

		try {
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			String line="";
			//处理header
			line=reader.readLine();
			String[] header=line.split(",");
			for(int i=0;i<header.length;i++){
				addColumn(header[i]);
			}
			
			//处理rows
			int rownum=0;
			while((line=reader.readLine())!=null){
				addRow();
				String[] row=line.split(",");
				for(int j=0;j<row.length;j++){
					setCellValue(j, rownum, (T)row[j]);
				}
				rownum++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public final void exportToCsv(String fileName){
		StringBuffer result = new StringBuffer();
		//表头
		String header=columns.stream().filter(e->e.getHeader()!=null).map(e->e.getHeader()+",").collect(Collectors.joining());
		result.append(header.substring(0, header.length()-1));
		result.append("\n");
		//行数据
		for(Row<T> row:rows){
			String line=row.getCells().stream().map(c->c.getValue()+",").collect(Collectors.joining());
			result.append(line.substring(0, line.length()-1));
			result.append("\n");
		}

		try {
			File outFile = new File("D://"+fileName+".csv");//写出的CSV文件
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			writer.write(result.toString());
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public final Table<T> sort(List<SortKey<T>> sortKeys){
		print();
		rows.sort((o1,o2)->{
			for(SortKey<T> sortKey:sortKeys){
				int result=o1.getCells().get(sortKey.getIndex()).getValue().toString().compareTo(o2.getCells().get(sortKey.getIndex()).getValue().toString());
				if(result==0){
				  continue;	
				}
				else if(sortKey.isASC){
					return result;
				}
				else {
					return  -result;
				}
			}
			return 0;
		});
		copyRowsToColumns();
		System.out.println("----------------------");
		print();
		return this;
	}
	
	public final void copyRowsToColumns(){
		for(int i=0;i<columns.size();i++){
			columns.get(i).getCells().clear();
			for(int j=0;j<rows.size();j++){
				columns.get(i).getCells().add(getRowsCell(i,j));
			}
		}
	}
	
	public final void copyColumnsToRows(){
		for(int i=0;i<rows.size();i++){
			rows.get(i).getCells().clear();
			for(int j=0;j<columns.size();j++){
				rows.get(i).getCells().add(getCell(j,i));
			}
		}
	}
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
	 * @param index the index of the column to get values from
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
	 * @param index the index of the column to be removed
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
	 * @param index the index of the row to get values from
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
	 * @param index the index of the row to be removed
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
	 * @param column the index of the column to get the value from
	 * @param row the index of the row to get the value from
	 * @return the value of the specified cell
	 */
	public final T getCellValue(int column, int row) {
		return columns.get(column).getCells().get(row).getValue();
	}
	
	public final T getRowsCellValue(int column, int row) {
		return rows.get(row).getCells().get(column).getValue();
	}
	
	public final Cell<T> getRowsCell(int column, int row) {
		return rows.get(row).getCells().get(column);
	}
	
	public final Cell<T> getCell(int column, int row) {
		return columns.get(column).getCells().get(row);
	}
	
	/**
	 * @param column the index of the column to set the value on
	 * @param row the index of the row to set the value on
	 * @param value the value to be set
	 * @return this table's instance
	 */
	public final Table<T> setCellValue(int column, int row, T value) {
		columns.get(column).getCells().get(row).setValue(value);
		
		return this;
	}
	
	public final Table<T> setRowsCellValue(int column, int row, T value) {
		rows.get(row).getCells().get(column).setValue(value);
		
		return this;
	}

	public Table() {
		columns = new ArrayList<Column<T>>();
		rows = new ArrayList<Row<T>>();
	}
}
