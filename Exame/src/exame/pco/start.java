package exame.pco;

public class start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Table t = new Table(5,5);
		t.put(1, 0, 1);
		t.put(1, 1, 2);
		t.put(1, 2, 3);
		t.put(1, 3, 4);
		t.put(1, 4, 5);
		System.out.println(t.getColumn(1));
}
}
