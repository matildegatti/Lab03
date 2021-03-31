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
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		List<RichWord> richwords=new ArrayList<RichWord>();
		
		
		for (String s : inputTextList)  {
			RichWord rich= new RichWord(s,false);
			for (String d:dizionario){
				if (d.equals(s)) {
					rich.setControllo(true);
					break;
				}
			} 
			richwords.add(rich);
		}
		
		return richwords;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		List<RichWord> richwords=new ArrayList<RichWord>();
		
		for (String s : inputTextList)  {
			RichWord rich= new RichWord(s,false);
			List<String> dizio=new LinkedList<String>(dizionario);
			while(dizio.size()>1) {
				List<String> fede=new LinkedList<String>();
				if (s.compareTo(dizionario.get(dizio.size()/2))<0) {
					fede.addAll(dizio.size()/2, dizio);
					dizio.removeAll(fede);
					
					//for(int i=0; i<dizio.size()/2;i++) 
						//fede.add(dizio.get(i));
				} 
				else if (s.compareTo(dizionario.get(dizio.size()/2))>0) {
					fede.addAll(dizio.size()/2, dizio);
					dizio.clear();
					dizio.addAll(fede);
				//	for(int i=dizio.size()/2; i<dizio.size(); i++) 
					//	fede.add(dizio.get(i));
				} 
				else {
					rich.setControllo(true);
					break;
				}	

			}
			richwords.add(rich);
		}
		return richwords;
	}
}
