package exame.pco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table<E> implements ITable<E> {

	private int row;
	private int column;
	E[][] table2;
	private int elements;
	private List<ArrayList<E>> table;
	
	public Table(int i, int j) {
		this.row = i;
		this.column = j;
		this.elements = row*column;
		//this.table2 = (E[][])new Object[i][j];
		this.table = new ArrayList<ArrayList<E>>();
		for(int lineH=0; lineH<i; lineH++) {
			table.add(new ArrayList<E>());
			for(int lineV=0; lineV<j; lineV++) {
				table.get(lineH).add(null);
			}
		}
	
		/**for(int lineH=0; lineH<i; lineH++) {
			for(int lineV=0; lineV<j; lineV++) {
				table2[lineH][lineV] = null;
			}
		}*/
		//System.out.println(table);
	}
	
	public E get(int i, int j) {
		//return table[i][j];
		return table.get(i).get(j);
	}

	public void put(int i, int j, E e) {
		//table2[i][j] = e;
		table.get(i).set(j, e);
	}

	public int rows() {
		return row;
	}

	public int columns() {
		// TODO Auto-generated method stub
		return column;
	}

	public List<E> getRow(int i) {
		List<E> newList = new ArrayList<E>();
		/**for(E elem : table2[i]) {
			newList.add(elem);
		}*/
		
		for(E elem : table.get(i)) {
			newList.add(elem);
		}
		return newList;
	}

	public List<E> getColumn(int j) {
		List<E> newList = new ArrayList<E>();
		/**for(E[] elem : table2) {
			newList.add(elem[j]);
		}*/
		
		for(int x=0; x<row; x++) {
			newList.add(table.get(x).get(j));
		}
		
		return newList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + elements;
		result = prime * result + row;
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		result = prime * result + Arrays.deepHashCode(table2);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (column != other.column)
			return false;
		if (elements != other.elements)
			return false;
		if (row != other.row)
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		if (!Arrays.deepEquals(table2, other.table2))
			return false;
		return true;
	}
}
