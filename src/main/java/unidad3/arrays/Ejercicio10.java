package unidad3.arrays;

import java.util.Arrays;

/*
 * Programa que cree una matriz de números enteros aleatorios con un número
 * de filas y de columnas aleatorios que estén comprendidos entre 2 y 20.
 * 
 * Escribir un método que reciba una matriz de números enteros y retorne un
 * vector que contenga la suma de los valores de cada fila.
 * 
 * Escribir otro método que reciba una matriz de números enteros y retorne un
 * vector que contenga la suma de los valores de cada columna.
 * 
 * Mostrar en la consola la matriz, la suma de cada fila a su derecha y la suma
 * de cada columna debajo de ella.
 * 
 */

public class Ejercicio10 {
	
//	public static void main(String[] args) {
//		Random r = new Random();
//		int [][] m;
//		int filas = r.nextInt(19) + 2;
//		int columnas = r.nextInt(19) + 2;
//		m = new int[filas][columnas];
//		for (int i=0; i<filas; i++)
//			for (int j=0; j<columnas; j++)
//				m[i][j] = r.nextInt(1000);
//	}
	
	public static void main(String[] args) {
//		Random r = new Random();
//		int [][] m = new int[r.nextInt(19) + 2][r.nextInt(19) + 2];
//		for (int i=0; i<m.length; i++)
//			for (int j=0; j<m[i].length; j++)
//				m[i][j] = r.nextInt(1000);
		
		int [][] m = {{1, 2, 3, 4}, {5, 6, 7, 8}, {1, 1, 1, 1}};
		System.out.println(Arrays.toString(sumaColumnas(m)));
		System.out.println(Arrays.toString(sumaFilas(m)));
		int [][] sfc = sumaFilasColumnas(m);
		System.out.println(Arrays.toString(sfc[0]));
		System.out.println(Arrays.toString(sfc[1]));
	}

//	static int [] sumaFilas(int [][] m) {
//		int [] v = new int[m.length];
//		int suma;
//		for (int i=0; i<m.length; i++) {
//			suma = 0;
//			for(int j=0; j<m[i].length; j++) {
//				suma += m[i][j];
//			}
//			v[i] = suma;
//		}
//		return v;
//	}
	
	static int [] sumaFilas(int [][] m) {
		if (m.length == 0)
			return null;
		else {
			int [] v = new int[m.length];
			for (int f=0; f<m.length; f++)
				for(int c=0; c<m[f].length; c++)
					v[f] += m[f][c];
			return v;
		}
	}
	
//	static int [] sumaColumnas(int [][] m) {
//		if (m.length == 0)
//			return null;
//		else {
//			int [] v = new int[m[0].length];
//			for (int c=0; c<m[0].length; c++)
//				for (int f=0; f<m.length; f++)
//					v[c] += m[f][c];
//			return v;
//		}
//	}
	
	static int [] sumaColumnas(int [][] m) {
		if (m.length == 0)
			return null;
		else {
			int [] v = new int[m[0].length];
			for (int f=0; f<m.length; f++)
				for (int c=0; c<m[f].length; c++)
					v[c] += m[f][c];
			return v;
		}
	}
	
	static int[][] sumaFilasColumnas(int [][] m) {
		if (m.length == 0)
			return null;
		else {
			int [][] s = new int[2][];
			s[0] = new int[m.length];
			s[1] = new int[m[0].length];
			for (int f=0; f<m.length; f++)
				for (int c=0; c<m[f].length; c++) {
					s[0][f] += m[f][c];
					s[1][c] += m[f][c];
				}
			return s;
		}		
	}
	
}
