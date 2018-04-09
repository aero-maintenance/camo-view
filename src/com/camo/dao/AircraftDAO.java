package com.camo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.camo.entities.Aircraft;

public class AircraftDAO {
	
	private static final String JPQL_SELECT_LIKE_IMMAT 	= "SELECT a FROM Aircraft a WHERE a.immatriculation LIKE :immatriculation";
	private static final String JPQL_SELECT_PAR_IMMAT 	= "SELECT a FROM Aircraft a WHERE a.immatriculation=:immatriculation";
	private static final String JPQL_SELECT_BY_OWNER 	= "SELECT a FROM Aircraft a WHERE a.proprietaire.user_id=:user";
    private static final String PARAM_IMMAT           	= "immatriculation";
    private static final String PARAM_OWNER_ID 			= "user";

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "softwair" )
    private EntityManager em;
    
    public AircraftDAO( EntityManager em) {
    	this.em = em;
    }
    
    public Aircraft update(Aircraft aircraft)throws DAOException {
    	try {
    		return em.merge(aircraft);
    	}catch ( Exception e ){
    		throw new DAOException( e );
    	}
    }

    public Aircraft trouver( long id ) throws DAOException {
        try {
            return em.find( Aircraft.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
   
    //Recherche d'un utilisateur à partir de son adresse email
    public Aircraft trouver( String immat ) throws DAOException {
        Aircraft ac = null;
        Query requete = em.createQuery( JPQL_SELECT_PAR_IMMAT );
        requete.setParameter( PARAM_IMMAT, immat );
        try {
            ac = (Aircraft) requete.getSingleResult();
        } catch ( NoResultException e) {
        	throw new DAOException( e );            
        }
        return ac;
    }

    public void creer( Aircraft aircraft ) throws DAOException {
        try {
        	em.getTransaction().begin();
            em.persist( aircraft );
            em.getTransaction().commit();
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }
    
    public List<Aircraft> filtrer(Long user_id) throws DAOException {
        try {
        	em.getTransaction().begin();
            TypedQuery<Aircraft> query = em.createQuery( JPQL_SELECT_BY_OWNER, Aircraft.class );
            query.setParameter( PARAM_OWNER_ID, user_id );
            em.getTransaction().commit();
            return query.getResultList();
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }

    public List<Aircraft> lister() throws DAOException {
        try {
        	em.getTransaction().begin();
            TypedQuery<Aircraft> query = em.createQuery( "SELECT c FROM Aircraft c ORDER BY c.ac_id", Aircraft.class );
            em.getTransaction().commit();
            return query.getResultList();
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }

    public void supprimer( Aircraft aircraft ) throws DAOException {
        try {
            em.remove( em.merge( aircraft ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
    
    public Aircraft saveAircraft(Aircraft aircraft) {
        try {
          em.getTransaction().begin();
          aircraft = em.merge(aircraft);
          em.getTransaction().commit();
        } catch (Exception e) {
          em.getTransaction().rollback();
        }
        return aircraft;
      }
    
    public List<Aircraft> rechercher( String nom ) throws DAOException {
    	try {
        	em.getTransaction().begin();
        	TypedQuery<Aircraft> query = em.createQuery( JPQL_SELECT_LIKE_IMMAT, Aircraft.class );
        	query.setParameter( PARAM_IMMAT, "%" + nom + "%");
            em.getTransaction().commit();  
            return query.getResultList();
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }
}
