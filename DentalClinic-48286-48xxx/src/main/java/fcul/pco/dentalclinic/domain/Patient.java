package fcul.pco.dentalclinic.domain;

public class Patient {
	
	private int snsNumber;
	private String name;
	
	/**
	 * @param snsNumber
	 * @param name
	 */
	public Patient(int snsNumber, String name) {

		this.snsNumber = snsNumber;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return snsNumber+","+name;
	}
	
	public static Patient fromString(String s) {
		String[] elements = s.split(",");
    	Patient p = new Patient(Integer.parseInt(elements[0]), elements[1]); 
    	return p;
	}

	public int getSns() {
		
		return snsNumber;
	}

	public String getName() {
		return name;
	}

}
