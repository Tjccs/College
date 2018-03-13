import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class myClient2 {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Socket echoSocket = new Socket("127.0.0.1", 23456);
		
		ObjectInputStream in = new ObjectInputStream(echoSocket.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
		
		
		String userInput = "AH";
		String userInput2 = "AAH";
		
		out.writeObject(userInput);
		out.writeObject(userInput2);
		
		Boolean serverAnswer = (Boolean) in.readObject();
		
		
		// ler ficheiro
		// usar read(), fileInputStream
		// para o buffer
		// file.read(buffer,0,1024)
		// escrever para o socket
			 
		File f1 = new File("aa.txt");
		 FileInputStream fis;
		 BufferedInputStream bis;
		 BufferedOutputStream out2;
		 byte[] buffer = new byte[1024];
		 try {
		    fis = new FileInputStream(f1);
		    bis = new BufferedInputStream(fis);
		    out2 = new BufferedOutputStream(echoSocket.getOutputStream());
		    int count;
		    while ((count = bis.read(buffer)) > 0) {
		        out2.write(buffer, 0, count);
		    }
		    out2.close();
		    fis.close();
		    bis.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
}
