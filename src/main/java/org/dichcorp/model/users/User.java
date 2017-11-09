package org.dichcorp.model.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dichcorp.model.rifms.Rifma;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "USERS")
public class User {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean enabled;
    private Set<UserRole> userRole = new HashSet<UserRole>(0);
    private Set<Rifma> rifms = new HashSet<Rifma>(0);
    private Integer countFollowers;
    private Integer countFollowing;

    private Set<User> following = new HashSet<User>(0);
    private Set<User> followers = new HashSet<User>(0);

    public User() {

    }

    public User(String login, String firstName, String lastName, String email, String password, boolean enabled) {
	this.login = login;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.enabled = enabled;
    }

    public User(String login, String firstName, String lastName, String email, String password, boolean enabled,
	    Set<UserRole> userRole) {
	this.login = login;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.enabled = enabled;
	this.userRole = userRole;
    }

    @Id
    @Column(name = "login", unique = true, nullable = false, length = 30)
    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, length = 45)
    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Column(name = "email", nullable = false, length = 60)
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Transient
    public String getConfirmPassword() {
	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    public Set<UserRole> getUserRole() {
	return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
	this.userRole = userRole;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Rifma> getRifms() {
	return rifms;
    }

    public void setRifms(Set<Rifma> rifms) {
	this.rifms = rifms;
    }

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH,
	    CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @JoinTable(name = "RIFMO_PLET_FOLLOWING", joinColumns = {
	    @JoinColumn(name = "login_follower") }, inverseJoinColumns = { @JoinColumn(name = "login_following") })
    public Set<User> getFollowing() {
	return following;
    }

    public void setFollowing(Set<User> following) {
	this.following = following;
    }

    @ManyToMany(mappedBy = "following", cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH,
	    CascadeType.REMOVE }, fetch = FetchType.LAZY)
    public Set<User> getFollowers() {
	return followers;
    }

    public void setFollowers(Set<User> followers) {
	this.followers = followers;
    }

    @Formula(value = "(select count(*) from RIFMO_PLET_FOLLOWING r where r.login_following = login)")
    @Basic(fetch = FetchType.EAGER)
    public Integer getCountFollowers() {
	return countFollowers;
    }

    public void setCountFollowers(Integer countFollowers) {
	this.countFollowers = countFollowers;
    }

    @Formula(value = "(select count(*) from RIFMO_PLET_FOLLOWING r where r.login_follower = login)")
    @Basic(fetch = FetchType.EAGER)
    public Integer getCountFollowing() {
	return countFollowing;
    }

    public void setCountFollowing(Integer countFollowing) {
	this.countFollowing = countFollowing;
    }

    @Override
    public String toString() {
	return "User [login=" + login + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
		+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", enabled=" + enabled + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (login == null) {
	    if (other.login != null)
		return false;
	} else if (!login.equals(other.login))
	    return false;
	return true;
    }

}
