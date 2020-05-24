package br.com.feira.Entities;

public class EstadoInfo {

//	@JsonProperty("area_km2")
	private String area;
	
//	@JsonProperty("codigo_ibge")
	private String codigoIbge;
	
//	@JsonProperty("nome")
	private String nome;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "EstadoInfo [area=" + area + ", codigoIbge=" + codigoIbge + ", nome=" + nome + "]";
	}
	
	
}
