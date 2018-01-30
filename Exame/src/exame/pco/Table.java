package exame.pco;

import java.util.ArrayList;
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
}
