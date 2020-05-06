package br.senac.tads.dsw.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.agenda.entidades.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
	
	// Convenção de nomenclatura do Spring Data JPA
	// Ver https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	// SELECT * FROM CONTATO WHERE NOME LIKE %TERMOBUSCA% OR APELIDO LIKE %TERMOBUSCA%;
	List<Contato> findByNomeContainingIgnoreCaseOrApelidoContainingIgnoreCase(String termoBusca1, String termoBusca2);

	// JPQL -> Sintaxe de queries do JPA - NAO É SQL
	@Query("SELECT c FROM Contato c WHERE lower(c.nome) LIKE %:termo% OR lower(c.apelido) LIKE %:termo%")
	List<Contato> searchJpql(@Param("termo") String termoBusca);
	
	// SQL Nativo
	@Query(value = "SELECT * FROM contato WHERE lower(nome) = ?1 OR lower(apelido) = ?1", nativeQuery = true)
	List<Contato> searchNativo(@Param("termo") String termoBusca);
	

}
