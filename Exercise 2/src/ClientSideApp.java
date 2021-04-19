

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
 * 3. Run -> java console.server.ClientSideApp
 * 
 *
 */

public class ClientSideApp {

	/**
	 * Main entry point of program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Request data
		ItemProduct itemProduct = new ItemProduct();
		
		itemProduct.setName("Lab5/");
		itemProduct.setPrice(5);
		String WindowMsg = "[+] Starting client [+]"; //message to be shown

		try {

			
			JFrame frame = new JFrame("Client application"); //Window
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set how to close
			JLabel textLabel = new JLabel("<html>"+WindowMsg+"</html>",SwingConstants.CENTER); //Window information as label
			textLabel.setPreferredSize(new Dimension(300, 100)); //Set the label size
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);//Display the window. 
			frame.setLocationRelativeTo(null); //how to place the elements within the window
			frame.pack(); //Prepare all graphics elements together
			frame.setVisible(true);//Show the window

			// Data to establish connection to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			//Showing information on window
			WindowMsg = WindowMsg + "<br/> [!] Send object to server: " + itemProduct +"[!]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");

			// Send request to server
			objectOS.writeObject(itemProduct);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream and display details
			itemProduct = (ItemProduct) objectIS.readObject();
			
			//Showing information on window
			WindowMsg = WindowMsg + "<br/>[o] Id for " + itemProduct.getName() + " is " + itemProduct.getItemProductId()+ " with price: " + String.valueOf(itemProduct.getPrice()) + "[o]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
			socket.close();

			WindowMsg = WindowMsg + "<br/>[+]Client closed [+]";
			textLabel.setText("<html>" +WindowMsg+ "</html>");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Showing information on window
		
	}

}
