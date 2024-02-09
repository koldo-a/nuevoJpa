package entidades;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@Column(nullable = false)
	private String texto;

	public Post() {
		super();
	}

	public Post(Long id, LocalDate fecha, Usuario usuario, String texto) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, id, texto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id) && Objects.equals(texto, other.texto)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", texto=" + texto + "]";
	}
}