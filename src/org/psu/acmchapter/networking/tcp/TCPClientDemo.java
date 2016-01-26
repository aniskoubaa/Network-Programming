package org.psu.acmchapter.networking.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPClientDemo {
    public static void main(String args[]) throws IOException { 
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   C L I E N T\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
         Socket clientSocket =null;
         String serverHostName = "localhost";
         
         int port = 1234;
        try {
            clientSocket = new Socket(serverHostName, port);
        } catch (IOException ex) {
            System.out.println("[TCP Client] cannot open the socket with the server");
        }
        
        PrintStream printStream = new PrintStream(clientSocket.getOutputStream());

        InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
        Scanner s = new Scanner(System.in);
        System.out.println("Press a key to send the message ...");
        s.next();
        //prepare the String. WARNING: add \n to make sure that the message is considered as one line
        String message = "Hello World from TCP Client \n\n\n\n";
        printStream.print(message);
        
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        message = bufferedReader.readLine();
        System.out.println("message received from server: \n\t"+message);
        
         
     }
    
}
