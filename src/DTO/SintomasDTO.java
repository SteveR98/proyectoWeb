package DTO;

public class SintomasDTO {

	private int id_Sintomas;
	private String descrip_Sintomas;
	
	public int getId_Sintomas() {
		return id_Sintomas;
	}
	public void setId_Sintomas(int id_Sintomas) {
		this.id_Sintomas = id_Sintomas;
	}
	public String getDescrip_Sintomas() {
		return descrip_Sintomas;
	}
	public void setDescrip_Sintomas(String descrip_Sintomas) {
		this.descrip_Sintomas = descrip_Sintomas;
	}
	public SintomasDTO(int id_Sintomas, String descrip_Sintomas) {
		super();
		this.id_Sintomas = id_Sintomas;
		this.descrip_Sintomas = descrip_Sintomas;
	}
	
	
	
	
	
}
