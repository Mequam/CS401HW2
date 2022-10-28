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

    /**
     * encodes data using a ceaser cipher method
     */
    public static String encodeData(String data) {
        Ceaser c = new Ceaser(5);
        return c.encode(data);
    }
    /**
     * decodes the data using a ceaser cipher method
     */
    public static String decodeData(String cyphertext) {
        Ceaser c = new Ceaser(5);
        return c.decode(cyphertext);
    }

    public static void main(String [] args) {
        String server_addr_string = "127.0.0.1";
        if (args.length > 0) server_addr_string = args[0];

        Scanner cin = new Scanner(System.in);
        //get the first and last names
        String firstName = demandUsername(cin, "Enter the first name: ");
        String lastName = demandUsername(cin, "Enter the last name: ");

        //encode them
        String cipherTextSend = encodeData(firstName + "_" + lastName);

        //display the name out to screen
        System.out.println("Encrypted user name: " + 
            encodeData(firstName) + //the assignment output example
            " " +                   //has the space un encoded in the encoded username on output
            encodeData(lastName));  //we mimic that here, even though we encode the delimiter in transit

        try {
            //send the data
            DatagramSocket udpSocket = new DatagramSocket(2645); //we will use this socket to send and recive
            DatagramPacket p = new DatagramPacket(
                    cipherTextSend.getBytes(), 
                    cipherTextSend.getBytes().length,
                    new InetSocketAddress(server_addr_string, 2644)
                );

            udpSocket.send(p);

            //recive the response into the required variable
            byte [] responce_buffer = new byte[256];
            DatagramPacket responce = new DatagramPacket(responce_buffer, responce_buffer.length);
            udpSocket.receive(responce);

            //decode the responce and print out an appropriate message            
            String cipherTextRecieve = (new String(responce.getData())).trim();
            String planeTextRecieve = decodeData(cipherTextRecieve);
            if (planeTextRecieve.equals("-1")) {
                System.out.println("invalid user name");
            } else {
                System.out.println("SSN: " + planeTextRecieve);
            }

            //close the socket and resources
            udpSocket.close();
            cin.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
