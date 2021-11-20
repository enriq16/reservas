

package com.arquitectura.web.reservas.ejb;


import com.arquitectura.web.reservas.entity.Mesas;
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
public class MesasDAO {
    @PersistenceContext(unitName = "hibernate_jpa_pu_reservas")
    private EntityManager em;
    
    public Mesas findById(Integer id){
        Mesas r = em.find(Mesas.class, id);
        if(r == null)
            throw new EntityNotFoundException("No existe Mesa con ID: "+id);
        
        return r;
    }
    
    public void crearMesas(Mesas r){        
        em.persist(r);        
    }
    
    public void updateMesas(Mesas r){
        Mesas mesas = findById(r.getId());  
        mesas.setNombreMesa(r.getNombreMesa());
        mesas.setPosicionLat(r.getPosicionLat());
        mesas.setPosicionLon(r.getPosicionLon());
        mesas.setPlanta(r.getPlanta());
        mesas.setCapacidad(r.getCapacidad());
        em.persist(mesas);        
    }
    
    public void deleteMesas(Mesas r){
        Mesas mesas = findById(r.getId());        
        em.remove(mesas); 
    }
    
    public List<Mesas> getMesas(){
        Query q = em.createQuery("select r from Mesas r");
        return (List<Mesas>) q.getResultList();        
    }
}
