package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> choicelingua;

    @FXML
    private TextArea txtparoleinserite;

    @FXML
    private Button btncheck;

    @FXML
    private TextArea txtparolesbagliate;

    @FXML
    private Label lblnumerrori;

    @FXML
    private Button btnclear;

    @FXML
    private Label lbltempoesecuzione;

    @FXML
    void doClearText(ActionEvent event) {
    	txtparolesbagliate.clear();
    	txtparoleinserite.clear();
    	lblnumerrori.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	txtparolesbagliate.clear();
    	double inizio = System.nanoTime();
    	
    	String testo=txtparoleinserite.getText();
    	testo.replaceAll("[-,\\/#!$%\\^&\\*;:{}=\\-_()\\[\\]\"]","");
    	
    	if(testo.length()==0) {
    		txtparolesbagliate.setText("Inserire una parola!");
    		return;
    	}
     	String[] elenco=testo.split(" ");
     	
		String lingua=choicelingua.getValue();
		this.model.loadDictionary(lingua);
		
    	List<String> parole=new LinkedList<String>();  
    	
    	for(String p:elenco) {
    		String r=p.toLowerCase();
    		parole.add(r);
    	}
    	
    	List<RichWord> richwords=new LinkedList<RichWord>();
    	richwords=this.model.spellCheckText(parole);
    	
    	List<String> sbagliate=new LinkedList<String>();
    	for(RichWord w:richwords)
    		if(!w.isControllo())
    			sbagliate.add(w.getWord());
    	
    	for(String s:sbagliate) {
    		txtparolesbagliate.appendText(s+"\n");
    	}
    	
    	double fine=System.nanoTime();
    	this.lbltempoesecuzione.setText("Spell check completed in "+(fine-inizio));
    	
    	int numerrori=sbagliate.size();
    	this.lblnumerrori.setText("The text contains "+numerrori+" errors.");
    		
    }

    public void setModel(Dictionary model) {
    	this.model=model;
    	choicelingua.getItems().addAll("English","Italian");
    	choicelingua.setValue("English");
    }
    
    @FXML
    void initialize() {
        assert choicelingua != null : "fx:id=\"choicelingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtparoleinserite != null : "fx:id=\"txtparoleinserite\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btncheck != null : "fx:id=\"btncheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtparolesbagliate != null : "fx:id=\"txtparolesbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblnumerrori != null : "fx:id=\"lblnumerrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnclear != null : "fx:id=\"btnclear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lbltempoesecuzione != null : "fx:id=\"lbltempoesecuzione\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


