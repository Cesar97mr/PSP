package Ejercicios;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket servidor = new ServerSocket(3000);
			System.out.println("Servidor iniciado");
			
			while(true) {
				Socket sc = servidor.accept();
				
				ServidorHilo hilo = new ServidorHilo(sc);
				hilo.start();
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}