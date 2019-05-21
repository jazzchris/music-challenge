package com.jazzchris.musicchallenge.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="composer")
public class Composer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_birth")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateBirth;
	
	@Column(name="date_death")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeath;
	
	@OneToMany(fetch=FetchType.LAZY, 
			mappedBy="composer",
			cascade=CascadeType.ALL)
	private List<Piece> pieces;
	
	public Composer() {}
	
	public Composer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public LocalDate getDateDeath() {
		return dateDeath;
	}

	public void setDateDeath(LocalDate dateDeath) {
		this.dateDeath = dateDeath;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	@Override
	public String toString() {
		return "Composer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName	+ "]";
	}

	public void add(Piece tempPiece) {
		if (pieces == null) {
			pieces = new ArrayList<>();
		}
		pieces.add(tempPiece);
		
		tempPiece.setComposer(this);
	}
}
