package unidad6;

import java.util.function.BiFunction;

public class Actividad1 {

	public static void main(String[] args) {
		BiFunction<String, Integer, Character> a;
		a = (x, y) -> x.charAt(y);
		char c1 = a.apply("hola", 2);
		System.out.println(c1);
		
		a = String::charAt;
		char c2 = a.apply("hola", 2);
		System.out.println(c1);
		
		a = new BiFunction<>() {
			@Override
			public Character apply(String t, Integer u) {
				return t.charAt(u);
			}
		};
	}
	
}
