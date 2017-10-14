package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    private static Socket socket;
    private static InputStream input;
    private static OutputStream output;
    private static BufferedReader in;
    private static PrintStream out;
    
    public static void main(String[] args) {
        System.out.println("CLIENTE");
        Scanner scanner = new Scanner(System.in);
        try {
            while(true) {
                System.out.println("Digite a operação: ");
                String op = scanner.nextLine();
                String message = op + "|";
                System.out.println("Digite o primeiro numero: ");
                message += scanner.nextLine();
                
                if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("<")) {
                    message += "|";
                    System.out.println("Digite o segundo numero: ");
                    message += scanner.nextLine();
                }
                
                conectarServidor();
                out.println(message);
                message = in.readLine();
                System.out.println("Resultado: " + message);
                desconectarServidor();
            }
	} catch(IOException ex){
            ex.printStackTrace();
	} finally {
            scanner.close();
	}
}
    private static void conectarServidor() throws UnknownHostException, IOException {
        socket = new Socket("localhost", 137);
        input = socket.getInputStream();
        output = socket.getOutputStream();
        in = new BufferedReader(new InputStreamReader(input));
        out = new PrintStream(output);
    }

    private static void desconectarServidor() throws IOException {
	in.close();
	out.close();
	socket.close();
    }
}