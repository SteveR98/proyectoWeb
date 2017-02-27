package Servicios;


import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import DAO.PatologiasDAO;
import DAO.SintomasDAO;
import DAO.conexionssh;
import DTO.ListadoSintomas;
import DTO.MapaPatologias;
import DTO.PatologiasDTO;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFin
 *
 */
@WebListener
public class EscuchaInicioFin implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public EscuchaInicioFin() {
        // TODO Auto-generated constructor stub
    }
    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("PROGRAMA DESTRUIDO");
    	System.out.println("PROGRAMA DESTRUIDO");
    	System.out.println("PROGRAMA DESTRUIDO");
    	System.out.println("PROGRAMA DESTRUIDO");
    	
    	conexionssh.desconectate_D_SSH();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("PROGRAMA INICIADO");
    	System.out.println("PROGRAMA INICIADO");
    	System.out.println("PROGRAMA INICIADO");
    	System.out.println("PROGRAMA INICIADO");
    	try {
			
    		conexionssh.conectate_A_SSH();
    		System.out.println("conectandome a la base de datos");
    		
			PatologiasDAO patologiaDAO = new PatologiasDAO();
			Map<Integer, PatologiasDTO> mapa_patDto = patologiaDAO.obtenerListaPalogias();
			
			SintomasDAO sintomasDAO = new SintomasDAO();
			ArrayList lista_sintomas =  (ArrayList) sintomasDAO.obtenerTodosSintomas();
			
				MapaPatologias mapaPatologias = new MapaPatologias();
				mapaPatologias.setMapapatologia(mapa_patDto);
				
				ListadoSintomas listadoSintomas = new ListadoSintomas();
				listadoSintomas.setMapapatologia(lista_sintomas);
				
				System.out.println("Mapa Inicializado");
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
}
