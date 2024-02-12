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
			
            Usuario usuario1 = new Usuario(null, "Koldo", null, null);
            Usuario usuario2 = new Usuario(null, "Odlok", null, null);
            Usuario usuario3 = new Usuario(null, "Pepito", null, null);
			
			em.persist(usuario1);
			em.persist(usuario2);
			em.persist(usuario3);
			
			Post post1 = new Post(null, LocalDate.now(), usuario1, "¡Hola a todos! ¿Cómo están?");
			Post post2 = new Post(null, LocalDate.now(), usuario2, "Buen día, comunidad. Espero que tengan un excelente día.");
			Post post3 = new Post(null, LocalDate.now(), usuario3, "Saludos desde mi rincón virtual.");
			Post post4 = new Post(null, LocalDate.now(), usuario1, "¿Alguien más disfruta del clima soleado?");
			Post post5 = new Post(null, LocalDate.now(), usuario2, "¡Nuevo post! Compartiendo mis pensamientos del día.");
			Post post6 = new Post(null, LocalDate.now(), usuario3, "¡Hola a todos! ¿Cómo están?");
			Post post7 = new Post(null, LocalDate.now(), usuario1, "Buen día, comunidad. Espero que tengan un excelente día.");
			Post post8 = new Post(null, LocalDate.now(), usuario2, "Saludos desde mi rincón virtual.");
			Post post9 = new Post(null, LocalDate.now(), usuario3, "¿Alguien más disfruta del clima soleado?");
			Post post10 = new Post(null, LocalDate.now(), usuario1, "¡Nuevo post! Compartiendo mis pensamientos del día.");
			
			em.persist(post1);
			em.persist(post2);
			em.persist(post3);
			em.persist(post4);
			em.persist(post5);
			em.persist(post6);
			em.persist(post7);
			em.persist(post8);
			em.persist(post9);
			em.persist(post10);
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
