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
	private long id;

	@Column(name = "NICKNAME")
	private String nickName;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="USER_COLLEAGUE",
			joinColumns={@JoinColumn(name="USER_ID")},
			inverseJoinColumns={@JoinColumn(name="COLLEAGUE_ID")})
	private Set<User> friends = new HashSet<User>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Photo> photos;

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
	private int age;

	@Column(name = "PHOTOS_QUANTITY")
	private int photosQuantity;
	

}
