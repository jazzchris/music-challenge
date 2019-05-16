package com.jazzchris.musicchallenge.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jazzchris.musicchallenge.entity.Composer;

@Repository
public class ComposerDAOImpl implements ComposerDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Composer> getComposers() {

		Session session = entityManager.unwrap(Session.class);
		
		Query<Composer> theQuery = session.createQuery(
				"from Composer order by lastName", Composer.class);
		
		List<Composer> composers = theQuery.getResultList();
		
		return composers;
	}

	@Override
	public Composer getComposer(int id) {

		Session session = entityManager.unwrap(Session.class);
		
		Composer theComposer = session.get(Composer.class, id);
		
		return theComposer;
	}

	@Override
	public void saveComposer(Composer comp) {

		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(comp);
	}

	@Override
	public void deleteComposer(int id) {

		Session session = entityManager.unwrap(Session.class);
		
		Composer theComp = session.get(Composer.class, id);
		session.delete(theComp);
	}
}
