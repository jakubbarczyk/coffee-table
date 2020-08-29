package generic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jakub Barczyk
 */
public class TableTest {
	Table<String> table;
	
	@Before 
	public void method() {
		table = new Table<String>();
	}

	@Test
	public void shouldNotBeNull() {
		assertNotNull(table);
	}
	
	@Test
	public void shouldAddColumn() {
		table.addColumn("Test");
		assertEquals(1, table.getColumnsCount());
	}
	
	@Test
	public void shouldRemoveColumn() {
		table.addColumn("Test").removeColumn(0);
		assertEquals(0, table.getColumnsCount());
	}
	
	@Test
	public void shouldAddRow() {
		table.addRow();
		assertEquals(1, table.getRowsCount());
	}
	
	@Test
	public void shouldRemoveRow() {
		table.addRow().removeRow(0);
		assertEquals(0, table.getRowsCount());
	}
	
	@Test
	public void shouldGetCellValue() {
		String cellValue = "123";
		table.addColumn("Test").addRow(cellValue);
		assertEquals(cellValue, table.getCellValue(0, 0));
	}
	
	@Test
	public void shouldSetCellValue() {
		String cellValue = "123";
		table.addColumn("Test").addRow().setCellValue(0, 0, cellValue);
		assertEquals(cellValue, table.getCellValue(0, 0));
	}
	@Test
	public void shouldSortColumn() {
		List<String> sorted = new ArrayList<String>();
		sorted.add("1");
		sorted.add("3");
		sorted.add("5");
		table.addColumn("testSort");
		table.addRow().setCellValue(0, 0, "5");
		table.addRow().setCellValue(0, 1, "3");
		table.addRow().setCellValue(0, 2, "1");
		SortKey sortkey = new SortKey(0);
		List<SortKey<String>> t=new ArrayList<SortKey<String>>();
		t.add(sortkey);
		table.sort(t);
		assertEquals(sorted, table.getColumnValues(0));
		table.clear();
	}
	@Test
	public void shouldSort2Column() {
		List<String> sorted = new ArrayList<String>();
		sorted.add("1");
		sorted.add("5");
		sorted.add("5");
		
		List<String> sorted2 = new ArrayList<String>();
		sorted2.add("6");
		sorted2.add("3");
		sorted2.add("4");
		
		List<String> rows3 = new ArrayList<String>();
		rows3.add("5");
		rows3.add("4");
		table.addColumn("colum1");
		table.addColumn("colum2");
		table.addRow().setCellValue(0, 0, "5").setCellValue(1, 0, "4");
		table.addRow().setCellValue(0, 1, "5").setCellValue(1, 1, "3");
		table.addRow().setCellValue(0, 2, "1").setCellValue(1, 2, "6");
		SortKey sortkey = new SortKey(0);
		SortKey sortkey2 = new SortKey(1);
		List<SortKey<String>> t=new ArrayList<SortKey<String>>();
		t.add(sortkey);
		t.add(sortkey2);
		table.sort(t);
		assertEquals(sorted, table.getColumnValues(0));
//		assertEquals(sorted2, table.getColumnValues(1));
		assertEquals(rows3, table.getRowValues(2));
		table.clear();
	}
	@Test
	public void shouldSort2ColumnDESC() {
		List<String> sorted = new ArrayList<String>();
		sorted.add("1");
		sorted.add("5");
		sorted.add("5");
		
		List<String> sorted2 = new ArrayList<String>();
		sorted2.add("6");
		sorted2.add("3");
		sorted2.add("4");
		
		List<String> rows3 = new ArrayList<String>();
		rows3.add("5");
		rows3.add("3");
		table.addColumn("colum1");
		table.addColumn("colum2");
		table.addRow().setCellValue(0, 0, "5").setCellValue(1, 0, "4");
		table.addRow().setCellValue(0, 1, "5").setCellValue(1, 1, "3");
		table.addRow().setCellValue(0, 2, "1").setCellValue(1, 2, "6");
		SortKey sortkey = new SortKey(0);
		SortKey sortkey2 = new SortKey(1,false);
		List<SortKey<String>> t=new ArrayList<SortKey<String>>();
		t.add(sortkey);
		t.add(sortkey2);
		table.sort(t);
		assertEquals(sorted, table.getColumnValues(0));
//		assertEquals(sorted2, table.getColumnValues(1));
		assertEquals(rows3, table.getRowValues(2));
		table.exportToCsv("test");
		table.clear();
	}
	
	@Test
	public void shouldimportFromCsv() {
		table.clear();
		table.importFromCsv("test");
		table.print();
		List<String> rows3 = new ArrayList<String>();
		rows3.add("5");
		rows3.add("3");
		assertEquals(rows3, table.getRowValues(2));
	}
}
