package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	
	private Avaliador avaliador;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;

	@Before
	public void criaAvaliador() {
		this.avaliador = new Avaliador();
	}
	
	@Before
	public void criaUsuarios() {
		this.joao = new Usuario("Joao");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("Jose");
	}
	
	@After
	public void testeAfter() {
		System.out.println("Após execucao dos testes");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(joao, 250.0)
				.lance(maria, 300.0)
				.lance(jose, 450.0)
				.Constroi();
		
		avaliador.avalia(leilao);
		
		assertEquals(450.0, avaliador.getMaiorLance(), 0.0001);
		assertEquals(250.0, avaliador.getMenorLance(), 0.0001);
		
	}
	
	@Test
	public void deveEntenderMediaDosLances() {
		
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(joao, 250.0)
				.lance(maria, 300.0)
				.lance(jose, 450.0)
				.Constroi();

		avaliador.calculaMedia(leilao);
		
		assertEquals(333.333333d, avaliador.getMediaLances(), 0.000001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(joao, 250)
				.Constroi();
		
		avaliador.avalia(leilao);
		
		assertEquals(250, avaliador.getMaiorLance(), 0.000001);
		assertEquals(250, avaliador.getMaiorLance(), 0.000001);
	}
	
	@Test
	public void deveEncontraOsTresMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(joao, 100)
				.lance(maria, 200)
				.lance(joao, 300)
				.lance(maria, 400)
				.Constroi();
		
		avaliador.avalia(leilao);
		
		List<Lance> tresMaiores = avaliador.getTresMaiores();
		
		assertEquals(3, tresMaiores.size());
		assertEquals(400, tresMaiores.get(0).getValor(), 0.000001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.000001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.000001);
	}
	
	@Test
	public void deveEncontrarMaiorEMenorLanceComOrdensAleatorias() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(joao, 500)
				.lance(joao, 100)
				.lance(maria, 1000)
				.lance(joao, 50)
				.lance(maria, 80)
				.lance(maria, 400)
				.Constroi();
		
		avaliador.avalia(leilao);

		assertEquals(1000, avaliador.getMaiorLance(), 0.000001);
		assertEquals(50, avaliador.getMenorLance(), 0.000001);
	}
	
	@Test
	public void deveAceitarLanceDecrescentes() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(joao, 500)
				.lance(joao, 400)
				.lance(maria, 300)
				.lance(joao, 200)
				.Constroi();
				

		avaliador.avalia(leilao);

		assertEquals(500, avaliador.getMaiorLance(), 0.000001);
		assertEquals(200, avaliador.getMenorLance(), 0.000001);
	}
	
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.Constroi();
		
		avaliador.avalia(leilao);
	}
}
