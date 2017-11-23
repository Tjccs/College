package fcul.pco.dentalclinic.domain;

public class Doctor {
	
	private String name;
	private int id;
	private Agenda agenda;
	
	public Doctor(String name, int id) {
		this.name = name;
		this.id = id;
		Agenda agenda;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	@Override
	public String toString() {
		return this.name+","+this.id;
	}
	
	public static Doctor fromString(String s) {
		String[] elements = s.split(",");
    	Doctor d = new Doctor(elements[0],Integer.parseInt(elements[1])); 
    	return d;
		
		
	}
}
