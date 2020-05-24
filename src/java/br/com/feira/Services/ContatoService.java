/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.Contato;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vagner.gomes
 */
@Stateless
public class ContatoService implements Serializable {
    
    @PersistenceContext
    protected EntityManager em;
    
    public ContatoService(){
       
    }
    
    public String salvar(Contato contato){
        try{
            em.merge(contato);
            em.flush();
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
    
    public Contato obter(Long id){
        return em.find(Contato.class, id);
    }
    
    public boolean buscaEmail(String email){
        Query query = em.createQuery("Select p from Contato as p where p.email = ?1", Contato.class);
        query.setParameter(1, email);
        List<Contato> r = query.getResultList();
        return !r.isEmpty();
    }
    
    public Contato obterContato(Long id){
        Query query = em.createQuery("Select p from Contato as p where p.idContato = ?1", Contato.class);
        query.setParameter(1, id);
        Contato r = (Contato) query.getSingleResult();
        return r;
    }
    
    public List<Contato> todos(){
        
        TypedQuery<Contato> query = em.createQuery("select p from Contato as p ", Contato.class);
        return query.getResultList();
    }
    
    public String excluir (Long id)
    {
        try{
            Contato p = obter(id);
            em.remove(p);
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
}
