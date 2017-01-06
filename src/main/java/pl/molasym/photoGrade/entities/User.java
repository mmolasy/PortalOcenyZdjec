package pl.molasym.photoGrade.entities;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	private Long userId;

	@Column(name = "NICKNAME")
	private String nickName;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinTable(name="USER_COLLEAGUE",
			joinColumns={@JoinColumn(name="USER_ID")},
			inverseJoinColumns={@JoinColumn(name="COLLEAGUE_ID")})
	private Set<User> friends = new HashSet<User>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Photo> photos;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private List<Invitation> receivedInvitations;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name = "EMAIL")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "AGE")
	@Formula("lower(datediff(curDate(), BIRTH_DATE)/365)")
	private Integer age;

	@Column(name = "PHOTOS_QUANTITY")
	private Integer photosQuantity;

	public User(){
		friends = new HashSet<User>();
		photos = new ArrayList<Photo>();
		age = new Integer(0);
		photosQuantity = new Integer(0);
		receivedInvitations = new ArrayList<Invitation>();

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPhotosQuantity() {
		return photosQuantity;
	}

	public void setPhotosQuantity(Integer photosQuantity) {
		this.photosQuantity = photosQuantity;
	}

	public List<Invitation> getReceivedInvitations() {
		return receivedInvitations;
	}

	public void setReceivedInvitations(List<Invitation> receivedInvitations) {
		this.receivedInvitations = receivedInvitations;
	}

	public User(String nickName, String password, Set<User> friends, List<Photo> photos, Date createdDate, String email, Date birthDate, Integer age, Integer photosQuantity) {
		this.nickName = nickName;
		this.password = password;
		this.friends = friends;
		this.photos = photos;
		this.createdDate = createdDate;
		this.email = email;
		this.birthDate = birthDate;
		this.age = age;
		this.photosQuantity = photosQuantity;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", nickName='" + nickName + '\'' +
				", password='" + password + '\'' +
				", createdDate=" + createdDate +
				", email='" + email + '\'' +
				", birthDate=" + birthDate +
				", age=" + age +
				", photosQuantity=" + photosQuantity +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
		if (nickName != null ? !nickName.equals(user.nickName) : user.nickName != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (friends != null ? !friends.equals(user.friends) : user.friends != null) return false;
		if (photos != null ? !photos.equals(user.photos) : user.photos != null) return false;
		if (receivedInvitations != null ? !receivedInvitations.equals(user.receivedInvitations) : user.receivedInvitations != null)
			return false;
		if (createdDate != null ? !createdDate.equals(user.createdDate) : user.createdDate != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
		if (age != null ? !age.equals(user.age) : user.age != null) return false;
		return photosQuantity != null ? photosQuantity.equals(user.photosQuantity) : user.photosQuantity == null;

	}
}
