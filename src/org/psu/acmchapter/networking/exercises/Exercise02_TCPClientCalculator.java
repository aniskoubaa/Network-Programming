/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psu.acmchapter.networking.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author akoubaa
 */
public class Exercise02_TCPClientCalculator {
     public static void main(String args[]) throws IOException { 
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   C L I E N T    C A L C U L A T O R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
         Socket clientSocket =null;
         String serverHostName = "localhost";
         
         int port = 2225;
        try {
             System.out.println("[TCP Client] Trying to connect to "+ serverHostName + " on port "+port);
            clientSocket = new Socket(serverHostName, port);
            System.out.println("[TCP Client] Connected "+ serverHostName + " on port "+port);
        } catch (IOException ex) {
            System.out.println("[TCP Client] cannot open the socket with the server");
        }
        
        /**
        **  Task1 : create PrintStream and InputStreamReader objects
        **/
        PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
        InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
        
        /**
        **  Task2 : read two integer from the user
        **/

        
        /**
        **  Task3 : create the message to be sent in the form x;y
        **/
        
        System.out.println("[Calculator Client] sending the message "+ message);
        /**
        **  Task4 : send the message to the server
        **/


        System.out.println("[Calculator Client] message {"+message.replace("\n","")+"} sent to server");
        
        System.out.println("[Calculator Client] waiting for response ...");
        /**
        **  Task5 : receive the response from the server and print the sum
        **/

        
         
     }
    
}
