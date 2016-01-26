package org.psu.acmchapter.networking.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddressDemo {
    
    public static void main(String args[]) throws UnknownHostException { 
        
        //obtain the IP address of the localhost (the computer the program is running on):
        InetAddress Address = InetAddress.getLocalHost(); 
        
        //print the InetAddress Object of localhost
        System.out.println("locahost InetAddress: "+Address); 
        
        //print the address only
        System.out.println("locahost host address: "+Address.getHostAddress()); 
        //print the name only
        System.out.println("locahost host name: "+Address.getHostName()); 
        System.out.println("");
        
        //get the InetAddress for a String representation of an IP address
        Address = InetAddress.getByName("78.46.84.171");
        //print the address of the website
        System.out.println("78.46.84.171 InetAddress: "+Address); 

        System.out.println("");
        
        //find the IP address of a website
        Address = InetAddress.getByName("google.com.sa"); 
        
        //print the address of the website
        System.out.println("Google InetAddress: "+Address); 
        
        System.out.println("");
        
        //get all IP address of a website
        InetAddress SW[] = InetAddress.getAllByName("www.google.com.sa"); 
        for (int i=0; i<SW.length; i++) 
        System.out.println(SW[i]); 
    }
}
