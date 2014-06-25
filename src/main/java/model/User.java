package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity(name = "user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="avatar_filepath")
	private String avatarFilepath;

	private String email;

	private String name;

	private String password;

	@Column(name="phone_number")
	private String phoneNumber;

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatarFilepath() {
		return this.avatarFilepath;
	}

	public void setAvatarFilepath(String avatarFilepath) {
		this.avatarFilepath = avatarFilepath;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}