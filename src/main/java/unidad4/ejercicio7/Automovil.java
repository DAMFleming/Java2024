package unidad4.ejercicio7;

public class Automovil {
	private String modelo;
	private float capacidad;	//capacidad del deposito en litros
	private float cantidad;		//cantidad de litros en deposito
	private float consumo;		//consumo por kilometros en litros
	private float km;			//kilometros recorridos
	private float consumido;	//combustible consumido
	
	public Automovil(String modelo, float capacidad, float cantidad, float consumo, float kmRec, float cmbCons) {
		this.modelo = modelo;
		this.capacidad = capacidad;
		this.cantidad = cantidad;
		this.consumo = consumo;
		this.km = kmRec;
		this.consumido = cmbCons;
	}

	public Automovil(String modelo, float capacidad, float consumo) {
		this.modelo = modelo;
		this.capacidad = capacidad;
		this.consumo = consumo;
		llenarDeposito();
	}
	
	public void llenarDeposito() {
		cantidad=capacidad;
	}
	
	public float llenarDeposito(float n) {
		cantidad=n+cantidad;
		float sobrante= cantidad-capacidad;
		if(sobrante <=0)
			return 0;
		cantidad=capacidad;
		return sobrante;
	}

	public float getConsumo() {
		return consumo;
	}

	public float getKilometros() {
		return km;
	}

	public String getModelo() {
		return modelo;
	}

	public float getCapacidad() {
		return capacidad;
	}

	public float getCantidad() {
		return cantidad;
	}

	public float getConsumido() {
		return consumido;
	}

	@Override
	public String toString() {
		return String.format("Automovil [modelo=%s, capacidad=%s, cantidad=%s, consumo=%s, km=%s, consumido=%s]",
				modelo, capacidad, cantidad, consumo, km, consumido);
	}
	
	
}
