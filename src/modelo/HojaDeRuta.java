package modelo;

import java.util.ArrayList;

import dto.FleteDTO;
import dto.HojaDeRutaDTO;
import dto.HojaDeRutaIngresosDTO;
import persistencia.dao.ClienteDAO;
import persistencia.dao.FleteDAO;
import persistencia.dao.HojaDeRutaDAO;
import persistencia.dao.HojaDeRutaIngresosDAO;
import persistencia.dao.IngresoDAO;

public class HojaDeRuta {
	private int id;
	private HojaDeRutaDTO hojaRuta;
	private FleteDTO flete;
	private ArrayList<HojaDeRutaIngresosDTO> ingresosEnHoja;
	
	private HojaDeRutaIngresosDAO hojaRutaIngresosDAO;
	private HojaDeRutaDAO hojaRutaDAO;
	private FleteDAO fleteDAO;
	private IngresoDAO ingresoDAO;
	private ClienteDAO clienteDAO;
	
	public HojaDeRuta() {
		//DAOS
		hojaRutaIngresosDAO = new HojaDeRutaIngresosDAO();
		hojaRutaDAO = new HojaDeRutaDAO();
		fleteDAO = new FleteDAO();
		ingresoDAO = new IngresoDAO();
		clienteDAO = new ClienteDAO();
		
		//Variables Locales
		id = -1;
		ingresosEnHoja = new ArrayList<HojaDeRutaIngresosDTO>();
		flete = null;
	}
	
	public void cargarVariables() {
		hojaRuta = hojaRutaDAO.find(id);
		ingresosEnHoja = hojaRutaIngresosDAO.findAll(id);
		flete = fleteDAO.find(hojaRuta.getFleteId());
	}
	
	public HojaDeRutaIngresosDAO getHojaRutaIngresosDAO() {
		return hojaRutaIngresosDAO;
	}

	public void setHojaRutaIngresosDAO(HojaDeRutaIngresosDAO hojaRutaIngresosDAO) {
		this.hojaRutaIngresosDAO = hojaRutaIngresosDAO;
	}

	public HojaDeRutaDAO getHojaRutaDAO() {
		return hojaRutaDAO;
	}

	public void setHojaRutaDAO(HojaDeRutaDAO hojaRutaDAO) {
		this.hojaRutaDAO = hojaRutaDAO;
	}

	public FleteDAO getFleteDAO() {
		return fleteDAO;
	}

	public void setFleteDAO(FleteDAO fleteDAO) {
		this.fleteDAO = fleteDAO;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HojaDeRutaDTO getHojaRuta() {
		return hojaRuta;
	}

	public void setHojaRuta(HojaDeRutaDTO hojaRuta) {
		this.hojaRuta = hojaRuta;
	}

	public ArrayList<HojaDeRutaIngresosDTO> getIngresosEnHoja() {
		return ingresosEnHoja;
	}

	public void setIngresosEnHoja(ArrayList<HojaDeRutaIngresosDTO> ingresosEnHoja) {
		this.ingresosEnHoja = ingresosEnHoja;
	}

	public FleteDTO getFlete() {
		return flete;
	}

	public void setFlete(FleteDTO flete) {
		this.flete = flete;
	}

	public IngresoDAO getIngresoDAO() {
		return ingresoDAO;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}
}
