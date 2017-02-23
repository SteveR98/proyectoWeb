package DAO;

import DTO.PatologiaDTO;
import DTO.SintomasDTO;
import Servicios.Consultas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class PatologiaDAO {
	
	public static final String LISTAR_PATOLOGIAS
	= "SELECT nom_patol From Patologias Where id_patol IN (Select id_patol From Patologias Where id_patol)";
	private final static Logger log = Logger.getLogger("mylog");

	public PatologiaDTO buscarPorId (int id)
	{
		PatologiaDTO pdto = null;
		Pool pool = null;	
		pool = Pool.getInstance();
		Connection con = Pool.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(Consultas.CONSULTA_PATOLOGIAS_POR_ID+id);
			while (rs.next())
		    {
				pdto = new PatologiaDTO(rs.getInt(1), rs.getNString(2), rs.getString(5), rs.getString(4), rs.getNString(3), getSintomasPorPatologiaID(con, rs.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("ERROR");
			e.printStackTrace();
		}
		finally
		{
			Pool.liberarRecursos(con, st, rs);
		}
		return pdto;
	}
	
	public static List<SintomasDTO> getSintomasPorPatologiaID (Connection conn, int id) throws SQLException
	{
		List<SintomasDTO> lista_sintomas = new ArrayList<SintomasDTO>();
			log.info("LISTA PATOLOGIAS CREADA");
			String descripcion_sintoma = null;
			int id_sintoma = 0;
			SintomasDTO sintoma = null;
			ResultSet rset2 = null;
			Statement stmt2 = null;
			stmt2 = conn.createStatement();
			rset2 = stmt2.executeQuery(Consultas.CONSULTA_SINTOMAS_POR_PATOLOGIA+id+"'))");
			while (rset2.next())
		    {
				id_sintoma = rset2.getInt(1);
				descripcion_sintoma = rset2.getString(2);
				sintoma = new SintomasDTO(id_sintoma, descripcion_sintoma);
				lista_sintomas.add(sintoma);
			}
			if (rset2 != null) 	{ try { rset2.close(); } catch (Exception e2) { e2.printStackTrace(); }}
			if (stmt2 != null)	{ try {	stmt2.close(); } catch (Exception e2) { e2.printStackTrace(); }}
			
		return lista_sintomas;
	}
}