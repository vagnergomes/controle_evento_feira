package br.com.feira.Entities;



public class CidadeInfo {
	
	@Override
	public String toString() {
		return "CidadeInfo [area=" + area + ", codigoIbge=" + codigoIbge + "]";
	}

//	@JsonProperty("area_km2")
	private String area;
	
//	@JsonProperty("codigo_ibge")
	private String codigoIbge;

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
	
}
