/* 
 * Clase Servidor 
 * Crea un hilo por cada cliente
 * Les da la bienvenida, les indican cual es su número de cliente y las opciones que pueden elegir para tener una respuesta sobre la asignatura
 * Según la opción elegida por el cliente envía la información correspondiente
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		
		ServerSocket listener = new ServerSocket(9999);
		
		System.out.println("Servidor iniciado");
		
		int cliente = 0;
		
		try {
		while(true) {
			// Espera y acepta un nuevo cliente
			Socket socket = listener.accept();
			System.out.println("Aceptado socket del cliente: " + ++cliente);
			// Creamos un nuevo hilo
			new WorkerThread(socket,cliente).start();
			}
		}finally {
			listener.close(); 
			}
		}
	}

// Implementacion nuevo hilo
class WorkerThread extends Thread{
	private Socket socket;
	private int cliente;

	// Constructor
	public WorkerThread(Socket socket, int cliente) {
		this.socket = socket;
	    this.cliente = cliente;
	    }

	@Override
	public void run() {
		try {
			// Creamos el buffer de entrada
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
			// Creamos el stream de salida
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
				
			// Imprimimos las opciones
			out.println("Cliente " + cliente);
			out.println("Bienvenido a la práctica 1 de Hilos y Socket. Por favor elija una de las siguientes opciones pulsado del 1 al 5.");		
			out.println("1. ¿Cuál es el nombre de la asignatura de la práctica?");
			out.println("2. ¿Donde se imparte la asignatura?");
			out.println("3. ¿Quien es el profesor?");
			out.println("4. ¿Qué alumno ha realizado la práctica?");
			out.println("5. ¿A que semestre corresponde la asignatura?");
			out.flush(); // Sirve para vaciar el buffer de salida
							
			// Leemos respuesta del cliente
			String lectura = input.readLine();
			// Devolvemos respuesta según elección del cliente
			switch (lectura) {
			   case "1":
			       out.println("Programación Concurrente y Distribuida.");
			       break;
			   case "2":
			       out.println("Universidad Europea modalidad online.");
			       break;
			   case "3":
			       out.println("Jose Delgado Perez");
			       break;
			   case "4":
			       out.println("Javier Mancebo Sánchez");
			       break;
			   case "5":
			       out.println("Semestre 3.");
			       break;    
			   default:
			       out.println("No ha elegido una opción entre la 1 y la 5.");
			       break;
			       }
			
			System.out.println("Cerrando socket del cliente: " + cliente);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			}
	}
}


