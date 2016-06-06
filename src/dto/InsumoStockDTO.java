package dto;

public class InsumoStockDTO {

	private int ID;
	private long InsumoID;
	private int existencias;
	private int aUsar;
	private int restante; 
	private int solicitada;
	private int alarma;
	private String marca;
	private String nombre;
	private int minimo;
	
	public InsumoStockDTO(int iD, long insumoID, int existencias, int aUsar, int restante, int solicitada, String marca,
			String nombre, int minimo, int alarma) {
		super();
		ID = iD;
		InsumoID = insumoID;
		this.existencias = existencias;
		this.aUsar = aUsar;
		this.restante = restante;
		this.solicitada = solicitada;
		this.marca = marca;
		this.nombre = nombre;
		this.minimo = minimo;
		this.alarma = alarma;
	}
	
	public InsumoStockDTO(int iD, long insumoID, int existencias) {
		super();
		ID = iD;
		InsumoID = insumoID;
		this.existencias = existencias;
		this.aUsar = 0;
		this.restante = 0;
		this.solicitada = 0;
		this.marca = "Marca";
		this.nombre = "Nombre";
		this.minimo = 0;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public long getInsumoID() {
		return InsumoID;
	}

	public void setInsumoID(long insumoID) {
		InsumoID = insumoID;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public int getaUsar() {
		return aUsar;
	}

	public void setaUsar(int aUsar) {
		this.aUsar = aUsar;
	}

	public int getRestante() {
		return restante;
	}

	public void setRestante(int restante) {
		this.restante = restante;
	}

	public int getSolicitada() {
		return solicitada;
	}

	public void setSolicitada(int solicitada) {
		this.solicitada = solicitada;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getAlarma() {
		return alarma;
	}

	public void setAlarma(int alarma) {
		this.alarma = alarma;
	}
	
	
}
