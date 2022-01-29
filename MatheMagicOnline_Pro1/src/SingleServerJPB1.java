
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;


public class SingleServerJPB1 {
    
    private static final int SERVER_PORT = 8765;
    
    public static void main(String[] args) {
        
       //open file and read data to hashmap
       HashMap<String, String> loginsInfo = new HashMap<String, String>();
       String[] currencies;
        try {
            File myObj = new File("logins.txt");
            Scanner myReader = new Scanner(myObj);  
            
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              currencies = data.split(" ");
              loginsInfo.put(currencies[0],currencies[1]);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        System.out.println(loginsInfo);   
        createCommunicationLoop(loginsInfo);
        
    }//end main
    
 
    public static void createCommunicationLoop(HashMap<String, String> loginsInfo) {
        boolean authorizedUser=false;
        String[] currencies;
        
        try {
            //create server socket
            ServerSocket serverSocket = 
                    new ServerSocket(SERVER_PORT);
            
            System.out.println("Server started at " +
                    new Date() + "\n");
            //listen for a connection
            //using a regular *client* socket
            Socket socket = serverSocket.accept();
            
            //now, prepare to send and receive data
            //on output streams
            DataInputStream inputFromClient = 
                    new DataInputStream(socket.getInputStream());
            
            DataOutputStream outputToClient =
                    new DataOutputStream(socket.getOutputStream());
            
            //server loop listening for the client 
            //and responding
            while(true) 
            {
                //login first
                String strReceived= inputFromClient.readUTF();

                if (authorizedUser==false)
                {
                    if(strReceived.contains("LOGIN"))
                    {
                        currencies = strReceived.split(" ");
//                        System.out.println(currencies[1]);
//                        System.out.println(currencies[2]);

                        if(loginsInfo.containsKey(currencies[1]))
                        {
                            if(loginsInfo.get(currencies[1]).equals(currencies[2])){
                               outputToClient.writeUTF("You can access the server"); 
                               authorizedUser=true;
                            }
                        }
                        else{
                            outputToClient.writeUTF("Your login info is incorrect"); 
                        }
                       
                    }
                    else{
                          outputToClient.writeUTF("You need to sign in first");  
                    }

                }
                
                else{
                    //solve command
                    if(strReceived.equalsIgnoreCase("solve")) {
                    System.out.println("Sending solve to client");
                    outputToClient.writeUTF("hello client!");
                    }
                    else if(strReceived.equalsIgnoreCase("quit")) {
                        System.out.println("Shutting down server...");
                        outputToClient.writeUTF("Shutting down server...");
                        serverSocket.close();//close the server
                        socket.close();      //then close the client
                        break;  //get out of loop
                    }
                    else {
                        System.out.println("Unknown command received: " 
                            + strReceived);
                        outputToClient.writeUTF("Unknown command.  "
                                + "Please try again.");
                        }
                    } 
            }//end server loop
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }//end try-catch
    }//end createCommunicationLoop
}
