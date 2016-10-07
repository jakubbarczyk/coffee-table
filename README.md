# Coffee Table
Coffee Table is a lightweight tabular data structure for Java. It comes handy when you need to organize data of a certain type into columns and rows while retaining full control over individual table cells. With Coffee Table you can easily:

  - add/remove rows and columns
  - get/set data stored in individual cells
  - chain API methods for improved readability
  - save oceans of your precious time

### API
##### addColumn(header [, columnCell ...])
Adds a headered column to a table. Column cell values may be passed in. Their references will be mapped onto any existing row cells.

##### addRow([rowCell ...])
Adds a row to a table. Row cell values may be passed in. Their references will be mapped onto any existing column cells.

##### getCellValue(columnIndex, rowIndex)
Returns the value of a cell at the specified column and row index.

##### getColumnsCount()
Returns the number of columns in a table.

##### getColumnHeader(columnIndex)
Return the header of a column at the specified index.

##### getColumnValues(columnIndex)
Returns a list of cell values of a column at the specified index.

##### getRowsCount()
Returns the number of rows in a table.

##### getRowValues(rowIndex)
Returns a list of cell values of a row at the specified index.

##### removeColumn(columnIndex)
Removes a column at the specified index.

##### removeRow(rowIndex)
Removes a row at the specified index.

##### setCellValue(columnIndex, rowIndex, cellValue)
Sets the value of a cell at the specified column and row index.

### Chaining
```java
Table<String> myTable = new Table<String>();

myTable.addColumn("First Name").addColumn("Last Name").addColumn("City");

myTable
    .addRow("John", "Smith", "New York")
    .addRow("Robert", "Johnson", "Boston");
	
myTable.setCellValue(0, 1, "Christopher").removeColumn(2);
```

### Version
1.2.8

### License
[MIT](http://ilee.mit-license.org)
