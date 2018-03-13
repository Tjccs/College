import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class myClient {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Socket echoSocket = new Socket("127.0.0.1", 23456);
		
		ObjectInputStream in = new ObjectInputStream(echoSocket.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
		
		
		String userInput = "AH";
		String userInput2 = "AAH";
		
		out.writeObject(userInput);
		out.writeObject(userInput2);
		
		Boolean serverAnswer = (Boolean) in.readObject();
		//String fromServer = (String) in.readObject();
		System.out.println(serverAnswer.toString());
		out.close();
		in.close();
		echoSocket.close();
	}
	
}
