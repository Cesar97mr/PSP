package ejercicio2;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread {

    private Socket sc;

    public ServidorHilo(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(sc.getInputStream());
             DataOutputStream out = new DataOutputStream(sc.getOutputStream())) {

            String ruta = in.readUTF();
            File f = new File(ruta);

            if (f.exists() && f.isFile()) {
                out.writeBoolean(true);

                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    StringBuilder contenido = new StringBuilder();
                    String linea;

                    while ((linea = br.readLine()) != null) {
                        contenido.append(linea).append("\r\n");
                    }

                    byte[] contenidoFichero = contenido.toString().getBytes("UTF-8");

                    out.writeInt(contenidoFichero.length);
                    out.write(contenidoFichero);
                }

            } else {
                out.writeBoolean(false);
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, "Error en el hilo del servidor", ex);
        }
    }
}