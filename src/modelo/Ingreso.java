package modelo;

import java.util.ArrayList;
import java.util.List;
import dto.IngresoDTO;
import dto.ClienteDTO;
import dto.ImpresionIngresoDTO;
import dto.MarcaDTO;
import dto.TipoProductoDTO;
import dto.ItemIngresoLogDTO;
import dto.IngresoLogDTO;
import persistencia.dao.ClienteDAO;
import persistencia.dao.ImpresionIngresoDAO;
import persistencia.dao.IngresoDAO;
import persistencia.dao.MarcaDAO;
import persistencia.dao.TipoProductoDAO;
import persistencia.dao.IngresoLogDAO;

public class Ingreso {
	public int id;
	public ClienteDTO clien;
	public IngresoDTO ingr;
	public MarcaDTO marc;
	public TipoProductoDTO tipoprod;
	public ArrayList<ItemIngresoLogDTO> logIngresos;
	
	private static ImpresionIngresoDAO ingresoStatic;
	
	//Recuperacion de datos con DAO's
	private IngresoDAO ingreso;
	private MarcaDAO marca;
	private TipoProductoDAO tipoproducto;
	private ClienteDAO cliente;
	private IngresoLogDAO log_ingresos;
	
	public Ingreso() {
		this.id = -1;
		this.ingreso = new IngresoDAO();
		this.marca = new MarcaDAO();
		this.tipoproducto = new TipoProductoDAO();
		this.cliente = new ClienteDAO();
		this.log_ingresos = new IngresoLogDAO();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void cargarModeloCompleto() {
		//cargo el ingreso
		this.setIngreso(ingreso.find(id));
		//cargoElCliente
		this.setCliente(cliente.find(ingr.getIdcliente()));
		//cargo la marca y tipo producto
		this.setMarca(marca.find(ingr.getIdmarca()));
		this.setTipoproducto(tipoproducto.find(ingr.getIdtipo_producto()));
		this.setLogIngresos(log_ingresos.find(id));
	}
	
	public Boolean guardarIngreso(int idusuario)
	{	
		Boolean ingreso = true;
		//guardo todos los objetos DTO con su respectivo DAO
		
		//busco el siguiente id de la tabla ingreso a insertar
		try {
			int ingreso_id = this.ingreso.getNextId();
			ingr.setId(ingreso_id);
			if(ingreso_id!=-1)
			{
				ingr.setId(ingreso_id);
				IngresoLogDTO ingrLog = new IngresoLogDTO(0, ingreso_id, 1, null, 0);
				this.log_ingresos.insert(ingrLog);
				this.ingreso.insert(ingr);
				
			}
		} catch (Exception e) {
			ingreso = false;
		}
		return ingreso;
	}
	
	public IngresoDTO getIngreso() {
		return ingr;
	}
	
	private void setIngreso(IngresoDTO ingreso) {
		ingr = ingreso;
	}

	public ClienteDTO getCliente() {
		return clien;
	}

	public void setCliente(ClienteDTO clien) {
		this.clien = clien;
	}

	public MarcaDTO getMarca() {
		return marc;
	}

	public void setMarca(MarcaDTO marc) {
		this.marc = marc;
	}

	public TipoProductoDTO getTipoproducto() {
		return tipoprod;
	}

	public void setTipoproducto(TipoProductoDTO tipoprod) {
		this.tipoprod = tipoprod;
	}
	
	public List<MarcaDTO> obtenerMarcas() {
		return marca.readAll();
	}
	
	public List<TipoProductoDTO> obtenerTiposProductos() {
		return tipoproducto.readAll();
	}

	public ArrayList<ItemIngresoLogDTO> getLogIngresos() {
		return logIngresos;
	}

	public void setLogIngresos(ArrayList<ItemIngresoLogDTO> logIngresos) {
		this.logIngresos = logIngresos;
	}
	
	public ArrayList<ImpresionIngresoDTO> getTodos(){
		ingresoStatic = new ImpresionIngresoDAO();
		return ingresoStatic.find(this.ingr.getId());
	}

}