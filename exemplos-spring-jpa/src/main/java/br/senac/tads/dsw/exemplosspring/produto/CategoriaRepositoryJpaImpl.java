package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepositoryJpaImpl implements CategoriaRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Categoria> findAll() {
		TypedQuery<Categoria> jpqlQuery = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> resultados = jpqlQuery.getResultList();
		return resultados;
	}

	@Override
	public Categoria findById(Integer id) {
		TypedQuery<Categoria> jpqlQuery = em.createQuery("SELECT c FROM Categoria c WHERE c.id = :idCat", 
				Categoria.class);
		jpqlQuery.setParameter("idCat", id);
		Categoria resultado = jpqlQuery.getSingleResult();
		return resultado;
	}

	@Override
	@Transactional
	public Categoria save(Categoria cat) {
		if (cat.getId() == null) {
			// Incluindo uma categoria nova
			em.persist(cat);
		} else {
			// Atualiza uma categoria existente
			cat = em.merge(cat);
		}
		return cat;
	}

}
