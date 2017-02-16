package DTO;
import java.util.List;


public class PatologiaDTO {

	private int id_pato;
	private String nombre_pato;
	private String causa_pato;
	private String tratamiento_pato;
	private String descripcion_pato;
	private List<SintomaDTO> lista_sintomas;
	
	public PatologiaDTO(int id, String nombre, String causa, String tratamiento, String descripcion,
			List<SintomaDTO> lista_sintomas) {
		this.id_pato = id;
		this.nombre_pato = nombre;
		this.causa_pato = causa;
		this.tratamiento_pato = tratamiento;
		this.descripcion_pato = descripcion;
		this.lista_sintomas = lista_sintomas;
	}
	public int getId() {
		return id_pato;
	}
	public void setId(int id) {
		this.id_pato = id;
	}
	public String getNombre() {
		return nombre_pato;
	}
	public void setNombre(String nombre) {
		this.nombre_pato = nombre;
	}
	public String getCausa() {
		return causa_pato;
	}
	public void setCausa(String causa) {
		this.causa_pato = causa;
	}
	public String getTratamiento() {
		return tratamiento_pato;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento_pato = tratamiento;
	}
	public String getDescripcion() {
		return descripcion_pato;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion_pato = descripcion;
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