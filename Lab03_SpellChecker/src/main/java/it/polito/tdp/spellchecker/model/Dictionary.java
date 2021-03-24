package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> dizionario;

	public void loadDictionary(String language) {
		dizionario=new LinkedList<String>();
		
		if(language.equals("English")) {
			try {
				FileReader fr=new FileReader("src/main/resources/English.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word=br.readLine())!=null) {
					dizionario.add(word);
				}
				br.close();
			
			}catch(IOException e) {
				System.out.println("Errore nella lettura da file");
			}
		}
		else if(language.equals("Italian")) {
			try {
				FileReader fr=new FileReader("src/main/resources/Italian.txt");
				BufferedReader br=new BufferedReader(fr);
				String word;
				while((word=br.readLine())!=null) {
					dizionario.add(word);
				}
				br.close();
			
			}catch(IOException e) {
				System.out.println("Errore nella lettura da file");
			}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> richwords=new ArrayList<RichWord>();
		
		for(String s:inputTextList) {
			if(dizionario.contains(s))
				richwords.add(new RichWord(s,true));
			else
				richwords.add(new RichWord(s,false));
		}
		return richwords;
	}
}
