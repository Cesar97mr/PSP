package Tarea1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
	private char[] buffer;
	private int siguiente;
	private boolean estaLlena;
	private boolean estaVacia;
	
	public Buffer(int tamanio) {
		this.buffer = new char[tamanio];
		this.siguiente = 0;
		this.estaLlena = false;
		this.estaVacia = false;
	}
	
	public synchronized char consumir() {
		
		while(this.estaVacia) {
			try {
				wait();
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		this.siguiente--;
		
		if(this.siguiente == 0) {
			this.estaVacia = true;
		}
		
		notifyAll();
		
		return this.buffer[this.siguiente];
	}
	
	public synchronized void producir(char c) {
		while(this.estaLlena) {
			try {
				wait();
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		this.buffer[this.siguiente] = c;
		
		this.siguiente++;
		this.estaVacia = false;
		
		if(this.siguiente == this.buffer.length) {
			this.estaLlena = true;
		}
		
		notifyAll();
	}
}
