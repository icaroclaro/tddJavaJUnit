package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LeilaoTest {

	private Usuario marcao;
	private Usuario robson;
	
	@Before
	public void CriadorDeUsuario() {
		this.marcao = new Usuario("Marcao");
		this.robson = new Usuario("Robson");
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
	
	@Test
	public void deveReceberUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(marcao, 400)
				.Constroi();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(400, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(marcao, 400)
				.lance(robson, 500)
				.Constroi();
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(400, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(500, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {

		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(marcao, 400)
				.lance(robson, 500)
				.lance(marcao, 600)
				.lance(robson, 700)
				.lance(robson, 800)
				.Constroi();
		
		int ultimoIndiceLances = leilao.getLances().size()-1;
		
		assertEquals(4, leilao.getLances().size());
		assertEquals(700, leilao.getLances().get(ultimoIndiceLances).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDeUmMesmoUsuario() {
		
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(marcao, 200)
				.lance(robson, 300)
				.lance(marcao, 400)
				.lance(robson, 500)
				.lance(marcao, 600)
				.lance(robson, 700)
				.lance(marcao, 800)
				.lance(robson, 900)
				.lance(marcao, 1000)
				.lance(robson, 1100)
				.lance(marcao, 1200)
				.lance(robson, 1300)
				.lance(marcao, 1400)
				.lance(robson, 1500)
				.Constroi();
		
		int ultimoIndiceLances = leilao.getLances().size()-1;
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(1100, leilao.getLances().get(ultimoIndiceLances).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarValorDoLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(marcao, 1200)
				.lance(robson, 1300)
				.lance(marcao, 1400)
				.lance(robson, 1500)
				.dobraLance(marcao)
				.Constroi();
		
		assertEquals(5, leilao.getLances().size());
		assertEquals(2800, leilao.getLances().get(4).getValor(), 0.00001);
	}
	@Test
	public void deveDobrarValorDoLanceENaoAceitarDoisLancesRepetidos() {
		
		Leilao leilao = new CriadorDeLeilao().para("Abacaxi")
				.lance(marcao, 1200)
				.lance(robson, 1300)
				.lance(marcao, 1400)
				.lance(robson, 1500)
				.lance(robson, 1700)
				.dobraLance(marcao)
				.Constroi();
		
		assertEquals(5, leilao.getLances().size());
		assertEquals(2800, leilao.getLances().get(4).getValor(), 0.00001);
	}
}
