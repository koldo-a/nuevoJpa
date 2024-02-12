package accesodatos;

import java.sql.SQLException;
import java.util.HashSet; // Agregar importación
import java.util.List;
import java.util.Set;

import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UsuarioAccesoDatosJpa {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entidades");
    
    public static Set<Usuario> obtenerTodos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String jpql = "select u from Usuario u";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> usuariosList = query.getResultList();
        
        // Convertir la lista a un conjunto
        Set<Usuario> usuariosSet = new HashSet<>(usuariosList);
        
        // Cerrar el EntityManager
        entityManager.close();
        
        return usuariosSet;
    }
    
        public static void borrar(long id) throws SQLException {
            EntityTransaction transaction = null;
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();

                // Encontrar el usuario por su ID y eliminarlo
                Usuario usuario = entityManager.find(Usuario.class, id);
                if (usuario != null) {
                    entityManager.remove(usuario);
                    transaction.commit();
                    System.out.println("Usuario con ID " + id + " eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró ningún usuario con el ID " + id);
                }
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw new SQLException("Error al intentar eliminar el usuario con ID " + id, e);
            }
        }


    public static Usuario obtenerPorId(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try {
            String jpql = "select u from Usuario u where u.id = :id";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("id", id);
            
            // Ejecutar la consulta y obtener el usuario
            Usuario usuario = (Usuario) query.getSingleResult();
            
            return usuario;
        } catch (NoResultException e) {
            // Manejar el caso en el que no se encuentra ningún usuario con el ID dado
            System.out.println("No se encontró ningún usuario con el ID: " + id);
            return null;
        } finally {
            entityManager.close(); // Cerrar el EntityManager
        }
    }
    }
