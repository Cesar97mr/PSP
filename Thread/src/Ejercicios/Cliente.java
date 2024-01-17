package Ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {

	public static void main(String[] args) {

		try {
			
			Socket	sc = new Socket("localhost", 3000);
			
			DataInputStream in = new DataInputStream(sc.getInputStream());
			DataOutputStream out = new DataOutputStream(sc.getOutputStream());
			
			boolean salir = false;
			Scanner sn = new Scanner(System.in);
			
			do {
				String mensaje = in.readUTF();
				
				System.out.println(mensaje);
				
				int num = sn.nextInt();
				out.writeInt(num);
				
				mensaje = in.readUTF();
				System.out.println(mensaje);
				
				salir = in.readBoolean();
			}while(!salir);
			
			sc.close();
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
