package ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class EjemploDataIO2 {
	
	static Random r = new Random();
	
	public static void main(String[] args) {
		String ruta = System.getProperty("user.home") + "/Documents/datos.bin";
		escribir(ruta);
		leer(ruta);
	}
	
	static void escribir(String ruta) {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(ruta));
			out.writeUTF("números");
			int n = r.nextInt(1000) + 100;
			for (int i=0; i<n; i++)
				out.writeInt(r.nextInt());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	static void leer(String ruta) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(ruta));
			String s = in.readUTF();
			System.out.println(s);
			int numero;
			
				numero = in.readInt();
				System.out.println(numero);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
