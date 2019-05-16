package com.jazzchris.musicchallenge.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jazzchris.musicchallenge.entity.Composer;
import com.jazzchris.musicchallenge.entity.Piece;

@Repository
public class PieceDAOImpl implements PieceDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Piece> getPieces() {
		Session session = entityManager.unwrap(Session.class);
		Query<Piece> theQuery = session.createQuery(
				"from Piece order by title", Piece.class);
		
		List<Piece> thePieces = theQuery.getResultList();
		
		return thePieces;
	}

	@Override
	public Piece getPiece(int id) {

		Session session = entityManager.unwrap(Session.class);
		
		Piece thePiece = session.get(Piece.class, id);
		
		return thePiece;
	}

	@Override
	public void savePiece(Piece piece) {
		System.out.println(">>>>Trying to save the Peace...");
		System.out.println(piece);
		Session session = entityManager.unwrap(Session.class);

		Composer theComp = session.get(Composer.class, piece.getComposer().getId());
		piece.setComposer(theComp);
		session.saveOrUpdate(piece);
	}

	@Override
	public void deletePiece(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		Piece thePiece = session.get(Piece.class, id);
		session.delete(thePiece);
	}

	@Override
	public List<Piece> getPiecesByComposer(int theId) {

		Session session = entityManager.unwrap(Session.class);
		
		Query<Piece> theQuery = session.createQuery(
				"from Piece where composer_id=:composerId order by title", Piece.class);
		theQuery.setParameter("composerId", theId);
		
		List<Piece> pieces = theQuery.getResultList();
		
		return pieces;
	}

}
