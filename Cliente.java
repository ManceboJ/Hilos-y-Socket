/* 
 * Clase Cliente
 * Envía una petición de socket al Servidor
 * Imprime en consola el menu que le envía el servidor
 * El cliente elige una opción
 * Imprime la respuesta dada por el servidor
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// Pedir IP del servidor
		String IPServer = System.console().readLine("Introduzca la dirección IP del servidor: \n");
		
		// Bucle while para crear nuevas peticiones al servidor al terminar.
		while(true) {
					
			//Creamos el socket
			Socket socket = new Socket(IPServer, 9999);
			
			// Creamos buffer de entrada
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			// Imprimimos mensaje del servidor con las opciones
			System.out.println(input.readLine());
			System.out.println(input.readLine());
			System.out.println(input.readLine());
			System.out.println(input.readLine());
			System.out.println(input.readLine());
			System.out.println(input.readLine());
			System.out.println(input.readLine());

		
			// Cliente elije opcion
			String number = System.console().readLine();
			
			// Enviamos la opcion elegida al servidor
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			out.println(number);
			
			// Imprimimos respuesta del servidor a la opción elegida por el cliente
			System.out.println(input.readLine() + "\n");
			
			// Cerramos el socket
			socket.close();
		}
	}

}
