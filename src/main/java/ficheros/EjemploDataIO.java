package ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class EjemploDataIO {

	static int[] numeros = new int[100];
	static {
		Random r = new Random();
		for (int i=0; i<numeros.length; i++)
			numeros[i] = r.nextInt();
	}
	
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
			for (int i=0; i<numeros.length; i++)
				out.writeInt(numeros[i]);
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
			int [] numeros = new int[100];
			for (int i=0; i<numeros.length; i++)
				numeros[i] = in.readInt();
			System.out.println(Arrays.toString(numeros));
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
