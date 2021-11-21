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
    

    public Mesas getMesaById(Integer idMesa){
        Mesas m = em.find(Mesas.class, idMesa);
        if(m == null)
            throw new EntityNotFoundException("No existe mesa con id: "+idMesa);
        
        return m;
    }
    
    public void crearRest(Mesas m){        
        em.persist(m);        
    }
    
    public void updateRest(Mesas m){        
        Mesas rest = getMesaById(m.getId());  
                
        rest.setNombreMesa(m.getNombreMesa());
        rest.setPosicionLat(m.getPosicionLat());
        rest.setPosicionLon(m.getPosicionLon());
        rest.setPlanta(m.getPlanta());
        rest.setCapacidad(m.getCapacidad());
        rest.setIdRestaurante(m.getIdRestaurante());
        
        em.persist(rest);        
    }
    
    public void deleteRest(Mesas m){
        Mesas rest = getMesaById(m.getId());        
        em.remove(rest); 
    }
    
    public List<Mesas> getMesas(Integer idRestorante){
        Query q = em.createQuery("select m from Mesas m where m.idRestaurante.idRestaurante = :idRest");
        q.setParameter("idRest", idRestorante);
        return (List<Mesas>) q.getResultList();        
    }
}
