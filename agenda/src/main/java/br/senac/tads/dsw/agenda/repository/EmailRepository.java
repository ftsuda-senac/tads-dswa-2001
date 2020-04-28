package br.senac.tads.dsw.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senac.tads.dsw.agenda.entidades.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

}
