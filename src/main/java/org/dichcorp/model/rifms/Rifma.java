package org.dichcorp.model.rifms;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dichcorp.model.users.User;

@Entity
@Table(name = "RIFMS")
public class Rifma {

    private Integer idRifm;
    private Rifma parentRifma;
    private Integer idParentRifma;
    private User user;
    private String text;
    private Date createDate;
    private Set<Rifma> childRifms = new HashSet<Rifma>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rifma", unique = true, nullable = false)
    public Integer getIdRifm() {
	return idRifm;
    }

    public void setIdRifm(Integer idRifm) {
	this.idRifm = idRifm;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parent_rifma")
    public Rifma getParentRifma() {
	return parentRifma;
    }

    public void setParentRifma(Rifma parentRifma) {
	this.parentRifma = parentRifma;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login", nullable = false)
    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    @Column(name = "text_rifm", length = 60)
    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
	return createDate;
    }

    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    @OneToMany(mappedBy = "parentRifma", fetch = FetchType.LAZY)
    public Set<Rifma> getChildRifms() {
	return childRifms;
    }

    public void setChildRifms(Set<Rifma> childRifms) {
	this.childRifms = childRifms;
    }

    public boolean hasParentRifma() {
	return parentRifma != null;
    }

    @Transient
    public Integer getIdParentRifma() {
	return idParentRifma;
    }

    public void setIdParentRifma(Integer idParentRifma) {
	this.idParentRifma = idParentRifma;
    }
}
