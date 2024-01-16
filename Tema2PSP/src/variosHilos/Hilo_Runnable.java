package variosHilos;

public class Hilo_Runnable implements Runnable {
    //clase que implementa Runnable
    public void run() {
        //redefinimos run() con el código asociado al hilo
        for (int i = 1; i <= 155; i++) {
            System.out.println("  Hilo_Runnable");
        }
    }
}