package br.com.contmatic.easyRandom;

import java.util.ArrayList;
import javax.swing.text.MaskFormatter;

public class GeraCPF {
	private static ArrayList<Integer> listaAleatoria = new ArrayList<Integer>();
	private static ArrayList<Integer> listaNumMultiplicados = null;
	
	private static int geraNumAleatorio(){
		int numero = (int) (Math.random() * 10);
		
		return numero;
	}	
	
	private static ArrayList<Integer> geraCPFParcial(){
		for(int i = 0; i < 9; i++){
			listaAleatoria.add(geraNumAleatorio());
		}
		
		return listaAleatoria;
	}
	
	private static ArrayList<Integer> geraPrimeiroDigito(){
		listaNumMultiplicados = new ArrayList<Integer>();
		int primeiroDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 10;
		
		for(int item : listaAleatoria){
			listaNumMultiplicados.add(item * peso);
			
			peso--;
		}
		
		for(int item : listaNumMultiplicados){
			totalSomatoria += item;
		}
		
		restoDivisao = (totalSomatoria % 11);
		
		if(restoDivisao < 2){
			primeiroDigito = 0;
		} else{
			primeiroDigito = 11 - restoDivisao;
		}
		
		listaAleatoria.add(primeiroDigito);
		
		return listaAleatoria;
	}

	private static ArrayList<Integer> geraSegundoDigito(){
		listaNumMultiplicados = new ArrayList<Integer>();
		int segundoDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 11;
		
		for(int item : listaAleatoria){
			listaNumMultiplicados.add(item * peso);
			
			peso--;
		}
		
		for(int item : listaNumMultiplicados){
			totalSomatoria += item;
		}
		
		restoDivisao = (totalSomatoria % 11);
		
		if(restoDivisao < 2){
			segundoDigito = 0;
		} else{
			segundoDigito = 11 - restoDivisao;
		}
		
		listaAleatoria.add(segundoDigito);
		
		return listaAleatoria;
	}
	
	public static String geraCPFFinal() {
		geraCPFParcial();
		geraPrimeiroDigito();
		geraSegundoDigito();
		
		String cpf = "";
		String texto = "";
		
		for(int item : listaAleatoria){
			texto += item;
		}
		
       	try{
    		MaskFormatter mf = new MaskFormatter("###.###.###-##");  
    		mf.setValueContainsLiteralCharacters(false);
    		cpf = mf.valueToString(texto);
       	}catch(Exception ex){
       		ex.printStackTrace();
       	}
		
		return cpf;	
	}
}