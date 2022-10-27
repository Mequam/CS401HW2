package DAK.HW2;

import DAK.Encryption.Ceaser;

import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.channels.DatagramChannel;

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
    try {
        DatagramSocket conn = new DatagramSocket(2644);
        byte [] buffer = new byte[256];
        DatagramPacket dpack = new DatagramPacket(buffer, buffer.length);

        //recive data over a udp connection
        conn.receive(dpack);

        String [] split_data = decodeData(new String(dpack.getData()).trim()).split("_");

        String first = split_data[0];
        String last = split_data[1];


        String to_send = encodeData("111-111-1111");
        //throw a response back at the client
        conn.send(new DatagramPacket(to_send.getBytes(), to_send.getBytes().length,dpack.getSocketAddress()));

        conn.close();
    } catch( Exception e) {
        System.out.println(e);
    }
    }
    
}
