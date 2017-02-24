

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Servicios.PatologiaService;

/**
 * Servlet implementation class BuscarPatologiasPorSintoma
 */
@WebServlet("/BuscarPatologiasPorSintoma")

public class BuscarPatologiasPorIdSintoma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPatologiasPorIdSintoma()
   {
    	super();
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	    	
		PatologiaService pt = new PatologiaService();


		String id_Sintoma = request.getParameter("intro");
    	List<PatologiaDTO> lista_sintomaDTO = pt.buscarPatologiaPorID(id);
		
    	
    	
		List<SintomasDTO> lista_sintomas = null;
		
		lista_sintomas = pt.buscarPatologiaPorID(id);
	
		
		Gson gson = new Gson();
		Type tipoListaSintomas = new TypeToken<List<SintomasDTO>>(){}.getType();
		String s = gson.toJson(lista_sintomas, tipoListaSintomas);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(s);
		
		
		SintomasDAO sdao =new SintomasDAO();
		sdao.getPatologiasPorSintomasID(id_Sintoma);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		
	}

}
