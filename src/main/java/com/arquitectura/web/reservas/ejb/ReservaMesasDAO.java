

package com.arquitectura.web.reservas.ejb;

import com.arquitectura.web.reservas.entity.ReservaMesa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
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
    
    public ReservaMesa findById(Integer id_reserva){
        ReservaMesa r = em.find(ReservaMesa.class, id_reserva);
        if(r == null)
            throw new EntityNotFoundException("No existe Reserva de Mesa con ID: "+id_reserva);
        
        return r;
    }
    
    public void crearReservaMesas(ReservaMesa r){        
        em.persist(r);
        em.flush();
    }
    
    public void updateReservaMesas(ReservaMesa r){
        ReservaMesa rmesas = findById(r.getIdReserva());  
        
        rmesas.setFecha(r.getFecha());
        rmesas.setRangoHora(r.getRangoHora());
        rmesas.setCantidadSolicitada(r.getCantidadSolicitada());
        rmesas.setCliente(r.getCliente());
        rmesas.setMesa(r.getMesa());
        
        em.persist(rmesas);        
    }
    
    public void deleteReservaMesas(ReservaMesa r){
        ReservaMesa rmesas = findById(r.getIdReserva());        
        em.remove(rmesas); 
        em.flush();
    }
    
    public List<ReservaMesa> getReservaMesas(){
        Query q = em.createQuery("select r from ReservaMesas r");
        return (List<ReservaMesa>) q.getResultList();        
    }

}
