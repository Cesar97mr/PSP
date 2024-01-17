package Ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServidorHilo extends Thread{
	
	private Socket sc;
	
	public ServidorHilo(Socket sc) {
		this.sc = sc;
		
	}
	
	public void run() {
		
		try {
			DataInputStream in = new DataInputStream(sc.getInputStream());
			DataOutputStream out  = new DataOutputStream(sc.getOutputStream());
			
			int numAleatorio = (int) (Math.random() * 500 + 1);
			int numUsuario = 0;
			
			System.out.println("Numero generado: " +  numAleatorio);
			
			do {
				
				out.writeUTF("Escribe un numero entre 1 y 500");
				
				numUsuario  = in.readInt();
				
				System.out.println("Numero recibido: " + numUsuario);
				
				if(numUsuario == numAleatorio) {
					out.writeUTF("!Has ganado! ");
				}else if(numUsuario < numAleatorio) {
					out.writeUTF("El numero buscado es mayor");
				}else {
					out.writeUTF("El numero buscado es menor" );
				}
			
				out.writeBoolean(numUsuario == numAleatorio);
			}while(numUsuario != numAleatorio);
			
			sc.close();
			System.out.println("Cliente desconectado");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
