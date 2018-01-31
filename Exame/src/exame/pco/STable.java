package exame.pco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class STable<E extends Table<E> & Comparable<E>>{

	private List<E> list;
	public STable() {
		list = new ArrayList<>();
	}
	
}
