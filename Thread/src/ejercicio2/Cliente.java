package ejercicio2;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        try (Socket sc = new Socket("localhost", 1500);
             DataInputStream in = new DataInputStream(sc.getInputStream());
             DataOutputStream out = new DataOutputStream(sc.getOutputStream())) {

            Scanner sn = new Scanner(System.in);

            System.out.println("Introduce la ruta del archivo a mostrar");
            String ruta = sn.next();

            out.writeUTF(ruta);

            boolean existe = in.readBoolean();

            if (existe) {
                int longitud = in.readInt();
                byte[] contenido = new byte[longitud];

                for (int i = 0; i < longitud; i++) {
                    contenido[i] = in.readByte();
                }

                String contenidoFichero = new String(contenido, "UTF-8");
                System.out.println(contenidoFichero);

            } else {
                System.out.println("Error, no existe el fichero");
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "Error en el cliente", ex);
        }
    }
}