package Tarea1;

//Creamos la clase Buffer con nuestros atributos
public class Buffer {
	private boolean bufferLleno;
	private boolean bufferVacio;
	private char[] buffer;
	private int next;

	//Constructor con tamaño por parametro para poder establecerlo en el main
	public Buffer(int tamanio) {
		this.buffer = new char[tamanio];
		this.next = 0;
		this.bufferLleno = false;
		this.bufferVacio = false;
	}
	
	public synchronized char consumir() {
		
		while(this.bufferVacio) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.next--;
		
		if(this.next == 0) {
			this.bufferVacio = true;
		}
		
		notifyAll();
		
		return this.buffer[this.next];
	}
	
	public synchronized void producir(char c) {
		while(this.bufferLleno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.buffer[this.next] = c;
		
		this.next++;
		this.bufferVacio = false;
		
		if(this.next == this.buffer.length) {
			this.bufferLleno = true;
		}
		
		notifyAll();
	}
}
