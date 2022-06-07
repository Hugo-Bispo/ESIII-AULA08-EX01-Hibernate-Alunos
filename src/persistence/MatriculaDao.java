package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.spi.QueryParameterBindingTypeResolver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Aluno;
import model.Matricula;

public class MatriculaDao implements IMatriculaDao<Matricula> {
	private SessionFactory sf;

	public MatriculaDao(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void insert(Matricula matricula) throws SQLException {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(matricula);
		transaction.commit();
	}

	@Override
	public void delete(Matricula matricula) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM matricula");
		sql.append("WHERE cod_disc = " + matricula.getCod_disc().toString());
		sql.append(" AND ");
		sql.append("ra = " + matricula.getRaAluno());
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(sql.toString());
		System.out.println(query);
	}

	@Override
	public List<Matricula> selectAll(Matricula matricula) {
		List<Matricula> matriculas = new ArrayList<Matricula>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT a.ra a.email a.nome a.posic_vestibular FROM matricula ");
		buffer.append("INNER JOIN aluno ON aluno.ra = matricula.ra ");
		buffer.append("WHERE ");
		buffer.append("matricula.cod_disc = '" + String.valueOf(matricula.getCod_disc().getCod_disc()));
		
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		
		for (Object[] obj : lista) {
			Aluno aluno = new Aluno();
//			atendimento.setDataHora(LocalDateTime.parse(obj[0].toString()));
//			cliente.setCpf(obj[3].toString());
//			cliente.setCelular(obj[4].toString());
//			cliente.setEmail(obj[5].toString());
//			cliente.setNome(obj[6].toString());
//			cliente.setPronomeTratamento(obj[7].toString());
//
//			atendente.setEmail(obj[8].toString());
//			atendente.setHoraEntrada(Integer.parseInt(obj[9].toString()));
//			atendente.setHoraSaida(Integer.parseInt(obj[10].toString()));
//
//			atendimento.setAtendente(atendente);
//			atendimento.setCliente(cliente);
//			atendimentos.add(atendimento);
		}

		
		return matriculas;
	}

}
