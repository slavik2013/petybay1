package model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
@Table(name = "photo")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private int id;

	private String filepath;

	@Column(name="is_photo_main")
	private String isPhotoMain;

	//bi-directional many-to-one association to Pet
    @JsonIgnore
	@ManyToOne
	private Pet pet;

	public Photo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getIsPhotoMain() {
		return this.isPhotoMain;
	}

	public void setIsPhotoMain(String isPhotoMain) {
		this.isPhotoMain = isPhotoMain;
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}