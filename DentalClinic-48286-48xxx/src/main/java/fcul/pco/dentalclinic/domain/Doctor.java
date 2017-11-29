package fcul.pco.dentalclinic.domain;

public class Doctor {
	
	private String name;
	private int id;
	private Agenda agenda;
	
	public Doctor(int id, String name, Agenda agenda) {
		this.id = id;
		this.name = name;
		this.agenda = agenda;
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
		return id+","+name;
	}
	
	public static Doctor fromString(String s) {
		String[] elements = s.split(",");
		//Duvida
    	Doctor d = new Doctor(Integer.parseInt(elements[0]), elements[1], new Agenda()); 
    	return d;
		
		
	}
}
