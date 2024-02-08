package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    // Constructor, Getters y Setters
	public Usuario() {
		super();
	}

	public Usuario(Long id, String nickName) {
		super();
		this.id = id;
		this.nickName = nickName;

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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nickName=" + nickName + "]";
	}	
}

//Eliminamos DTO's de momento y hacemos commit en el main'