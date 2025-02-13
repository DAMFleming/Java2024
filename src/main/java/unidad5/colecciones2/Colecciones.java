package unidad5.colecciones2;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Colecciones {

	public static void main(String[] args) {
		
		
		List<String> list1 = List.of("a", "b", "a", "c", "d", "e", "d", "f", "e", "d", "e", "g");
		List<String> list2 = List.of("a", "b", "a", "c", "d", "e", "d", "f" ,"e", "g");
		System.out.println(algunaSeRepiteAlMenos3Veces(list1));
		System.out.println(algunaSeRepiteAlMenos3Veces(list2));
		
//		Set<String> conjunto = new HashSet<>(Set.of("a", "aa", "aaa", "aaaa", "aaaaa"));
//		eliminarLasDeLongitudPar(conjunto);
//		System.out.println(conjunto);
//		Set<Integer>c2 = Set.of(0,2,4,10,6,20);
//		System.out.println(contieneImpares(c2));
		ArrayDeque<Integer> pila = new ArrayDeque<>();
		List.of(23, -5, 37, 29, -19, -55, 89).forEach(pila::push);
		negativosAbajoPositivosArriba(pila);
		while (!pila.isEmpty())
			System.out.println(pila.pop());
	}
	
	
	
    // -------------------------------------------------------------------------
    // ---  EJERCICIO 1                                                      ---
    // -------------------------------------------------------------------------
	
	/*
	 * Método llamado eliminarLasDeLongitudPar que acepte un conjunto
	 * de cadenas y elimine del mismo todas aquellas cadenas de una 
	 * longitud par. No se permite el uso de ninguna otra estructura de
	 * datos auxiliar.
	 */
	
	/* SOLUCIÓN 1 */
//	static void eliminarLasDeLongitudPar(Set<String> conjunto) {
//		Iterator<String> i = conjunto.iterator();
//		while (i.hasNext())
//			if (i.next().length() % 2 == 0)
//				i.remove();
//	}
	
	/* SOLUCIÓN 2 */
	static void eliminarLasDeLongitudPar(Set<String> conjunto) {
		conjunto.removeIf(s -> s.length() % 2 == 0);
	}
	
	
	
	// -------------------------------------------------------------------------
    // ---  EJERCICIO 2                                                      ---
    // -------------------------------------------------------------------------
	
	/*
	 * Método llamado contieneImpares que acepte un conjunto de números
	 * enteros y retorne true si contiene al menos un número impar o false
	 * en caso contrario.
	 */
	
	/* SOLUCIÓN 1 */
//	static boolean contieneImpares(Set<Integer> conjunto) {
//		Iterator<Integer> i = conjunto.iterator();
//		while (i.hasNext())
//			if (i.next() % 2 != 0)
//				return true;
//		return false;
//	}
	
	/* SOLUCIÓN 2 */
	static boolean contieneImpares(Set<Integer> conjunto) {
		return conjunto.stream().anyMatch(n -> n % 2 == 1);
	}
	
	
	
	// -------------------------------------------------------------------------
    // ---  EJERCICIO 3                                                      ---
    // -------------------------------------------------------------------------
	
	/*
	 * Método llamado valoresUnicos que acepte un mapa de cadenas a cadenas
	 * y retorne false si dos o más claves se asocian a un mismo valor o true
	 * en caso contrario.
	 */
	
	/* SOLUCIÓN 1 */
//	static boolean valoresUnicos(Map<String, String> map) {
//		Collection<String> valores = map.values();
//		Set<String> set = new HashSet<>(valores);
//		return set.size() == valores.size();
//	}
	
	/* SOLUCIÓN 2 */
	static boolean valoresUnicos(Map<String, String> map) {
		Collection<String> valores = map.values();
		return valores.stream().distinct().count() == valores.size();
	}
	
	
	
	// -------------------------------------------------------------------------
    // ---  EJERCICIO 4                                                      ---
    // -------------------------------------------------------------------------
	
	/*
	 * Método llamado algunaSeRepiteAlMenos3Veces que acepte una lista de
	 * cadenas como parámetro y retorne true si alguna cadena se repite al
	 * menos 3 veces en la lista o false en caso contrario.
	 * 
	 * Resolver el problema utilizando un mapa como almacenamiento auxiliar.
	 */
	
	/* SOLUCIÓN 1 */
//	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {
//		HashMap<String, Integer> m = new HashMap<>();
//		for (String s: lista) {
//			Integer contador = m.get(s);
//			if (contador == null)
//				m.put(s, 1);
//			else {
//				if (contador == 2)
//					return true;
//				m.put(s, contador + 1);
//			}
//		}
//	}
	
	/* SOLUCIÓN 2 */
//	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {
//		HashMap<String, Integer> m = new HashMap<>();
//		for (String s: lista) {
//			Integer contador = m.putIfAbsent(s, 1);
//			if (contador != null) {
//				if (contador == 2)
//					return true;
//				m.put(s, contador + 1);
//			}
//		}
//	}
	
	/* SOLUCIÓN 3 */
//	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {
//		HashMap<String, Integer> m = new HashMap<>();
//		for (String s: lista) {
//			Integer contador = m.computeIfAbsent(s, k -> 0);
//			if (contador == 2)
//					return true;
//			m.put(s, contador + 1);
//		}
//		
//		return false;
//	}
	
	/* SOLUCIÓN 4 */
//	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {
//		HashMap<String, Integer> m = new HashMap<>();
//		for (String s: lista) {
//			int contador = m.getOrDefault(s, 0);
//			if (contador == 2)
//				return true;
//			m.put(s, contador + 1);
//		}
//		return false;
//	}
	
	/* SOLUCIÓN 5 */
//	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {
//		Map<String, Integer> aux = lista.stream()
//				.collect(HashMap<String, Integer>::new,
//						(map, key) -> map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1),
//						(a, b) -> {});
//		return aux.values().stream().anyMatch(value -> value >= 3);
//	}
	
	/* SOLUCIÓN 6 */
	static boolean algunaSeRepiteAlMenos3Veces(List<String> lista) {
		return lista.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.values().stream().anyMatch(value -> value >= 3);
	}
		
	
	
	// -------------------------------------------------------------------------
    // ---  EJERCICIO 5                                                      ---
    // -------------------------------------------------------------------------
	
	/*
	 * Método llamado negativosAbajoPositivosArriba que acepte una pila
	 * de números enteros como parámetro y la reorganice para que se queden
	 * debajo los negativos y encima los positivos.
	 * 
	 * Usa una cola como estructura de datos auxiliar.
	 * 
	 * Ejemplo:
	 * 
	 *     Entrada: [23, -5, 37, 29, -19, -55, 89] <- Tope de la pila
	 *     Salida:  [-55, -19, -5, 23, 89, 29, 37] <- Tope de la pila
	 */
	
	static void negativosAbajoPositivosArriba(Deque<Integer> pila) {
		LinkedList<Integer> cola = new LinkedList<>();
		int cont = 0;
		int n;
		while (!pila.isEmpty()) {
			cola.offer(n = pila.pop());
			if (n < 0)
				cont++;
		}
		while (cont > 0) {
			n = cola.poll();
			if (n >= 0)
				cola.offer(n);
			else {
				pila.push(n);
				cont--;
			}
		}
		while (!cola.isEmpty())
			pila.push(cola.poll());
	}
	
	
	
	// -------------------------------------------------------------------------
    // ---  EJERCICIO 6                                                      ---
    // -------------------------------------------------------------------------
	
	/*
	 * Método llamado moda que acepte una lista de números enteros
	 * y retorne el número que se repite con mayor frecuencia (la moda)
	 * o 0 si la lista está vacía.
	 */
	
	/* SOLUCIÓN 1 */
//	static int moda(List<Integer> l) {
//		if (l.isEmpty())
//			return 0;
//		
//		Map<Integer, Integer> mapa = new HashMap<>();
//		for (Integer num : l) {
//			Integer contador = mapa.get(num);
//			if (contador == null)
//				contador = 0;
//			mapa.put(num, contador + 1);
//		}
//		int maxValor = Integer.MIN_VALUE;
//		int moda = 0;
//		for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
//			if (entry.getValue() > maxValor) {
//				maxValor = entry.getValue();
//				moda = entry.getKey();
//			}
//		}
//		return moda;
//	}
	
	/* SOLUCIÓN 2 */
//	static int moda(List<Integer> l) {
//		if (l.isEmpty())
//			return 0;
//		
//		Map<Integer, Integer> mapa = new HashMap<>();
//		for (Integer num : l) {
//			Integer contador = mapa.get(num);
//			mapa.put(num, contador == null ? 1 : contador + 1);
//		}
//		
//		int maxValor = Integer.MIN_VALUE;
//		int moda = 0;
//		for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
//			if (entry.getValue() > maxValor) {
//				maxValor = entry.getValue();
//				moda = entry.getKey();
//			}
//		}
//		return moda;
//	}
	
	/* SOLUCIÓN 3 */
//	static int moda(List<Integer> l) {
//		if (l.isEmpty())
//			return 0;
//		Map<Integer, Integer> mapa = new HashMap<>();
//		for (Integer num : l)
//			mapa.put(num, mapa.containsKey(num) ? mapa.get(num) + 1 : 1);
//		int maxValor = Integer.MIN_VALUE;
//		int moda = 0;
//		for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
//			if (entry.getValue() > maxValor) {
//				maxValor = entry.getValue();
//				moda = entry.getKey();
//			}
//		}
//		return moda;
//	}
	
	/* SOLUCIÓN 4 */
//	static int moda(List<Integer> l) {
//		if (l.isEmpty())
//			return 0;
//		Map<Integer, Integer> mapa = new HashMap<>();
//		for (Integer num : l)
//			mapa.put(num, mapa.getOrDefault(num, 0) + 1);
//		int maxValor = Integer.MIN_VALUE;
//		int moda = 0;
//		for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
//			if (entry.getValue() > maxValor) {
//				maxValor = entry.getValue();
//				moda = entry.getKey();
//			}
//		}
//		return moda;
//	}
	
	/* SOLUCIÓN 5 */
	static int moda(List<Integer> l) {
		if (l.isEmpty())
			return 0;
		Map<Integer, Long> mapa = l.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return mapa.entrySet().stream().max((e1, e2) -> e1.getValue().intValue() - (int) e2.getValue().intValue()).get().getKey();
	}
	
}
