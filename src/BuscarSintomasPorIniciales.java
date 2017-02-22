

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.lang.reflect.Type;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DTO.SintomasDTO;
import Servicios.SintomasService;

/**
 * Servlet implementation class BuscarSintomasPorIniciales
 */
@WebServlet("/BuscarSintomasPorInicial")
public class BuscarSintomasPorIniciales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarSintomasPorIniciales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
    	String inicial = request.getParameter("intro");
		SintomasService ss = new SintomasService();
		List<SintomasDTO> lista_sintomas = null;
		
			try {
				lista_sintomas = ss.buscarSintomasPorInicial(inicial);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		Gson gson = new Gson();
		Type tipoListaSintomas = new TypeToken<List<SintomasDTO>>(){}.getType();
		String s = gson.toJson(lista_sintomas, tipoListaSintomas);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(s);
		*/
    	
    	
    	SintomasService ss = new SintomasService();

    	try
    	{
    	String valorSintoma = request.getParameter("intro");
    	List<SintomasDTO> lista_sintomaDTO = ss.buscarSintomasPorInicial(valorSintoma);
    	Gson gson = new Gson();

    	Type tipoListaSintomas = new TypeToken<List<SintomasDTO>>(){}.getType();
    	String s = gson.toJson(lista_sintomaDTO, tipoListaSintomas);

    	for(SintomasDTO sintoma: lista_sintomaDTO)
    	{    	
    		valorSintoma = sintoma.getDescripcion();

    	System.out.println(valorSintoma);

    	}
    	System.out.println(s);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().write(s);
    		} 
    	
    	catch (SQLException e) 
    	{
    	e.printStackTrace();
    	}

    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String json = request.getReader().readLine();
		Gson gson = new Gson();
		SintomasDTO dto = gson.fromJson(json, SintomasDTO.class);
		System.out.println("Sintomas: " + dto.getId()+ " " +dto.getDescripcion());
		
		
	}
	

}