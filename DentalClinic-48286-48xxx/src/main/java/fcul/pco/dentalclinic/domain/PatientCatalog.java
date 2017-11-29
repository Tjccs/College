package fcul.pco.dentalclinic.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatientCatalog {
	
	Map<Integer,Patient> patientCatalog;
	
	static PatientCatalog instance;
	
	private PatientCatalog() {
		patientCatalog = new TreeMap<Integer,Patient>();
	}
	
	public static PatientCatalog getInstance() {
		if(instance == null) {
			instance = new PatientCatalog();
		}
		return instance;
	}

	public void addPatient(Patient p) {
		patientCatalog.put(p.getSns(), p);
	}

	public void load() throws IOException {
		patientCatalog = fcul.pco.dentalclinic.persistence.PatientCatalog.load();
	}

	public void save() throws IOException {
		fcul.pco.dentalclinic.persistence.PatientCatalog.save(patientCatalog);
	}
	
	public Patient getPatientById(int id) {
		return patientCatalog.get(id);
	}

	@Override
	public String toString() {
		List<List<String>> table = new ArrayList<>();
		for(Patient p : patientCatalog.values()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(String.valueOf(p.getSns()));
			row.add(p.getName());
			table.add(row);
		}
		return Utils.tableToString(table);
	}
	
}
