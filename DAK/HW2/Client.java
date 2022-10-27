package DAK.HW2;

import DAK.Encryption.Ceaser;

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

    public static String encodeData(String data) {
        Ceaser c = new Ceaser(5);
        return c.encode(data);
    }
    public static String decodeData(String cyphertext) {
        Ceaser c = new Ceaser(5);
        return c.decode(cyphertext);
    }

    public static void main(String [] args) {
        Scanner cin = new Scanner(System.in);
        String firstName = demandUsername(cin, "First Name: ");
        System.out.println();
        String lastName = demandUsername(cin, "Last Name: ");

        String cipherTextSend = encodeData(firstName + "_" + lastName);
        System.out.println("sending cipeherText " + cipherTextSend);
        try {
            //send the data
            DatagramSocket udpSocket = new DatagramSocket(2645);
            DatagramPacket p = new DatagramPacket(cipherTextSend.getBytes(), cipherTextSend.getBytes().length,new InetSocketAddress("127.0.0.1", 2644));            
            udpSocket.send(p);

            //recive the response into the required variable
            byte [] responce_buffer = new byte[256];
            DatagramPacket responce = new DatagramPacket(responce_buffer, responce_buffer.length);
            udpSocket.receive(responce);
            String cipherTextRecieve = (new String(responce.getData())).trim();
            String planeTextRecieve = decodeData(cipherTextRecieve);
            if (planeTextRecieve.equals("-1")) {
                System.out.println("invalid user name");
            } else {
                System.out.println(planeTextRecieve);
            }


            udpSocket.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
