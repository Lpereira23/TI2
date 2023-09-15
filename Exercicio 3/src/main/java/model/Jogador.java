package model;

public class Jogador {
	
	private int ID;
	private String Nome;
	private String Esporte;
	private String Clube;
	
	public Jogador() {
		this.ID = -1;
		this.Nome = "";
		this.Esporte = "";
		this.Clube = "";
	}
	
	public Jogador(int ID, String Nome, String Esporte, String Clube) {
		this.ID = ID;
		this.Nome = Nome;
		this.Esporte = Esporte;
		this.Clube = Clube;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}

	public String getEsporte() {
		return Esporte;
	}

	public void setEsporte(String Esporte) {
		this.Esporte = Esporte;
	}

	public String getClube() {
		return Clube;
	}

	public void setClube(String Clube) {
		this.Clube = Clube;
	}

	@Override
	public String toString() {
		return "Jogador [ID=" + ID + ", Nome=" + Nome + ", Esporte=" + Esporte + ", Clube=" + Clube + "]";
	}	
}
