package br.com.weon.persistencia.conexao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Component
public class Conexao implements IConexao {

	private EntityManagerFactory factory;
	private EntityManager manager;

	public Conexao() {
		factory = Persistence.createEntityManagerFactory("teste");
		manager = factory.createEntityManager();
	}

	@Override
	public void fechar() {
		manager.close();
		factory.close();
	}

	@Override
	public synchronized <T> T salvar(T objeto) {
		manager.persist(objeto);
		manager.flush();

		return objeto;
	}

	@Override
	public <T> T atualizar(T objeto) {
		objeto = manager.merge(objeto);
		manager.flush();
		return objeto;
	}

	@Override
	public void deletar(Object objeto) {
		manager.remove(objeto);
		manager.flush();
	}

	@Override
	public <T> T buscar(Class<T> classeEntidade, Object chavePrimaria) {
		return manager.find(classeEntidade, chavePrimaria);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> buscar(String consulta, HashMap<String, Object> parametros) {
		Query query = manager.createQuery(consulta.trim());

		if (parametros != null) {
			for (String chave : parametros.keySet())
				query.setParameter(chave, parametros.get(chave));
		}
		List<T> lista = query.getResultList();
		return lista;
	}

	@Override
	public void iniciarTransacao() {
		// if (!manager.getTransaction().isActive())
		manager.getTransaction().begin();
	}

	@Override
	public void comitarTransacao() {
		// if (manager.getTransaction().isActive())
		manager.getTransaction().commit();
	}

	@Override
	public void desfazerTransacao() {
		// if (manager.getTransaction().isActive())
		manager.getTransaction().rollback();
	}

}
