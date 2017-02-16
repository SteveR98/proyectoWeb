

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFin
 *
 */
@WebListener
public class EscuchaInicioYFin implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public EscuchaInicioYFin() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("Programa DESTRUIDO");

    	try {
    		Conexiones.desconectate_D_SSH();
    		System.out.println("Te has desconectado");
		} catch (Exception e)
    	{
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	System.out.println("Programa iniciado");
    	
    	try {
			Conexiones.conectate_A_SSH();
			System.out.println("La conexion SSH Iniciada");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
}
