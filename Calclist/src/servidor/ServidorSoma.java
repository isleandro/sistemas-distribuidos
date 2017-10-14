package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class ServidorSoma {
    public static void main(String[] args) {
        System.out.println("SOMA");
        
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
                out.println(somar(message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static double somar(String message) {
        double num1, num2;
        
        String[] numeros = message.split(Pattern.quote("|"));
        
        num1 = Double.parseDouble(numeros[0]);
        num2 = Double.parseDouble(numeros[1]);
        
        return num1 + num2;
    }
}