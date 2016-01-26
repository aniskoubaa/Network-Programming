package org.psu.acmchapter.networking.serialization;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import org.psu.acmchapter.networking.structures.Date;
import org.psu.acmchapter.networking.structures.Student;

public class TCPStudentClientDemo {
    public static void main(String args[]) throws IOException { 
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   S T U D E N T    C L I E N T\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
         Socket clientSocket =null;
         String serverHostName = "localhost";
         
         int port = 7777;
        try {
            clientSocket = new Socket(serverHostName, port);
        } catch (IOException ex) {
            System.out.println("[Student TCP Client] cannot open the socket with the server");
        }
        
        Student student = new Student (12, "Ali", "Ahmed", new Date(1,1,1980));
        
        
        /*********************************************************************/
        /******************* Using Serializable *************************/
        /*********************************************************************/
        
        /** second approach: using Serializable **/
        //ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        //oos.writeObject(student);
        
        /*********************************************************************/
        /******************* Using toString and JSON *************************/
        /*********************************************************************/
        
        PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
        InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
        
        Scanner s = new Scanner(System.in);
        System.out.println("[Student TCP Client] Press a key to send the message ...");
        s.next();

        /** first approach: using toString() **/
        //String message = String.format("%s\n", student.toString());
        
        
        /** second approach: using JSON serialization **/
        Gson gson = new Gson();
        String message = gson.toJson(student);
        System.out.println("[Student TCP Client] Message Format: "+message);
        
        /**********  Send the message to the server *************/
        printStream.print(message+"\n");
        System.out.println("[Student TCP Client] The message "+message+" is sent to the server");
        
        
        /**********  Get the response back from the server *************/
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        System.out.println("[Student TCP Client] waiting for server response ... \n");
        String receivedMessage = bufferedReader.readLine();
        System.out.println("[Student TCP Client] message received from server: \n"+receivedMessage);
        
         
     }
    
}
