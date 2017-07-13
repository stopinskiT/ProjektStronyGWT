package pl.stopinski.client;

import java.io.Serializable;

public class Wpis implements Serializable {
	public Wpis() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String data;
	private String tytul;
	private String tresc;
	
	public Wpis(String data, String tytul, String tresc) {
		this.data = data;
		this.tytul = tytul;
		this.tresc = tresc;
	}

	@Override
	public String toString() {
		return "Wpis [data=" + data + ", tytul=" + tytul + ", tresc=" + tresc + "]";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

}
