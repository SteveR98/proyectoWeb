package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DTO.MapaPatologias;
import DTO.MapaPatologiasCandidatas;
import DTO.PatologiasDTO;
import DTO.SintomasDTO;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void mostrar(Map<Integer, PatologiasDTO> mapa) {
		Iterator<Integer> it = mapa.keySet().iterator();
		PatologiasDTO paux = null;
		while (it.hasNext()) {
			paux = mapa.get(it.next());
			log.debug(paux.toString());
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			log.debug("ENTRNADO EN TEST_SERVLET");

			HttpSession session = request.getSession(false);
			
			String respuesta = request.getParameter("resp");
			List<Integer> lista_sintomas_preguntados = null;
			//super Ã±apa
			if (session != null)
				
			{
				lista_sintomas_preguntados = (List<Integer>) session.getAttribute("lista_sintomas_preguntados");
			}
			
			Integer npregunta = Integer.parseInt(request.getParameter("npreg"));
			if ((session == null) || ((respuesta == null || respuesta == ""))
					|| (lista_sintomas_preguntados == null) || (lista_sintomas_preguntados.contains(npregunta))) {
				// caso especial, el usuario a heehco uan peticiÃ³n sin pasar por
				// init test
				log.debug("Cliente sin sesiÃ³n vÃ¡lida o  con peticiÃ³n incorrecta");
				response.sendRedirect("/InitTest");

			} else {

				log.debug("PeticiÃ³n formalmente correctga");
				lista_sintomas_preguntados.add(npregunta);

				List<SintomasDTO> lista_sdto = (List<SintomasDTO>) session.getAttribute("lista_sint");
				MapaPatologiasCandidatas mapa_patolog_candidatas = (MapaPatologiasCandidatas) session
						.getAttribute("mapa_patologias");
				int num_sintoma_actual = (Integer) session.getAttribute("num_sintoma_actual");

				Map<Integer, PatologiasDTO> mapa_patolog_resultado = null;
				// TODO SI NO HAY RESPUESTA, REDIRIGIR A INICIO O MANDAR A
				// PÃ�GINA DE ERROR

				if (respuesta.equals("SI")) {
					mapa_patolog_resultado = mapa_patolog_candidatas.filtrarPatologiasCandidatas(
							mapa_patolog_candidatas.getMapa_patologias_candidatas(),
							lista_sdto.get(num_sintoma_actual));
				} else // dijo que no
				{
					Map<Integer, PatologiasDTO> mapa_patolog_filtradas = mapa_patolog_candidatas
							.filtrarPatologiasCandidatas(mapa_patolog_candidatas.getMapa_patologias_candidatas(),
									lista_sdto.get(num_sintoma_actual));
					mapa_patolog_resultado = MapaPatologias.diferencia(MapaPatologias.getMapapatologia(),
							mapa_patolog_filtradas);
				}

				log.debug("MOSTRANDO PATOLOGIAS CANDIDATAS");
				mostrar(mapa_patolog_resultado);
				int npatols = mapa_patolog_resultado.size();

				log.debug(mapa_patolog_resultado.size());

				switch (npatols) {
				case 0:
					log.debug("Sin patologias candidatas");
					session.invalidate();
					log.debug("SesiÃ³n invalidada");
					// MENSAJE ERROR--> NO SABEMOS QUï¿½ TE PASA
					request.setAttribute("pregunta", "PREGUNTE A SU Mï¿½DICO");
					request.getRequestDispatcher(".//html//test.jsp").forward(request, response);
					break;

				case 1:

					log.debug("Patolgia ENCONTRADA");
					session.invalidate();
					log.debug("Sesion invalidada");

					Iterator<Integer> it = mapa_patolog_resultado.keySet().iterator();
					PatologiasDTO patologia_ganadora = mapa_patolog_resultado.get(it.next());

					log.debug("Patolgia resultado 1" + patologia_ganadora.toString());

					request.setAttribute("patologia", patologia_ganadora); // lista_sdto.get(num_sintoma_actual).getPregunta_web());
					request.getRequestDispatcher(".//html//resultadotest.jsp").forward(request, response);

					break;

				default: // CASO POR DEFECTO --> + DE 1 PATOLOGï¿½A CANDIDATA

					log.debug("Queda mÃ¡s de una patol candidadata, buscando siguiente sÃ­ntoma/pregunta");

					boolean sintoma_seleccionado = false;
					int nsintomas = lista_sdto.size();

					log.debug("NÂº Sintoma Actual = " + num_sintoma_actual);
					log.debug("nsintomas = " + nsintomas);

					num_sintoma_actual = num_sintoma_actual + 1;

					if (num_sintoma_actual < nsintomas) {
						do {

							SintomasDTO sintomaactual = lista_sdto.get(num_sintoma_actual);
							log.debug("Sintoma actual " + sintomaactual.toString());

							boolean sintoma_presente = false;
							PatologiasDTO paux = null;
							Iterator<Integer> itm = mapa_patolog_resultado.keySet().iterator();

							while (!sintoma_presente && itm.hasNext()) {
								paux = mapa_patolog_resultado.get(itm.next());
								sintoma_presente = mapa_patolog_candidatas.sintomaEnPatologia(sintomaactual, paux);
							}

							if (sintoma_presente) {
								log.debug("Sintoma presente en  " + paux.toString());
								sintoma_seleccionado = true;
							} else {
								log.debug("Sintoma no presente en ninguna patol candidata " + sintomaactual.toString());
								log.debug("Buscando el siguiente ");
								num_sintoma_actual = num_sintoma_actual + 1;
							}
						} while ((!sintoma_seleccionado) && (num_sintoma_actual < nsintomas));
					}

					log.debug("Sale del WHILE");
					if (!sintoma_seleccionado) // ninguno de los sï¿½ntomas, estï¿½
												// presente en la lista de
												// patologï¿½as candidatas
					{
						// case 0: //MENSAJE ERROR--> NO SABEMOS QUï¿½ TE PASA
						log.debug("LleguÃ© al final y no estÃ¡ presente en ninguna patol ABANDONO  ");
						session.invalidate();
						log.debug("SesiÃ³n invalidada");
						request.getRequestDispatcher(".//html//resultadotestnegativo.jsp").forward(request, response);
					} else {
						log.debug("SÃ­ntoma encontrado  ");

						MapaPatologiasCandidatas mapa_nuevo = new MapaPatologiasCandidatas(mapa_patolog_resultado);
						session.setAttribute("lista_sintomas_preguntados", lista_sintomas_preguntados);
						session.setAttribute("mapa_patologias", mapa_nuevo);// actualiza
																			// mapa
																			// en
																			// sesiï¿½n
						session.setAttribute("num_sintoma_actual", num_sintoma_actual);// actualiza
																						// nï¿½mero
																						// de
																						// sï¿½ntoma
																						// en
																						// sesiï¿½n
						// la lista de sï¿½ntomas no se actualiza porque siempre
						// es la misma.
						request.setAttribute("npregunta", num_sintoma_actual); // esto
																				// deberÃ­a
																				// estar
																				// en
																				// el
																				// contexto
						request.setAttribute("pregunta", lista_sdto.get(num_sintoma_actual).getPregunta_web());
						request.getRequestDispatcher(".//html//test.jsp").forward(request, response);
						// Y REDIRIGIR AL JSP CON LA PREGUNTA/SINTOMA
						// SELECCIONADA
					}

					break;
				}
			}
		} catch (Throwable t) {
			log.error("ERRORAZO", t);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
