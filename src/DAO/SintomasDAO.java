package DAO;

import DTO.PatologiasDTO;
import DTO.SintomasDTO;
import Servicios.Consultas;

import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;


	public class SintomasDAO {
	
		public PatologiasDTO buscarPorId (int id)
		{
			PatologiasDTO pdto = null;
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
					pdto = new PatologiasDTO(rs.getInt(1), rs.getNString(2), rs.getString(5), rs.getString(4), rs.getNString(3), getPatologiasPorSintomasID(con, rs.getInt(1)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				Pool.liberarRecursos(con, st, rs);
			}
			return pdto;
		}
		
			
		
		public static List<SintomasDTO> getSintomasPorInicial (String inicial) throws SQLException
			{
				List<SintomasDTO> lista_sintomas = new ArrayList<SintomasDTO>();
				String descripcion_sintoma = null;
				int id_sintoma = 0;
				SintomasDTO sintoma = null;
				ResultSet rset2 = null;
				Statement stmt2 = null;
				Connection conn = null;
				conn = Pool.getConnection();
			
				stmt2 = conn.createStatement();
				rset2 = stmt2.executeQuery(Servicios.Consultas.BUSCAR_SINTOMAS_POR_INICIAL+inicial+"%'");
			
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
		

			public static List<PatologiasDTO> getPatologiasPorSintomasID (Connection conn, int id) throws SQLException
	{
		List<PatologiasDTO> lista_patologias = new ArrayList<PatologiasDTO>();
		List<SintomasDTO> lista_sintomas=new ArrayList<SintomasDTO>();

		String nom_patol = null;
		int id_patol = 0;
		String causa_patol=null;
		String trat_patol=null;
		String des_patol=null;
		
		PatologiasDTO patologia = null;
		
		ResultSet rset2 = null;
		Statement stmt2 = null;
		conn = null;
		conn = Pool.getConnection();
	
		stmt2 = conn.createStatement();
		rset2 = stmt2.executeQuery(Servicios.Consultas.BUSCAR_SINTOMAS_POR_INICIAL+id+"%'");
	
		while (rset2.next())
		{
			id_patol = rset2.getInt(1);
			nom_patol = rset2.getString(2);
			causa_patol = rset2.getString(1);
			trat_patol = rset2.getString(1);
			des_patol = rset2.getString(1);
			
			patologia = new PatologiasDTO(id_patol, nom_patol, causa_patol, trat_patol, des_patol, lista_sintomas);
			lista_patologias.add(patologia);
		}
		if (rset2 != null) 	{ try { rset2.close(); } catch (Exception e2) { e2.printStackTrace(); }}
		if (stmt2 != null)	{ try {	stmt2.close(); } catch (Exception e2) { e2.printStackTrace(); }}
		
	return lista_patologias;
	}
}