package generic;

/**
 * @author Jakub Barczyk
 */
final class Cell<T> {
	private T value;
	
	/**
	 * @return this cell's value
	 */
	public final T getValue() {
		return value;
	}
	
	/**
	 * @param value the value to be set
	 */
	public final void setValue(T value) {
		this.value = value;
	}

	/**
	 * @param value the initial value that the cell will hold
	 */
	public Cell(T value) {
		this.value = value;
	}
}
