package unidad6.streams.ejercicio1;

import java.util.stream.Stream;

public class Ejercicio1 {

	public static void main(String[] args) {
		Datos.getPalabras().forEach(System.out::println);
		metodo01(Datos.getPalabras());
	}

	static void metodo01(Stream<String> palabras) {
		
	}
	
}
