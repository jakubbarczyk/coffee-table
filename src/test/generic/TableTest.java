package generic;

import static org.junit.Assert.*;

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
}
