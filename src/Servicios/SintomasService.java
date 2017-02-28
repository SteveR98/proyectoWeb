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
		String[] palabras_sintoma_descripcion ;
				
		inicial = inicial.toLowerCase();
		
		for (SintomasDTO sintoma:lista_sintomas)
		{
			sintoma_descripcion = sintoma.getDescripcion();
	        
	        palabras_sintoma_descripcion = separarFrase(sintoma_descripcion);
	        
	        for(String palabra_sintoma:palabras_sintoma_descripcion)
	        {
	        	if(palabra_sintoma.startsWith(inicial)) 
	        	{
	        		lista_descripcion.add(sintoma);
	        	}
	        }
		}
		
		return lista_descripcion;
	}
	
	
	
    public static String[] separarFrase(String frase_introducida) {

    	String delimitadores= "[ .,;?!¡¿\'\"\\[\\]]+";
    	String[] palabrasSeparadas = frase_introducida.split(delimitadores);
    	
        return palabrasSeparadas;
    }
}