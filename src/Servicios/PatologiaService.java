package Servicios;

import DAO.PatologiaDAO;
import DTO.MapaPatologias;
import DTO.PatologiaDTO;
//import dto.MapaPatologias;

public class PatologiaService {

	
	public PatologiaDTO buscarPatologiaPorID (int id)
	{
PatologiaDTO patoDTO = null;
		
		//VERSIÓN 1 .- Accediendo a la base de datos cada vez
			PatologiaDAO pdao = new PatologiaDAO();
			patoDTO = pdao.buscarPorId(id);
		
			System.out.println(patoDTO.toString());
		//FIN VERSIÓN 1 .- Accediendo a la base de datos cada vez
			
		//VERSIÓN 2 .- Accediendo al Mapa precargado con Todas las patlogías
			
//			patoDTO = MapaPatologias.getPatologia (id);
			
		//FIN VERSIÓN 2 .- Accediendo al Mapa precargado con Todas las patlogías
			
		return patoDTO;
	}
}