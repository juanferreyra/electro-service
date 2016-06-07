package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.InsumoStockDTO;
import dto.RepuestoDTO;
import persistencia.dao.ItemStockDAO;
import persistencia.dao.RepuestoDAO;

public class Stock {

	private static ItemStockDAO itemStockDAO;
	ArrayList<InsumoStockDTO> stockFiltrado = new ArrayList<InsumoStockDTO>();

	public Stock() {

		Stock.itemStockDAO = new ItemStockDAO();

	}

	public void agregarRepuesto(RepuestoDTO nuevoComponente) {
		itemStockDAO.insert(nuevoComponente);
	}

	public void borrarRepuestoDelStock(int id_componente_a_eliminar) {
		itemStockDAO.delete(id_componente_a_eliminar);
	}

	public boolean modificarStock(List<InsumoStockDTO> insumosGastados) {

		return true;
	};

	public ArrayList<InsumoStockDTO> levantarStock() {

		return itemStockDAO.readAll();
	}

	public void modificarStock(long repuestoID, int variacion) {
		itemStockDAO.update(repuestoID, variacion);
	}

	private ArrayList<InsumoStockDTO> cargarPedidos(ArrayList<InsumoStockDTO> insumosStock) {

		return itemStockDAO.cargarInsumosPedidos(insumosStock);
	}

	public ArrayList<InsumoStockDTO> obtenerStock() {
		ArrayList<InsumoStockDTO> stock = new ArrayList<InsumoStockDTO>();
		stock = levantarStock();
		System.out.println(stock.size());
		stock = cargarReservados(stock);
		stock = cargarPedidos(stock);
		cargarAlert(stock);

		return stock;
	}
	private void cargarAlert(ArrayList<InsumoStockDTO> insumosStock){
		for (InsumoStockDTO i : insumosStock) {
			if ((i.getRestante()<1) && (i.getRestante()+i.getSolicitada())<i.getMinimo()){
				i.setAlarma(1);//ROJO FUERTE
			}else if((i.getRestante()<1) && (i.getRestante()+i.getSolicitada())>i.getMinimo()){
				i.setAlarma(2);//ROJO TENUE
			}else if((i.getRestante()<i.getMinimo()) && (i.getRestante()+i.getSolicitada())<i.getMinimo()){
				i.setAlarma(3);//AMARILLO FUERTE
			}else if((i.getRestante()<i.getMinimo()) && (i.getRestante()+i.getSolicitada())>i.getMinimo()){
				i.setAlarma(4);//AMARILLO TENUE
			}else{
				i.setAlarma(0);//NORMAL
			}
		}
	}
	

	public ArrayList<InsumoStockDTO> cargarReservados(ArrayList<InsumoStockDTO> insumosStock) {
		return itemStockDAO.CargarInsumosReservados(insumosStock);
	}

	public ArrayList<InsumoStockDTO> getFiltrado() {

		ArrayList<InsumoStockDTO> filtrado = new ArrayList<InsumoStockDTO>();
		ArrayList<InsumoStockDTO> todos = obtenerStock();
		for (InsumoStockDTO i : todos) {
			if (i.getAlarma() == 1 || i.getAlarma() == 3) {
				filtrado.add(i);
			}
		}

		return filtrado;
	}

	public boolean esRojo(String repuestoName) {
		ArrayList<InsumoStockDTO> filtrado = getFiltrado();
		for (int i = 0; i < filtrado.size(); i++) {
			if (filtrado.get(i).getNombre().equals(repuestoName)) {
				if (filtrado.get(i).getAlarma() == 1) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
