package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDeLances = 0d;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		if(leilao.getLances().size()==0) {
			throw new RuntimeException("Não há lances para processar");
		}
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos ) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos ) menorDeTodos = lance.getValor();
		}
		
		pegaOsMaiores(leilao);

	}
	
	private void pegaOsMaiores(Leilao leilao) {
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		
		Collections.sort(maiores, new Comparator<Lance>() {

			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
		
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
	public void calculaMedia(Leilao leilao) {

		double totalDeLances = 0;
		
		for(Lance lance : leilao.getLances()) {
			totalDeLances += lance.getValor();
		}
		
		mediaDeLances=  totalDeLances / leilao.getLances().size();
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	
	public double getMediaLances() {
		return mediaDeLances;
	}

}
