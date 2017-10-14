package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorRaizQuadrada {
    public static void main(String[] args) {
        System.out.println("RAIZ QUADRADA");

        ServerSocket server;

        try {
            server = new ServerSocket(65000);
            Socket socket;

            while (true) {
                socket = server.accept();
                
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                PrintStream out = new PrintStream(output);
                
                String message = in.readLine();
                out.println(raiz(message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static double raiz(String message) {
        double num1;

        num1 = Double.parseDouble(message);

        return Math.sqrt(num1);
    }
}