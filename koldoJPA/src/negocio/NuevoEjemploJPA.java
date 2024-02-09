package negocio;

import java.time.LocalDate;
import java.util.function.Consumer;

import entidades.Post;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NuevoEjemploJPA {
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("entidades");

		inTransaction(em -> {
			
            Usuario usuario1 = new Usuario(null, "Koldo", null);
            Usuario usuario2 = new Usuario(null, "Odlok", null);
			
			em.persist(usuario1);
			em.persist(usuario2);
			//em.persist(new Post(null, LocalDate.now(), new Usuario(null, "Pepe", null), "Hola people!"));
			
			Post post1 = new Post(null, LocalDate.now(), usuario1, "Hola People!!!");

			em.persist(post1);
		});
		
		inTransaction(em -> System.out.println("hola"+em.find(Usuario.class, 2L)));

		inTransaction(em -> {
			var usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
			var posts = em.createQuery("from Post", Post.class).getResultList();
						
			for(Usuario usuario: usuarios) {
				System.out.println("Cada usuario: "+ usuario);
				for(Post post: posts) {
					System.out.println("Cada post: "+ post);
				}
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
