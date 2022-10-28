package DAK.HW2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.io.File;
import java.util.Scanner;

import DAK.Encryption.Ceaser;

public class ResponseThread extends Thread {
    public static String encodeData(String data) {
        Ceaser c = new Ceaser(5);
        return c.encode(data);
    }
    public static String decodeData(String cyphertext) {
        Ceaser c = new Ceaser(5);
        return c.decode(cyphertext);
    }

    String clientCypherRequest;
    SocketAddress clientAddress;
    DatagramSocket outsock; 
    //do the absolute bare minimum on the main thread to prepare
    //this thread to run and respond to the client
    ResponseThread(DatagramSocket ds,DatagramPacket clientRequest) {
        clientCypherRequest = new String(clientRequest.getData());
        this.clientAddress = clientRequest.getSocketAddress();
        outsock = ds;
    }
    @Override
    public void run() {
        String [] split_data = decodeData(clientCypherRequest.trim()).split("_");

        String first = split_data[0];
        String last = split_data[1];

        //get the entry from the file
        File f = new File("./sample.txt");
        try {
        Scanner sc = new Scanner(f);
        Entry e = null;
        while (sc.hasNext()) {
            e = new Entry(sc);
            if (e.checkFirstLast(first,last ))  {
                break;
            }
        }
       

        String data_to_send = "-1";
        if (e != null) {
            data_to_send = e.ssn.toString();
        }

        String to_send = encodeData(data_to_send);
 
        //throw a response back at the client
        outsock.send(new DatagramPacket(to_send.getBytes(), to_send.getBytes().length,clientAddress));
    }
    catch (Exception e)  {
        System.out.println(e);
    }

    }
}
