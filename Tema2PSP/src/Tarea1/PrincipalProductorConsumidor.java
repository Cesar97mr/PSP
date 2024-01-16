package Tarea1;

public class PrincipalProductorConsumidor {

	public static void main(String[] args) {
		
	
		
		try {
			
			Buffer b = new Buffer(6);
			Productor p = new Productor(b);
			Consumidor c = new Consumidor(b);
			p.start();
			Thread.sleep(3000);
			c.start();
			
			p.join();
			c.join();
			System.out.println("Termina programa");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
