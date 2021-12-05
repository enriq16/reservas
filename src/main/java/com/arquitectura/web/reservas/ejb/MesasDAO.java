package com.arquitectura.web.reservas.ejb;



import com.arquitectura.web.reservas.entity.Mesa;
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
    

    public Mesa getMesaById(Integer idMesa){
        Mesa m = em.find(Mesa.class, idMesa);
        if(m == null)
            throw new EntityNotFoundException("No existe mesa con id: "+idMesa);
        
        return m;
    }
    
    public void crearRest(Mesa m){        
        em.persist(m);   
        em.flush();
    }
    
    public void updateRest(Mesa m){        
        Mesa rest = getMesaById(m.getId());  
                
        rest.setNombreMesa(m.getNombreMesa());
        rest.setPosicionLat(m.getPosicionLat());
        rest.setPosicionLon(m.getPosicionLon());
        rest.setPlanta(m.getPlanta());
        rest.setCapacidad(m.getCapacidad());
        rest.setRestaurante(m.getRestaurante());
        
        em.flush();
    }
    
    public void deleteRest(Mesa m){
        Mesa rest = getMesaById(m.getId());        
        em.remove(rest); 
        em.flush();
    }
    
    public List<Mesa> getMesas(Integer idRestorante){
        Query q;
        if(idRestorante != null){
            q = em.createQuery("select m from Mesa m where m.restaurante.idRestaurante = :idRest order by m.id");
            q.setParameter("idRest", idRestorante);
        }else{
            q = em.createQuery("select m from Mesa m order by m.restaurante.idRestaurante");
        }
        
        return (List<Mesa>) q.getResultList();        
    }
}
