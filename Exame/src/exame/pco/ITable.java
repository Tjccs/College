package exame.pco;

import java.util.List;

public interface ITable<E> {
	
	public E get(int i, int j);
	public void put(int i, int j, E e);
	public int rows();
	public int columns();
	public List<E> getRow(int i);
	public List<E> getColumn(int j);

}
