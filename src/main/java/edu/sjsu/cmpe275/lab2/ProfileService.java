package edu.sjsu.cmpe275.lab2;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

	@PersistenceContext
	private EntityManager em;


	/** This method is used to get the details of a specific user with the id as passed.
	 * 
	 * @param id of the user whose record needs to be searched
	 * @return Profile of the user.
	 */
	@Transactional
	public Profile getSpecificUser(String id) {
		Profile p;
		Query q = em.createQuery("Select p from Profile p where p.id=:arg1");
		q.setParameter("arg1", Integer.parseInt(id));
		try {
			p = (Profile) q.getSingleResult();
		} catch (NoResultException e) {
			p = null;
		}
		return p;
	}

	/**
	 * This method is used to add a new profile.
	 * @param profile contains the profile which needs to be added
	 */
	@Transactional
	public void add(Profile p) {
		em.merge(p);
	}
	
	/**
	 * Use to update the record with the profile p. If the record does not exist it will create a new record.
	 * @param profile object
	 */
	@Transactional
	public void update(Profile p) {
		em.merge(p);
	}

	/**
	 * will check if the user with the id exists?
	 * @param id of the user
	 * @return yes if it exists else no
	 */
	public boolean exists(int id) {
		Profile pr = new Profile();
		pr.setId(id);
		return em.find(Profile.class, id) != null;
	}

	/**
	 * deletes the user with the specified id
	 * @param id of the user
	 */
	@Transactional
	public void delete(int id) {
		Profile entity = new Profile();
		entity.setId(id);
		System.out.println("ProfileService::Delete called for id:" +id );
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}
}
