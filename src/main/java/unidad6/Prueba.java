package unidad6;

import java.util.function.IntFunction;

public class Prueba {

	public static void main(String[] args) {
		String s = "Hola";
		IntFunction<Character> f = s::charAt;
		f.apply(0);
	}

}
