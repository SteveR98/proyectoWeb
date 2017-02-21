package DAO;

	import DTO.SintomasDTO;

	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;


	public class SintomasDAO {

	

			
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
		}