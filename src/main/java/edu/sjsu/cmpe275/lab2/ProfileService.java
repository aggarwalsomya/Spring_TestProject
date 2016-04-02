package edu.sjsu.cmpe275.lab2;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

	// An EntityManager will be automatically injected from EntityManagerFactory
	// setup on
	// spring-context.xml
	@PersistenceContext
	private EntityManager em;

	/*Since we've setup <tx:annotation-config> and transaction manager on
	 spring-context.xml, any bean method annotated with @Transactional will cause Spring to
	 magically call begin() and commit() at the start/end of the method. If exception occurs
	 it will also call rollback()*/

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

	@Transactional
	public void add(Profile p) {
		em.merge(p);
	}
	
	@Transactional
	public void update1(Profile p) {
		em.merge(p);
	}

//	@Transactional
//	public void update(Profile p, String id) {
//		Query q = em.createQuery("insert into Profile ( SET p.firstname=:fname "
//				+ ",p.lastname = :lname" 
//				+ ",p.address = :address"
//				+ ",p.organization = :organization"
//				+ ",p.email = :email"
//				+ ",p.about_myself = :aboutMyself"
//				+ " where p.id=:userId");
//		
//		q.setParameter("userId", Integer.parseInt(id));
//		q.setParameter("fname", p.getFname());
//		q.setParameter("lname", p.getLname());
//		q.setParameter("address", p.getAddress());
//		q.setParameter("email", p.getEmail());
//		q.setParameter("organization", p.getOrg());
//		q.setParameter("aboutMyself", p.getAbout());
//		
//		q.executeUpdate();
//	}

	public boolean exists(int id) {
		Profile pr = new Profile();
		pr.setId(id);
		return em.find(Profile.class, id) != null;
	}

	@Transactional
	public void delete(int id) {
		Profile entity = new Profile();
		entity.setId(id);
		System.out.println("ProfileService::Delete called for id:" +id );
		em.remove(em.contains(entity) ? entity : em.merge(entity));
//		em.flush();
//		Query q = em.createQuery("DELETE from Profile p where p.id= :userId");
//		q.setParameter("userId", Integer.parseInt(id));
//		return q.executeUpdate();
	}
}
