/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psu.acmchapter.networking.serialization;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.psu.acmchapter.networking.structures.Student;

public class TCPStudentServerDemo {
    
    static ArrayList<Integer> StudentIDDatabase = new ArrayList<Integer>();
    
    public static void main(String []args){
        StudentIDDatabase.add(1);
        StudentIDDatabase.add(52);
        StudentIDDatabase.add(3);
        StudentIDDatabase.add(12);
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tT C P   S T U D E N T S E R V E R\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
        
        //declare a TCP socket object and initialize it to null
        ServerSocket serverSocket=null;
        //create the port number
        int port = 7777;
        
        try {
            //create the TCP socket server
            serverSocket = new ServerSocket(port);
            System.out.println("TCP server created on port = "+port);
        } catch (IOException ex) {
            //will be executed when the server cannot be created
            System.out.println("Error: The server with port="+port+" cannot be created");
        }
        
        //starts an endless loop
        while(true){
            Socket clientSocket = null;
            try {
                //start listening to incoming client request (blocking function)
                System.out.println("[TCP Server] waiting for the incoming request ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Error: cannot accept client request. Exit program");
                return;
            }
            try {
                processClientRequest(clientSocket);
            } catch (IOException e) {
                //log exception and go on to next request.
            }
        }
        
    }
    
    public static void processClientRequest(Socket clientSocket) throws IOException {
        System.out.println("[TCP Server] processing the incoming request");
        try {
            PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
            InputStreamReader inputStream = new InputStreamReader(clientSocket.getInputStream());
 
            /*********************************************************************/
            /*********************** Serializable ********************************/
            /*********************************************************************/
            /** 
             * parsing using the second approach: Serializable 
             **/
            /*ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Student student =(Student) (ois.readObject());
            System.out.println("student message received from client: \n\n"+student);
            */
            
            /*********************************************************************/
            /******************* Using toString and JSON *************************/
            /*********************************************************************/
            //read the received message
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String message = null;
            message = bufferedReader.readLine();

            System.out.println("student message received from client: \n\n"+message);
            
            /** 
             * APPROACH 1
             * parsing using the first approach: using toString() 
             * difficult to parse - depends on the format of toString()
             **/

            
            
            /** 
             * APPROACH 2
             * parsing using the second approach: using JSON format
             * much easier to parse, universal format
             **/
            Gson gson = new Gson();
            Student student = gson.fromJson(message, Student.class);
            String responseMessage = "\n";
            
            /******* CHECK IF THE STUDENT IS IN DATABASE ***********/
            
            if (StudentIDDatabase.contains(student.getId()))
                responseMessage = String.format("%s %s is registered!\n", 
                        student.getFirstName(),
                        student.getLastName());
            else
                responseMessage = String.format("%s %s is NOT registered!\n", 
                        student.getFirstName(),
                        student.getLastName());
            
            /******* SEND RESPONSE BACK TO CLIENT ***********/

            //prepare the String. 
            //WARNING: add \n to make sure that the message is considered as one line
            
            printStream.println(responseMessage);
            printStream.close();
        }catch (Exception e){
            System.out.print("[TCP Server] The server cannot send the message");
        }
    }
    
    public static void processClientRequestHTML(Socket clientSocket) throws IOException {
        InputStream  input  = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();
        long time = System.currentTimeMillis();

        output.write(("HTTP/1.1 200 OK\n\n<html><body>" +
                "Singlethreaded Server: " +
                time +
                "<p>Salam wa rahmatou Allah</p></body></html>").getBytes());
        output.close();
        input.close();
        System.out.println("Request processed: " + time);
    }
    
}
