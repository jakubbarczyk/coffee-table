package generic;

import java.util.Comparator;

public class MyComparator implements Comparator<Cell<String>>{

	@Override
	public int compare(Cell<String> o1, Cell<String> o2) {
		// TODO Auto-generated method stub
		return o1.getValue().compareTo(o2.getValue());
	}

}
