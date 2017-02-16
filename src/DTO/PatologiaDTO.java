package DTO;
import java.util.List;


public class PatologiaDTO {

	private int id_patol;
	private String nom_patol;
	private String causa_patol;
	private String trat_patol;
	private String des_patol;
	private List<SintomaDTO> lista_sintomas;
	
	public PatologiaDTO(int id_patol, String nom_patol, String causa_patol, String trat_patol, String des_patol,
			List<SintomaDTO> lista_sintomas) {
		this.id_patol = id_patol;
		this.nom_patol = nom_patol;
		this.causa_patol = causa_patol;
		this.trat_patol = trat_patol;
		this.des_patol = des_patol;
		this.lista_sintomas = lista_sintomas;
	}
	public int getId() {
		return id_patol;
	}
	public void setId(int id) {
		this.id_patol = id;
	}
	public String getNombre() {
		return nom_patol;
	}
	public void setNombre(String nombre) {
		this.nom_patol = nombre;
	}
	public String getCausa() {
		return causa_patol;
	}
	public void setCausa(String causa) {
		this.causa_patol = causa;
	}
	public String getTratamiento() {
		return trat_patol;
	}
	public void setTratamiento(String tratamiento) {
		this.trat_patol = tratamiento;
	}
	public String getDescripcion() {
		return des_patol;
	}
	public void setDescripcion(String descripcion) {
		this.des_patol = descripcion;
	}
	public List<SintomaDTO> getLista_sintomas() {
		return lista_sintomas;
	}
	public void setLista_sintomas(List<SintomaDTO> lista_sintomas) {
		this.lista_sintomas = lista_sintomas;
	}

	public String mostrarListaSintomas ()
	{
		String res = "";
		for (SintomaDTO s : lista_sintomas) {
			res += s.toString()+"<br>";
		}
		return res;
	}
	
}