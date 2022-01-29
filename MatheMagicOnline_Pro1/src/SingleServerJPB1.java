
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
import java.io.*;



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
        String userName="", pwd="";
        
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
                currencies = strReceived.split(" ");
                String command=currencies[0];

                if (authorizedUser==false)
                {
                    if(command.equalsIgnoreCase("login"))
                    {
                        userName=currencies[1];
                        pwd=currencies[2];
//                        System.out.println(currencies[1] + " " + currencies[2]);
                        
                        //check if username exist
                        if(loginsInfo.containsKey(userName))
                        {
                            //check if its is the right password for that usename
                            if(loginsInfo.get(userName).equals(pwd)){
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
                    if(command.equalsIgnoreCase("solve")) 
                    {
                        System.out.println("Sending solve to client");
                        //get solve command details
                        String shape= currencies[1];
                        float redius, circumference,length, length2,perimeter, area;
                        
                        //create a solve file
                        try {
                          File solveFile = new File(userName+"_solutions.txt");
         
                          if (solveFile.createNewFile()) {
                            System.out.println("File created: " + solveFile.getName());
                          } else {
                            System.out.println("File already exists.");
                          }
                        } catch (IOException e) {
                          System.out.println("An error occurred.");
                          e.printStackTrace();
                        } 
                    
                        if(shape.equalsIgnoreCase("-c"))
                        {
                            if(currencies.length==3)
                            {
                                redius= Float.parseFloat(currencies[2]);
                                circumference= (float)(Math.PI*2*(redius));
                                area= (float) (Math.PI * Math.pow(redius, 2));
         
                                outputToClient.writeUTF("Circle’s circumference is "+
                                        String.format("%.2f", circumference)
                                        + " and area is "+ String.format("%.2f" ,area));                          
                            }
                            else
                            {
                                outputToClient.writeUTF("Error: No radius found");
                            }  
                        }
                        else if(shape.equalsIgnoreCase("-r"))
                        {
                            if(currencies.length==3)
                            {
                                length= Float.parseFloat(currencies[2]);
                                perimeter= (float)(4 * length);
                                area= (float) (Math.pow(length, 2));
         
                                outputToClient.writeUTF("Rectangle’s perimeter is "+
                                        String.format("%.2f", perimeter)
                                        + " and area is "+ String.format("%.2f" ,area));                          
                            }
                            else if(currencies.length==4)
                            {
                                length= Float.parseFloat(currencies[2]);
                                length2= Float.parseFloat(currencies[3]);
                                perimeter= (float)(2*(length+length2));
                                area= (float) (length*length2);
         
                                outputToClient.writeUTF("Rectangle’s perimeter is "+
                                        String.format("%.2f", perimeter)
                                        + " and area is "+ String.format("%.2f" ,area));                          
                            }
                            else
                            {
                                outputToClient.writeUTF("Error: No radius found");
                            }     
                        }          
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
