package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {
	
	private EntityManager manager;
	
	public void save(Sessao sessao) {
		manager.persist(sessao);
	}

	public List<Sessao> buscaSessoesDaSala(Sala sala){
		return manager.createQuery ("select s from Sesao s where s.sala = :sala", Sessao.class)
				.setParameter("sala", sala)
				.getResultList();
		
	}
}
