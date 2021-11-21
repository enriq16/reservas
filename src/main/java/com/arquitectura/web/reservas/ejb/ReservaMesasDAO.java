

package com.arquitectura.web.reservas.ejb;

import com.arquitectura.web.reservas.entity.ReservaMesas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Karen
 */

@Stateless
public class ReservaMesasDAO {
    @PersistenceContext(unitName = "hibernate_jpa_pu_reservas")
    private EntityManager em;
    
    public ReservaMesas findById(Integer id_reserva){
        ReservaMesas r = em.find(ReservaMesas.class, id_reserva);
        if(r == null)
            throw new EntityNotFoundException("No existe Reserva de Mesa con ID: "+id_reserva);
        
        return r;
    }
    
    public void crearReservaMesas(ReservaMesas r){        
        em.persist(r);        
    }
    
    public void updateReservaMesas(ReservaMesas r){
        ReservaMesas rmesas = findById(r.getIdReserva());  
        rmesas.setFecha(r.getFecha());
        rmesas.setRangoHora(r.getRangoHora());
        rmesas.setCantidadSolicitada(r.getCantidadSolicitada());
       
        em.persist(rmesas);        
    }
    
    public void deleteReservaMesas(ReservaMesas r){
        ReservaMesas rmesas = findById(r.getIdReserva());        
        em.remove(rmesas); 
    }
    
    public List<ReservaMesas> getReservaMesas(){
        Query q = em.createQuery("select r from ReservaMesas r");
        return (List<ReservaMesas>) q.getResultList();        
    }

}
