package exame.pco;

import java.util.List;

public interface ITable<E> {
	
	E get(int i, int j);
	void put(int i, int j, E e);
	int rows();
	int columns();
	List<E> getRow(int i);
	List<E> getColumn(int j);

}
