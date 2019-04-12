package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;


public class GerenciadorDeSessaoTest {
	
	private Filme rogueOne;
	private Sala sala3D;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	
	@Before
	public void preparaSessoes() {
		this.rogueOne = new Filme("Rouge One", Duration.ofMinutes(120), "SCI-FI");
		this.sala3D = new Sala("Sala 3D");
		
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"),sala3D, rogueOne);
		this.sessaoDasTreze = new Sessao(LocalTime.parse("13:00:00"), sala3D, rogueOne);
		this.sessaoDasDezoito = new Sessao (LocalTime.parse("18:00:00"), sala3D, rogueOne);

	}
	
	@Test
	public void garanteQueNaodevePermitirSessaoNoMesmoHoraio() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
		
	}
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHoraio() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1),sala3D, rogueOne);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHoraioJaExistente() {
		List<Sessao> sessoesDaSala = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);
		Sessao sessao = new Sessao(sessaoDasDez.getHorario().minusHours(1),sala3D, rogueOne);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}
	
	
	@Test
	public void garanteQueNaoDevePermitirUmaInsercaoEntreDoisFilmes() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessaoDasTreze));
	}

}