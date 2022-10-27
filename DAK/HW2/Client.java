package DAK.HW2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.regex.*;

public class Client {
    public static String demandPattern(Scanner cin,String pattern,String prompt,String warning) {
        System.out.print(prompt);
        String ret_val = cin.nextLine();

        while (!Pattern.matches(pattern, ret_val)) {
            System.out.print(warning);
            ret_val = cin.nextLine();
        }

        return ret_val;
    }
    public static String demandUsername(Scanner cin,String prompt) {
        return demandPattern(cin, "[A-Z]+", prompt, "invalid name, must only contain A-Z\n" + prompt);
    }
    public static void main(String [] args) {
        Scanner cin = new Scanner(System.in);
        String firstName = demandUsername(cin, "First Name: ");
        System.out.println();
        String lastName = demandUsername(cin, "Last Name: ");

        String data = firstName + "_" + lastName;
        try {
            DatagramSocket udpSocket = new DatagramSocket(2645);
            
            DatagramPacket p = new DatagramPacket(data.getBytes(), data.getBytes().length,new InetSocketAddress("127.0.0.1", 2644));
            
            udpSocket.send(p);

            byte [] responce_buffer = new byte[256];
            DatagramPacket responce = new DatagramPacket(responce_buffer, responce_buffer.length);
            udpSocket.receive(responce);
            System.out.println(new String(responce.getData()));


            udpSocket.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
