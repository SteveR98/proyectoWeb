package DTO;

import java.util.List;

public class SintomasDTO{
	
	private int id;
	private String descripcion;
	
	public SintomasDTO(int id_sintoma, String nombre_sintoma)
	{
		super();
		this.id = id_sintoma;
		this.descripcion = nombre_sintoma;
	}

	public SintomasDTO(){
		
	}
	
	public SintomasDTO(String nombre_sintoma) {
		this.descripcion = nombre_sintoma;
	}

	public int getId() {
		return id;
	}
	public void setId_sintoma(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String nombre_sintoma) {
		this.descripcion = nombre_sintoma;
	}
	
}
