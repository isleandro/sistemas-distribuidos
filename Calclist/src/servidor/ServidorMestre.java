package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class ServidorMestre {
    private static Socket socket2;
    private static InputStream input2;
    private static OutputStream output2;
    private static BufferedReader in2;
    private static PrintStream out2;
    
    public static void main(String[] args) {
        System.out.println("MESTRE");
        
        ServerSocket server;
        
        try {
            server = new ServerSocket(137);
            Socket socket;
            
            while(true) {
                socket = server.accept();
                
                InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintStream out = new PrintStream(output);

		String message = in.readLine();
                
                String ip = selecionarEscravo(message);
                String numeros = separarNumeros(message);
                
                conectarEscravo(ip);
                out2.println(numeros);
                
                message = in2.readLine();
                out.println(message);
                
                desconectarEscravo();
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private static void conectarEscravo(String ip) throws UnknownHostException, IOException {
        socket2 = new Socket(ip, 65000);
        input2 = socket2.getInputStream();
        output2 = socket2.getOutputStream();
        in2 = new BufferedReader(new InputStreamReader(input2));
        out2 = new PrintStream(output2);
    }
    
    private static void desconectarEscravo() throws IOException {
	in2.close();
	out2.close();
	socket2.close();
    }
    
    private static String selecionarEscravo(String message) {
        String[] operandos = message.split(Pattern.quote("|"));
        String ip = "";

        String operador = operandos[0];
        
        switch (operador) {
            case "+": {
                ip = "localhost";
                break;
            }
            case "-": {
                ip = "";
                break;
            }
            case "*": {
                ip = "";
                break;
            }
            case "/": {
                ip = "";
                break;
            }
            case "<": {
                ip = "";
                break;
            }
            case ">": {
                ip = "";
                break;
            }
        }
        return ip;
    }
    
    private static String separarNumeros(String message) {
        String[] operandos = message.split(Pattern.quote("|"));
        String op = operandos[0];
        String numeros;
        
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("<")){
            numeros = operandos[1] + "|" + operandos[2];
        } else {
            numeros = operandos[1];
        }
        
        return numeros;
    }
}
