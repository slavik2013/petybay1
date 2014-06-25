package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pet database table.
 * 
 */
@Entity
@NamedQuery(name="Pet.findAll", query="SELECT p FROM Pet p")
//@JsonIgnoreProperties({ "location", "user" , "photos" , "breeds"})
public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String color;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Lob
	private String description;

	@Column(name="eye_color")
	private String eyeColor;

	@Column(name="has_award")
	private String hasAward;

	@Column(name="has_award_in_parent")
	private String hasAwardInParent;

	private int price;

	private String purebred;

	@Column(name="type_of_color")
	private String typeOfColor;

	@Column(name="wool_length")
	private String woolLength;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="pet_has_breed"
            , joinColumns={
            @JoinColumn(name="pet_id")
    }
            , inverseJoinColumns={
            @JoinColumn(name="breed_id")
    }
    )
	private List<Breed> breeds;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="pet", fetch = FetchType.EAGER)
	private List<Photo> photos;

	public Pet() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEyeColor() {
		return this.eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getHasAward() {
		return this.hasAward;
	}

	public void setHasAward(String hasAward) {
		this.hasAward = hasAward;
	}

	public String getHasAwardInParent() {
		return this.hasAwardInParent;
	}

	public void setHasAwardInParent(String hasAwardInParent) {
		this.hasAwardInParent = hasAwardInParent;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPurebred() {
		return this.purebred;
	}

	public void setPurebred(String purebred) {
		this.purebred = purebred;
	}

	public String getTypeOfColor() {
		return this.typeOfColor;
	}

	public void setTypeOfColor(String typeOfColor) {
		this.typeOfColor = typeOfColor;
	}

	public String getWoolLength() {
		return this.woolLength;
	}

	public void setWoolLength(String woolLength) {
		this.woolLength = woolLength;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Breed> getBreeds() {
		return this.breeds;
	}

	public void setBreeds(List<Breed> breeds) {
		this.breeds = breeds;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setPet(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setPet(null);

		return photo;
	}

}