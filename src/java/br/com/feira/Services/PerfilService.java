/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.Perfil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vagner.gomes
 */
@Stateless
public class PerfilService implements Serializable{
    
    @PersistenceContext
    private EntityManager em;

    public PerfilService() {
    }
    
    public String salvar(Perfil perfil){
        try{
            em.merge(perfil);
            em.flush();
            return null;
        }catch(Exception  ex){
            return ex.getMessage();
        }
    }
    
    public Perfil obter(Long id){
        return em.find(Perfil.class, id);
    }
    
    public List<Perfil> todos(){
        TypedQuery<Perfil> query = em.createQuery("Select p FROM Perfil as p", Perfil.class);
        return query.getResultList();
    }
    
}
