package entidades;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "denuncias")
public class Denuncia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "u_denunciante_id", nullable = false, foreignKey = @ForeignKey(name = "fk_denunciante_usuario"))
	private Usuario quien;
	
	private String razon;

	public Denuncia() {
		super();
	}

	public Denuncia(Long id, Usuario quien, String razon) {
		super();
		this.id = id;
		this.quien = quien;
		this.razon = razon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getQuien() {
		return quien;
	}

	public void setQuien(Usuario quien) {
		this.quien = quien;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Override
	public String toString() {
		return "Denuncia [id=" + id + ", quien=" + quien + ", razon=" + razon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quien, razon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Denuncia other = (Denuncia) obj;
		return Objects.equals(id, other.id) && Objects.equals(quien, other.quien) && Objects.equals(razon, other.razon);
	}

}