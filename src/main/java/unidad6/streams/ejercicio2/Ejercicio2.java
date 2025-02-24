package unidad6.streams.ejercicio2;

import java.util.stream.Stream;

public class Ejercicio2 {

	public static void main(String[] args) {
//		Datos.getLineas().forEach(System.out::println);
	}

	/*
	 * Crea un método estático que acepte una secuencia de cadenas y retorne el
	 * máximo número de elementos contenidos en una línea.
	 */
	static int metodo02(Stream<String> lineas) {
//		return lineas
//				.mapToInt(l -> l.split(" ").length)
//				.max()
//				.getAsInt();
		return lineas
				.map(l -> l.split(" ").length)
				.max(Integer::compare).get();
				
	}
}
