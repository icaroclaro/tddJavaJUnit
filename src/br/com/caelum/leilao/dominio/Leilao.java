package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.core.IsNull;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return !nomeDoUltimoLanceDado().equals(usuario.getNome()) && quantidadeLancesDo(usuario) < 5;
	}

	private int quantidadeLancesDo(Usuario usuario) {
		int total = 0;
		
		for(Lance l : lances) {
			if(l.getUsuario().getNome().equals(usuario.getNome())) {
				total ++;
			}
		}
		return total;
	}

	private String nomeDoUltimoLanceDado() {
		return lances.get(lances.size()-1).getUsuario().getNome();
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
	
	public void dobraValorLance(Usuario usuario) {
		if(podeDarLance(usuario)) {
			
			Lance ultimoLance = ultimoLanceDo(usuario);
			
			if( ultimoLance != null) {
				propoe(new Lance(usuario, ultimoLance.getValor() * 2));
			}
		}
	}

	private Lance ultimoLanceDo(Usuario usuario) {
		Lance ultimoLance = null;
		
		for(Lance lance : lances) {
			if(lance.getUsuario().getNome().equals(usuario.getNome())) {
				ultimoLance = lance;
			}
		}
		return ultimoLance;
	}
	
}
