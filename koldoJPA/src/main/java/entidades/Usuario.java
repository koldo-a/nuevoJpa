package entidades;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nick_name", nullable = false)
	private String nickName;

	@OneToMany(mappedBy = "usuario")
	private Set<Post> posts = new HashSet<>();
	
	@OneToMany(mappedBy = "quien")
	private Set<Denuncia> denuncias = new HashSet<>();

	public Usuario() {
	}

	public Usuario(Long id, String nickName, Set<Post> posts, Set<Denuncia> denuncias) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.posts = posts;
		this.denuncias = denuncias;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nickName=" + nickName + ", posts=" + posts + ", denuncias=" + denuncias + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(denuncias, id, nickName, posts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(denuncias, other.denuncias) && Objects.equals(id, other.id)
				&& Objects.equals(nickName, other.nickName) && Objects.equals(posts, other.posts);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Denuncia> getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(Set<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}
}

