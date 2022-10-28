package DAK.HW2;

import DAK.Encryption.Ceaser;


import java.io.FileInputStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;
import java.io.File;
import java.util.Scanner;

public class Server {

    public static String encodeData(String data) {
        Ceaser c = new Ceaser(5);
        return c.encode(data);
    }
    public static String decodeData(String cyphertext) {
        Ceaser c = new Ceaser(5);
        return c.decode(cyphertext);
    }

    public static void main(String [] args) {
    DatagramSocket conn = null;
    try {
        conn = new DatagramSocket(2644);
        byte [] buffer = new byte[256];
        DatagramPacket dpack = new DatagramPacket(buffer, buffer.length);

        while (true) {
        //recive data over a udp connection
            conn.receive(dpack);
            ResponseThread t = new ResponseThread(conn,dpack);//prepare a resonse thread
            t.start();//start the thread
        }

    } catch( Exception e) {
        System.out.println(e);
    }
    finally {
        if (conn != null) {
            conn.close();
        }
    }
    }
    
}
