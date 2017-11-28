package fcul.pco.dentalclinic.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fcul.pco.dentalclinic.domain.Doctor;
import fcul.pco.dentalclinic.main.ApplicationConfiguration;

public class DoctorCatalog {

	
	public static void save(Map<Integer, Doctor> doctors) throws IOException{
		StringBuilder sbf = new StringBuilder();
        
        for (Doctor d : doctors.values()){
        	sbf.append(d.toString() + "\n");
        }
        
        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(ApplicationConfiguration.ROOT_DIRECTORY,ApplicationConfiguration.DOCTOR_CATALOG_FILENAME+".csv")));
        bwr.write(sbf.toString());
        bwr.flush();
        bwr.close();
    }

	public static Map<Integer,Doctor> load() throws FileNotFoundException {
        
    	Map<Integer,Doctor> dLoad = new TreeMap<Integer,Doctor>();
		Scanner scanner = new Scanner(new File(ApplicationConfiguration.ROOT_DIRECTORY+ApplicationConfiguration.DOCTOR_CATALOG_FILENAME+".csv"));
		scanner.useDelimiter(",");
	
		while (scanner.hasNext()) {
			String[] element = scanner.nextLine().split(",");
			//String name = element[0];
			//int id = Integer.parseInt(element[1]);
			Doctor d = Doctor.fromString(element[0]+","+element[1]);
			dLoad.put(Integer.parseInt(element[1]),d);
		}
		scanner.close();
		return dLoad;
	}

}

