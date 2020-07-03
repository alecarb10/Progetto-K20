package clientserver.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    ServerSocket server = null;
    Socket clientSocket = null;
    DataOutputStream out;
    DataInputStream in;
    int porta = 6789;

    public void comunica(){
        try {
            System.out.println("3.aspetto un messaggio");
            String letto = in.readLine();

            System.out.println("4.messaggio ricevuto  "+ letto);
            String risposta = letto.toUpperCase();
            System.out.println("5. rispondo con "+ risposta);
            out.writeBytes(risposta + "\n");
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Socket attendi(){

        try {
            System.out.println("1. Server inizializzato");
            server = new ServerSocket(porta);
            System.out.println("2. Server pronto, in ascolto sulla porta: " + porta);
            
            clientSocket = server.accept();
            System.out.println("3. connessione stabilita");

            server.close();

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());




        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientSocket;

    }

    public static void main(String[] args) {

        Server s = new Server();
        s.attendi();
        s.comunica();
    }

}