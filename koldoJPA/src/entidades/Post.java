package entidades;

import java.time.*;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_post_usuario"))
	private Usuario usuario;
	
	@Column(name = "tweet", nullable = false)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", texto=" + texto + "]";
	}
}