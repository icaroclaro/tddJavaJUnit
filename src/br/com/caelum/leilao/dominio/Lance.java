	package br.com.caelum.leilao.dominio;

public class Lance {

	private Usuario usuario;
	private double valor;
	
	public Lance(Usuario usuario, double valor) {
		validaValorPositivo(valor);
		this.usuario = usuario;
		this.valor = valor;
	}

	private void validaValorPositivo(double valorLance) {
		if(valorLance <= 0) {
			throw new IllegalArgumentException();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}
	
}
