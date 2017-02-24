package Servicios;

import java.util.ArrayList;
import java.util.List;

import DTO.ListadoSintomas;
import DTO.SintomasDTO;


public class SintomasService {

	public List<SintomasDTO> buscarSintomaPorInicial(String inicial){
		List<SintomasDTO> lista_descripcion = new ArrayList<SintomasDTO>();
		List<SintomasDTO> lista_sintomas = ListadoSintomas.listaSintomasCompleta();
		String sintoma_descripcion = null;
		
		inicial = inicial.toLowerCase();
		
		for (SintomasDTO sintoma:lista_sintomas)
		{
			sintoma_descripcion = sintoma.getDescripcion();
	        
	        if(sintoma_descripcion.startsWith(inicial)) 
	        {
	        	lista_descripcion.add(sintoma);
	        }
		}
		
		return lista_descripcion;
	}
	
	public static void imprimirLista(List<String> lista_descripcion)
	{
		for(String descripcion:lista_descripcion)
		{
			System.out.println(descripcion);
		}
	}
	
}