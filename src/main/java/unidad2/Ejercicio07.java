package unidad2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * Crea un método que acepte tres números enteros que representen la hora,
 * minutos y segundos de un instante cualquiera. A partir de estos datos
 * deberá retornar una cadena que contenga la hora correspondiente al segundo
 * siguiente con el formato “HH:MM:SS”.
 *
 */


public class Ejercicio07 {

	public static void main(String[] args) {
		LocalTime time = LocalTime.now();
		System.out.println(time.format(DateTimeFormatter.ofPattern("h:m:s")));
		int h = time.getHour();
		int m = time.getMinute();
		int s = time.getSecond();
		String nuevaHora = actualizarHora(h, m, s);
		System.out.println(nuevaHora);
	}
	
	static String actualizarHora(int h, int m, int s) {
		if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59)
			return null;
		else {
			if (++s == 60) {
				s = 0;
				if (++m == 60) {
					m = 0;
					if (++h == 24)
						h = 0;
				}
			}
			return String.format("%02d:%02d:%02d", h, m, s);
		}
	}

}
