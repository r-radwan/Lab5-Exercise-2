
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.*; 
import javax.swing.*;



/**
 * This class demonstrate the application of ObjectInputStream and 
 * ObjectOutputStream at the server-side application.
 * 
 * How to run this program?
 * 
 * 1. Open Terminal
 * 2. Change the directory to /workspace-dad/demoObjectStream/bin
 * 3. Run -> java console.server.ServerSideApp
 * 
 *
 */

public class ServerSideApp {

	/**
	 * Main entry point of application
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			String WindowMsg = "[+] Starting server [+]";//Message to be shown
			JFrame frame = new JFrame("Server application");//Window
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set how to close
			JLabel textLabel = new JLabel("<html>"+WindowMsg+"</html>",SwingConstants.CENTER);//Window information as label       
			textLabel.setPreferredSize(new Dimension(300, 100));//Set the label size
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);//Display the window.       
			frame.setLocationRelativeTo(null);//how to place the elements within the window
			frame.pack();//Prepare all graphics elements together
			frame.setVisible(true);//Show the window

			// Port to receive and respond to request
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			//Showing information on window
			WindowMsg = WindowMsg + "<br/> [+] Ready for request [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
				
			// Accept client request for connection
			Socket socket = serverSocket.accept();
			
			// Create input stream to read object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Read object from stream and cast to Location
			ItemProduct itemProduct = (ItemProduct) objectIS.readObject();
			
			//Showing information on window
			WindowMsg = WindowMsg + "<br/>[!] Data received: " + itemProduct.getName()+"[!]";
			textLabel.setText("<html>" +WindowMsg+ "</html>");

			if(isAlphaNumeric(itemProduct.getName())) {
				//Showing information on window
				WindowMsg = WindowMsg + "<br/> [o] Item name is alphanumeric [o]";
		    	textLabel.setText("<html>" +WindowMsg+ "</html>");
			}
			else{
				//Showing information on window 
				WindowMsg = WindowMsg + "<br/> [x] Item name is NOT alphanumeric [x]";
		    	textLabel.setText("<html>" +WindowMsg+ "</html>");
			}
			// Process object
			itemProduct.setItemProductId(1);
			
			// Create output stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
			objectOS.writeObject(itemProduct);
			

			// Close all streams
			objectIS.close();
			objectOS.close();
			
			serverSocket.close();
			//Showing information on window
			WindowMsg = WindowMsg + "<br/>[+] Server closed [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Uses a simple regex function on the string to strictly determinate
	 * if all chars are alphanumeric
	 * 
	 * @param s The string to be checket for alphanumeric chars
	 * @return boolean
	 */
	public static boolean isAlphaNumeric(String s) {
		//search within the string 
		//elements from a to z (no capital)
		//elements from A to Z (capital)
		//elements from 0 to 9 (numbers)
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
 


}
