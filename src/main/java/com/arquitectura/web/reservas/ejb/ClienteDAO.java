
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
    @PersistenceContext(unitName = "hibernate_jpa_pu_reservas")
    private EntityManager en;

    public Cliente findById(Integer id){
        Cliente c = en.find(Cliente.class, id);
        if(c == null){
            throw new EntityNotFoundException("No existe Cliente con ID: "+id);
        }
        return c;
    }

    public void agregar(Cliente cliente){
        
        en.persist(cliente);
               
        System.out.println("Cliente: "+cliente.toString());            
    }
    
    public void update(Cliente cliente){
        Cliente c = en.find(Cliente.class, cliente.getId());
        
        
        c.setId(cliente.getId());    
        c.setCedula(cliente.getCedula());    
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
        
        en.persist(c);
                      
    }

    public List<Cliente> listar(){
        Query q = en.createQuery("select c from Cliente c");
        return (List<Cliente>) q.getResultList();
    }
}
