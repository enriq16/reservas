
package com.arquitectura.web.reservas.ejb;

import com.arquitectura.web.reservas.entity.Cliente;
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
public class ClienteDAO {
    @PersistenceContext(unitName = "hibernate_jpa_pu")
    private EntityManager em;

    public Cliente findById(Integer id){
        Cliente c = em.find(Cliente.class, id);
        if(c == null){
            throw new EntityNotFoundException("No existe Cliente con ID: "+id);
        }
        return c;
    }

    public void agregar(Cliente cliente){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(cliente);
        tr.commit();
        
        System.out.println("Cliente: "+cliente.toString());            
    }
    
    public void update(Cliente cliente){
        Cliente c = em.find(Cliente.class, cliente.getId());
        
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        
        c.setID(cliente.getID());    
        c.setCedula(cliente.getCedula());    
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
        
        
        tr.commit();                
    }

    public List<Cliente> listar(){
        Query q = em.createQuery("select c from Cliente c");
        return (List<Cliente>) q.getResultList();
    }
}
