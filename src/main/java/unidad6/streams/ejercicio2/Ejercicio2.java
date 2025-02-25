package unidad6.streams.ejercicio2;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio2 {

	public static void main(String[] args) {
		List<List<String>> l = metodo01(Datos.getLineas());
//		l.forEach(System.out::println);
//		System.out.println(metodo02(Datos.getLineas()));
//		System.out.println(metodo03(l));
//		metodo04(l).forEach(System.out::println);
		metodo05(Datos.getLineas()).forEach(System.out::println);
	}
	
	/*
	 * Crea un método estático que acepte una secuencia de cadenas para
	 * extraer de cada una de ellas los elementos que la forman: palabras
	 * (secuencia de uno o más caracteres alfabéticos) y no-palabras (todo lo
	 * que haya entre cada palabra) y almacenarlos en una lista. Finalmente
	 * se retornará una lista de listas que contenga todas las anteriores.
	 *
	 */

	static List<List<String>> metodo01(Stream<String> s) {
		final Pattern regex = Pattern.compile("(\\p{L}+)|(\\P{L}+)");
		return s
				.map(l -> regex.matcher(l).results().map(r -> r.group()).toList())
				.toList();
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
				.max(Integer::compare)
				.get();
	}
	
	
	/*
	 * Lo mismo que en el ejercicio anterior, pero aceptando una lista
	 * como la que retorna metodo01.
	 */
	
	static int metodo03(List<List<String>> lista) {
		return lista
				.stream()
				.mapToInt(l -> l.size())
				.max()
				.getAsInt();
	}
	
	/*
	 * Crea un método estático que acepte una lista como la que retorna
	 * el ejercicio 1 y retorne una lista de cadenas que contenga las
	 * palabras y no-palabras en orden de aparición.
	 */
	
	static List<String> metodo04(List<List<String>> lista) {
		return lista
				.stream()
				.flatMap(l -> l.stream())
				.toList();
	}
	
	/*
	 * Crea un método estático que acepte una secuencia de cadenas y
	 * retorne una lista de cadenas que contenga las palabras y no-palabras
	 * en orden de aparición.
	 */
	
	static List<String> metodo05(Stream<String> stream) {
		final Pattern regex = Pattern.compile("(\\p{L}+)|(\\P{L}+)");
		return stream
				.flatMap(l -> regex.matcher(l).results().map(r -> r.group()))
				.toList();
	}
}
