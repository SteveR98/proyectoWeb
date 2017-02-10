package proyectoControler;

import DAO.PatologiasDAO;
import DTO.PatologiasDTO;

public class PatologiaService {

	public static PatologiasDTO buscarPatologiasPorID(int id) {
		PatologiasDTO patoDTO = null;
		PatologiasDAO patoDAO = new PatologiasDAO();

		patoDTO = patoDAO.getPatologiasPorID(id);

		return patoDTO;
	}

	public static PatologiasDTO buscarPatologiasPorNombre(String nombre) {

		return null;
	}
	
/*		
		public PatologiasDTO obtenerPatologiaPorID(int id){
			PatologiasDTO patologiaBuscada = new PatologiasDTO();
			PatologiasDAO patologiasDao = new PatologiasDAO();
			
				patologiaBuscada = (PatologiasDTO) patologiasDao.buscarPatologiaPorID(id);
			
			return patologiaBuscada;
		}*/

	}

