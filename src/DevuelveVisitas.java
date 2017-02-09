import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class ContadorPagina
*/
@WebServlet("/DevuelveVisitas")
public class DevuelveVisitas extends HttpServlet {
private int visitas;
private static final long serialVersionUID = 1L;

/**
* @see HttpServlet#HttpServlet()
*/
public DevuelveVisitas() {
super();

}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
*      response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
PrintWriter pw = response.getWriter();
pw.write(visitas);
pw.write("Visitas: " + visitas);
}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
*      response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doGet(request, response);
}

@Override
public void init() throws ServletException {
visitas = 0;
super.init();
}

@Override
protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
visitas++;

super.service(arg0, arg1);
}
}