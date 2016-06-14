package modelo;

import java.util.ArrayList;
import dto.FleteDTO;
import dto.HojaDeRutaDTO;
import dto.HojaDeRutaIngresosDTO;
import dto.ImpresionHojaDeRutaDTO;
import dto.IngresoDTO;
import dto.UsuarioDTO;
import persistencia.dao.ClienteDAO;
import persistencia.dao.FleteDAO;
import persistencia.dao.HojaDeRutaDAO;
import persistencia.dao.HojaDeRutaIngresosDAO;
import persistencia.dao.ImpresionHojaDeRutaDAO;
import persistencia.dao.IngresoDAO;

public class HojaDeRuta {
	private int id;
	private HojaDeRutaDTO hojaRuta;
	private FleteDTO flete;
	private ArrayList<HojaDeRutaIngresosDTO> ingresosEnHoja;
	
	private static ImpresionHojaDeRutaDAO impresionHojasRutasStatic;
	
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
	
	public ArrayList<HojaDeRutaDTO> readAll(){
		return hojaRutaDAO.readAll();
	}
	
	public void cargarVariables() {
		if(id!=-1) {
			hojaRuta = hojaRutaDAO.find(id);
			ingresosEnHoja = hojaRutaIngresosDAO.findAll(id);
			flete = fleteDAO.find(hojaRuta.getFleteId());
		} else {
			ingresosEnHoja = new ArrayList<HojaDeRutaIngresosDTO>();
			flete = null;
		}
	}
	
	public boolean existeHojaDeRuta(int id) {
		if(hojaRutaDAO.find(id) == null) {
			return false;	
		}
		return true;
	}
	
	public ArrayList<IngresoDTO> getIngresosSeleccionadosEnHoja() {
		ArrayList<IngresoDTO> ingresos = new ArrayList<IngresoDTO>();
		
		for (int i = 0; i < ingresosEnHoja.size(); i++) {
			ingresos.add(ingresoDAO.find(ingresosEnHoja.get(i).getIdIngreso()));
		}
		
		return ingresos;
	}
	
	public ArrayList<IngresoDTO> getIngresosReparados() {
		return this.ingresoDAO.readReparadosNoReparados();
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

	public void cargarFlete(int nro) {
		this.flete = this.fleteDAO.findPorNrodoc(nro);
	}

	public Boolean guardarModelo(UsuarioDTO usuarioLogueado) {
		try {
			int next = hojaRutaDAO.getNextId();
			this.hojaRuta = new HojaDeRutaDTO(next, flete.getId(), null, usuarioLogueado.getId());
			this.hojaRutaDAO.insert(this.hojaRuta);
			
			for (int i = 0; i < ingresosEnHoja.size(); i++) {
				this.ingresosEnHoja.get(i).setIdHojaDeRuta(next);
				this.hojaRutaIngresosDAO.insert(ingresosEnHoja.get(i));
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public ArrayList<ImpresionHojaDeRutaDTO> getDatosImpresion(){
		impresionHojasRutasStatic = new ImpresionHojaDeRutaDAO();
		return impresionHojasRutasStatic.find(this.hojaRuta.getId());
	}
}
