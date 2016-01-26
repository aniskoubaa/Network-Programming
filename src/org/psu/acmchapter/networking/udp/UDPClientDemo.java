package org.psu.acmchapter.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPClientDemo {
    
    public static void main(String args[]) throws Exception  { 
        
        System.out.print("/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n"
                + "\t\tU D P   C L I E N T\n"
                + "/* * * * * * * * * * * * * * * * * * * * * * * * * * */\n");
             
        //create the server host name
        String serverHostName = "192.168.100.4";
        
        //create the server address
        InetAddress serverIPAddress=null;
        
        //create the port number
        int serverPortNumber = 5555; //same as the one set in the server
        
        //create the client socket that will be used to contact the UDP server
        DatagramSocket clientSocket = new DatagramSocket();
        
        //find the IP address of the server from its name
        serverIPAddress = InetAddress.getByName(serverHostName);
        
        System.out.println("Attemping to send to " + serverIPAddress + ") via UDP port" + serverPortNumber + "\n");
        
        /** send a packet to UDP server **/
        
        String yourName = "write_your_name";
        //prepare the message to send
        String message = "Hello from "+yourName+" sent by the host: " + InetAddress.getLocalHost(); 
        
        //prepare the buffer that will be used to send the message
        byte[] sendData = new byte[message.length() * 8];
        sendData = message.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPortNumber);
        clientSocket.send(sendPacket);
        
        /** get response from UDP server **/
        /*
        //prepare a the packet structure for received packets
        int dataLength = 200; //must be large enough otherwise large message will not be fully received
        byte[] receiveData = new byte[dataLength];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        
        System.out.println("[UDP Client] Waiting for server response ...");
        clientSocket.receive(receivePacket);
        
        //Extract received packet data 
        InetAddress IPAddress = receivePacket.getAddress();
        int serverPort = receivePacket.getPort();
        String messageReceivedFromServer = new String(receivePacket.getData());
        //we must trim the message to remove empty bytes. Observe the output without the trim function
        messageReceivedFromServer = messageReceivedFromServer.trim();
        System.out.println ("[UDP Client] The message {"+messageReceivedFromServer +"}.\n\t\tThe message is received from host: " + IPAddress + " on port" + serverPort);
        */
    }
    
}
