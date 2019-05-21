package com.jazzchris.musicchallenge.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="piece")
public class Piece {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(fetch=FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="composer_id")
	private Composer composer;

	public Piece() {}
	
	public Piece(String title) {
		this.title = title;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Composer getComposer() {
		return composer;
	}

	public void setComposer(Composer composer) {
		this.composer = composer;
	}

	@Override
	public String toString() {
		return "Piece [id=" + id + ", title=" + title + ", " + composer + "]";
	}
	
}
