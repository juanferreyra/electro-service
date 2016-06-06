package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.InsumoStockDTO;
import dto.RepuestoDTO;
import persistencia.dao.ItemStockDAO;
import persistencia.dao.RepuestoDAO;

public class Stock {

private static ItemStockDAO itemStockDAO;
	
	public Stock (){
		
		Stock.itemStockDAO = new ItemStockDAO();
		
	}
	
	public void agregarRepuesto(RepuestoDTO nuevoComponente) {
		itemStockDAO.insert(nuevoComponente);
	}

	public void borrarRepuestoDelStock(int id_componente_a_eliminar) {
		itemStockDAO.delete(id_componente_a_eliminar);
	}
	
	public boolean modificarStock (List<InsumoStockDTO> insumosGastados ){
	
		
		return true;
	}; 
	
	public ArrayList<InsumoStockDTO> levantarStock() {
		
		return itemStockDAO.readAll();		
	}
	
	public void  modificarStock(long repuestoID, int variacion) {
		itemStockDAO.update(repuestoID, variacion);
	}
	
	public  ArrayList<InsumoStockDTO> cargarPedidos(ArrayList<InsumoStockDTO> insumosStock ) {
		
		return itemStockDAO.cargarInsumosPedidos(insumosStock);		
	}
		
	public ArrayList<InsumoStockDTO> obtenerStock() {
		ArrayList<InsumoStockDTO> stock = new  ArrayList<InsumoStockDTO>();
		stock = itemStockDAO.readAll();
		System.out.println(stock.size());
		stock = cargarReservados(stock);
		stock = cargarPedidos(stock);
		
		
		return stock;
	}
	
	public ArrayList<InsumoStockDTO> cargarReservados(ArrayList<InsumoStockDTO> insumosStock ){
	return itemStockDAO.CargarInsumosReservados(insumosStock);	
	}
}
