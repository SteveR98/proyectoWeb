package DTO;
import java.util.HashMap;
import java.util.Map;


public class MapaPatologias {
	
	private static Map<Integer, PatologiaDTO> mapapatologia;

	public Map<Integer, PatologiaDTO> getMapapatologia() {
		return mapapatologia;
	}

	public void setMapapatologia(Map<Integer, PatologiaDTO> mapapatologia) {
		MapaPatologias.mapapatologia = mapapatologia;
	}

	public MapaPatologias() {
		super();
		mapapatologia = new HashMap<Integer, PatologiaDTO>();
	}
	
	public void addPatologia (Integer id, PatologiaDTO pdto)
	{
		mapapatologia.put(id, pdto);
	}
	
	public static PatologiaDTO getPatologia (Integer id)
	{
		return mapapatologia.get(id);
	}
	
	

}
