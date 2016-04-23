/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author jferreyra
 */

import modelo.Presupuesto;
import presentacion.controlador.VistaControlador;
import presentacion.vista.Vista;


public class Main 
{

	public static void main(String[] args) 
	{
		Vista vista = new Vista();
		Presupuesto modelo = new Presupuesto();
		VistaControlador controlador = new VistaControlador(vista, modelo);
		controlador.inicializar();
	}
}