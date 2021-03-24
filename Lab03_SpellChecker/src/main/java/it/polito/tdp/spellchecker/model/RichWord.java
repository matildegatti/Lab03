package it.polito.tdp.spellchecker.model;

public class RichWord {

	String word;
	boolean controllo;
	
	public RichWord(String word, boolean controllo) {
		this.word = word;
		this.controllo = controllo;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isControllo() {
		return controllo;
	}

	public void setControllo(boolean controllo) {
		this.controllo = controllo;
	}
	
}
