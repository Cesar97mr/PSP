
public class Contador1 extends Thread{

	
	public Contador1(String nombre) {
		if(nombre != null) {
			setName(nombre);
		}
		// El hilo ejecuta su propio método run 
		start();
	}
	
	@Override
	public void run() {
		for(int i = 0; i <= 100; i++) {
			System.out.println(getName() + " " +  i + "\r"); 
		}
			
		System.out.println();
	}
	
	public static void main(String[] args) {
		Contador1 c1 = new Contador1("hiloCesar");
		
	}
}

