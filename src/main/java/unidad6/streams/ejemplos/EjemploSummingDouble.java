package unidad6.streams.ejemplos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EjemploSummingDouble {

	static class Venta {
		int id;
		LocalDate fecha;
		double importe;
		
		public Venta(int id, LocalDate fecha, double importe) {
			this.id = id;
			this.fecha = fecha;
			this.importe = importe;
		}

		public int getId() {
			return id;
		}

		public LocalDate getFecha() {
			return fecha;
		}

		public double getImporte() {
			return importe;
		}
		
	}
	
	public static void main(String[] args) {
		List<Venta> ventas = List.of(
				new Venta(1, LocalDate.of(2024, 1, 3), 102.45),
				new Venta(2, LocalDate.of(2025, 1, 13), 32.25),
				new Venta(1, LocalDate.of(2025, 1, 23), 71.99)
		);
		
		double total = ventas
			.stream()
			.filter(v -> v.fecha.getYear() == LocalDate.now().getYear())
			.collect(Collectors.summingDouble(Venta::getImporte));
		
		System.out.println(total);
	}
	
}
