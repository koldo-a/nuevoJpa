package negocio;

import java.util.function.*;

import entidades.*;
import jakarta.persistence.*;

public class NuevoEjemploJPA {
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("entidades");

		inTransaction(em -> {
			em.persist(new Usuario(null, "Koldo", null));
			em.persist(new Usuario(null, "Odlok", null));
		});
		
		inTransaction(em -> System.out.println("hola"+em.find(Usuario.class, 2L)));

		inTransaction(em -> {
			var usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
						
			for(Usuario usuario: usuarios) {
				System.out.println("Cada usuario: "+ usuario);
			}
		});
		
	}
	
	private static void inTransaction(Consumer<EntityManager> work) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			work.accept(entityManager);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
