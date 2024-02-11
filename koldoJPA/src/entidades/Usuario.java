package entidades;

import java.util.*;

import jakarta.persistence.*;

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

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nickName, Set<Post> posts) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nickName=" + nickName + ", posts=" + posts + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nickName, posts);
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
		return Objects.equals(id, other.id) && Objects.equals(nickName, other.nickName)
				&& Objects.equals(posts, other.posts);
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


}