package unidad6.streams.ejercicio1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class Ejercicio1 {

	public static void main(String[] args) {
//		metodo01(Datos.getPalabras());
//		metodo02(Datos.getPalabras())
//			.entrySet()
//			.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
		metodo04(Datos.getPalabras()).entrySet().forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
//		System.out.println(metodo05(Datos.getPalabras()));

//		metodo07(Datos.getPalabras()).forEach(System.out::println);
//		metodo07(Datos.getPalabras()).forEach(palabra -> System.out.println(palabra));
//		System.out.println(metodo06facil(Datos.getPalabras()));
//		System.out.println(metodo06(Datos.getPalabras()));
//		metodo08(Datos.getPalabras(), 'z').entrySet()
//				.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
//		metodo08dificil(Datos.getPalabras(), 'z')
//				.entrySet()
//				.forEach(e -> System.out.println((e.getKey() ? "Par: " : "Impar: ") + e.getValue()));
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y muestre en la
	 * consola las palabras que empiecen por 'k', 'ñ', 'w' 'x' o 'y'.
	 */
	static void metodo01(Stream<String> palabras) {
		palabras.filter(palabra -> palabra.matches("[kñwxy].*")).forEach(System.out::println);
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y retorne el
	 * resultado de agrupar las palabras de longitud mayor que 3 que comiencen por
	 * los mismos 3 caracteres.
	 */
	static Map<String, List<String>> metodo02(Stream<String> palabras) {
		return palabras.filter(palabra -> palabra.length() > 3).collect(Collectors.groupingBy(p -> p.substring(0, 3)));
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y una longitud y
	 * muestre en la consola todos los palíndromos de esa longitud. Si la longitud
	 * es menor que 3 se lanzará la excepción IllegalArgumentException.
	 */
	static void metodo03(Stream<String> palabras, int longitud) {
		if (longitud < 3)
			throw new IllegalArgumentException();
		palabras.filter(palabra -> palabra.length() == longitud).filter(Ejercicio1::esPalindromo)
				.forEach(System.out::println);
	}

	static boolean esPalindromo(String s) {

		return true;
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y retorne por
	 * cada inicial presente en la secuencia, el número de palabras que comienzan
	 * por ella. Como posibles iniciales sólo se considerarán las letras de la 'a' a
	 * la 'z' (minúsculas). Las vocales con tilde se considerarán como vocales sin
	 * tilde.
	 */

	static Map<Character, Long> metodo04(Stream<String> palabras) {
		return palabras.collect(
				Collectors.groupingBy(p -> StringUtils.stripAccents(p.toLowerCase()).charAt(0), Collectors.counting()));
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y retorne la
	 * longitud de la palabra o palabras mas largas.
	 */
	static int metodo05(Stream<String> palabras) {
		return palabras.mapToInt(palabra -> palabra.length()).max().orElse(0);
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y agrupe las
	 * palabras por longitud.
	 */
	static Map<Integer, List<String>> metodo06facil(Stream<String> palabras) {
		return palabras.collect(Collectors.groupingBy(String::length));
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y retorne el
	 * número de palabras de cada longitud presente en la secuencia.
	 */
	static Map<Integer, Long> metodo06(Stream<String> palabras) {
		return palabras.collect(Collectors.groupingBy(String::length, Collectors.counting()));
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y retorne una
	 * LinkedList que contenga las 20 palabras más largas de la secuencia.
	 */
	static List<String> metodo07(Stream<String> palabras) {
		return palabras.sorted((s1, s2) -> s2.length() - s1.length()).limit(20).toList();
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y una letra y
	 * retorne el resultado de particionar el conjunto de palabras que comienzan por
	 * esa letra, separando las de longitud par de las de longitud impar.
	 */
	static Map<Boolean, List<String>> metodo08(Stream<String> palabras, char letra) {
		return palabras.filter(palabra -> palabra.charAt(0) == letra)
				.collect(Collectors.partitioningBy(palabra -> palabra.length() % 2 == 0));
	}

	/*
	 * Crea un método estático que acepte una secuencia de palabras y una letra y
	 * retorne el el número de palabras que empiecen por esa letra de longitud par y
	 * de longitud impar.
	 */
	static Map<Boolean, Long> metodo08dificil(Stream<String> palabras, char letra) {
		return palabras.filter(palabra -> palabra.charAt(0) == letra)
				.collect(Collectors.partitioningBy(palabra -> palabra.length() % 2 == 0, Collectors.counting()));
	}
}
