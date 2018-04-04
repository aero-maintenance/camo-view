package com.camo.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.camo.entities.Utilisateur;

public class UtilisateurDAO {	
	private static final String JPQL_SELECT_PAR_NOM 	= "SELECT u FROM Utilisateur u WHERE u.nom_aeroclub LIKE :nom";
	private static final String JPQL_SELECT_PAR_EMAIL 	= "SELECT u FROM Utilisateur u WHERE u.email=:email";
    private static final String PARAM_EMAIL           	= "email";
    private static final String PARAM_NOM				= "nom";
	    
    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "softwair" )
    private EntityManager em;
    
    public UtilisateurDAO(EntityManager em) {
    	this.em = em;
    }

    public Utilisateur trouver( long id ) throws DAOException {
        try {
        	em.getTransaction().begin();
            return em.find( Utilisateur.class, id );
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }
    
    //Recherche d'un utilisateur à partir de son adresse email
    public Utilisateur trouver( String email ) throws DAOException {
        Utilisateur utilisateur = null;
        em.getTransaction().begin();
        Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
        requete.setParameter( PARAM_EMAIL, email );
        em.getTransaction().commit();
        
        try {
            utilisateur = (Utilisateur) requete.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
        return utilisateur;
    }
    
    public List<Utilisateur> rechercher( String nom ) throws DAOException {
    	try {
        	em.getTransaction().begin();
        	TypedQuery<Utilisateur> query = em.createQuery( JPQL_SELECT_PAR_NOM, Utilisateur.class );
        	query.setParameter( PARAM_NOM, "%" + nom + "%");
            em.getTransaction().commit();  
            return query.getResultList();
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }
    
    public void creer( Utilisateur utilisateur ) throws DAOException {
        try {
        	em.getTransaction().begin();
        	em.persist( utilisateur );
            em.getTransaction().commit();
            
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }
    


    public List<Utilisateur> lister() throws DAOException {
        try {
        	em.getTransaction().begin();
        	TypedQuery<Utilisateur> query = em.createQuery( "SELECT c FROM Utilisateur c ORDER BY c.user_id", Utilisateur.class );
            em.getTransaction().commit();            
            return query.getResultList();
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }

    public void supprimer( Utilisateur utilisateur ) throws DAOException {
        try {
        	em.getTransaction().begin();
        	em.remove( em.merge( utilisateur ) );
            em.getTransaction().commit();            
        } catch ( Exception e ) {
        	em.getTransaction().rollback();
            throw new DAOException( e );
        }
    }	

    public Utilisateur saveUtilisateur(Utilisateur user) {
        try {
          em.getTransaction().begin();
          user = em.merge(user);
          em.getTransaction().commit();
        } catch (Exception e) {
          em.getTransaction().rollback();
        }
        return user;
      }
}
