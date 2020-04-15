package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepositoryJpaImpl implements ProdutoRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Produto> findAll(int offset, int quantidade) {
		TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findAll", Produto.class)
				.setFirstResult(offset)
				.setMaxResults(quantidade);
		List<Produto> resultados = jpqlQuery.getResultList();
		return resultados;
	}

	@Override
	public List<Produto> findByCategoria(List<Integer> idsCat, int offset, int quantidade) {
		TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findByCategoria", Produto.class)
				.setParameter("idCat", idsCat)
				.setFirstResult(offset)
				.setMaxResults(quantidade);
		List<Produto> resultados = jpqlQuery.getResultList();
		return resultados;
	}

	@Override
	public Produto findById(Long id) {
		TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findById", Produto.class)
				.setParameter("idProd", id);
		Produto resultado = jpqlQuery.getSingleResult();
		return resultado;
	}

	@Override
	@Transactional
	public Produto save(Produto p) {
		if (p.getId() == null) {
			// Adiciona novo produto
			em.persist(p);
		} else {
			// Atualiza produto existente
			p = em.merge(p);
		}
		return p;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// Faz consulta para associar o objeto ao entityManager (ATTACHED)
		Produto p = em.find(Produto.class, id);
		em.remove(p);
	}

}
