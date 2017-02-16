package Servicios;

	import javax.servlet.ServletContextEvent;
	import javax.servlet.ServletContextListener;
	import javax.servlet.annotation.WebListener;

	/**
	* Application Lifecycle Listener implementation class EscuchaInicioFin
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
	        // TODO Auto-generated method stub
	    System.out.println("Programa destruido");
	    System.out.println("Programa destruido");
	    System.out.println("Programa destruido");
	    
	    Principal.desconectate_D_SSH();
	   }

	/**
	    * @see ServletContextListener#contextInitialized(ServletContextEvent)
	    */
	   public void contextInitialized(ServletContextEvent arg0)  { 
	        // TODO Auto-generated method stub
	    System.out.println("Programa iniciado");
	    System.out.println("Programa iniciado");
	    System.out.println("Programa iniciado");
	    
	    try {
			Principal.conectate_A_SSH();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	
	   }


}
