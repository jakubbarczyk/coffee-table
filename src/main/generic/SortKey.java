package generic;

public class SortKey<T> {
int index;
boolean isASC=true;//默认升序，否则就是降序
//Comparator<? super Cell<T>> c;


public SortKey(int index) {
	super();
	this.index = index;

}

public SortKey(int index, boolean isASC) {
	super();
	this.index = index;
	this.isASC = isASC;
}

//public Comparator<? super Cell<T>> getC() {
//	return c;
//}
//public void setC(Comparator<? super Cell<T>> c) {
//	this.c = c;
//}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public boolean isASC() {
	return isASC;
}
public void setASC(boolean isASC) {
	this.isASC = isASC;
}

}
