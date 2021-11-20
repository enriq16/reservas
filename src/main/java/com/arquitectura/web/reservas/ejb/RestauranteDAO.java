/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arquitectura.web.reservas.ejb;

import com.arquitectura.web.reservas.entity.Restaurante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USUARIO
 */
@Stateless
public class RestauranteDAO {
    @PersistenceContext(unitName = "hibernate_jpa_pu")
    private EntityManager em;
    
    public Restaurante findById(Integer idRestaurante){
        Restaurante r = em.find(Restaurante.class, idRestaurante);
        if(r == null)
            throw new EntityNotFoundException("No existe Cliente con ID: "+idRestaurante);
        
        return r;
    }
    
    public void crearRest(Restaurante r){
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }
    
    public void updateRest(Restaurante r){
        Restaurante rest = findById(r.getIdRestaurante());
        em.getTransaction().begin();
        em.persist(rest);
        em.getTransaction().commit();
    }
    
    public void deleteRest(Restaurante r){
        Restaurante rest = findById(r.getIdRestaurante());
        em.getTransaction().begin();
        em.remove(rest);
        em.getTransaction().commit();
    }
    
    public List<Restaurante> getRestaurantes(){
        Query q = em.createQuery("select r from Restaurante r");
        return (List<Restaurante>) q.getResultList();        
    }
}
