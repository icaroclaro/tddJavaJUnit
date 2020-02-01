package br.com.caelum.leilao.dominio;

import org.junit.Before;
import org.junit.Test;

public class LanceTest {
	
	private Usuario marcao;
	@Before
	public void criadorDeUsuarios() {
		marcao = new Usuario("Marcao");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLancesMenoresOuIgualAZeros() {
		new Lance(marcao, 0);
	}
}
