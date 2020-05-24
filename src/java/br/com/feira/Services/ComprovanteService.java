/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.Comprovante;
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
public class ComprovanteService implements Serializable {
    
    @PersistenceContext
    protected EntityManager em;
    
    public ComprovanteService(){
       
    }
    
    public String salvar(Comprovante comprovante){
        try{
            em.merge(comprovante);
            em.flush();
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
    
    public Comprovante obter(int id){
        return em.find(Comprovante.class, id);
    }
    
    public Comprovante buscarComprovate(Long id){
        Query query = em.createQuery("Select p from Comprovante as p where p.idComprovante = ?1", Comprovante.class);
        query.setParameter(1, id);
        Comprovante comp = (Comprovante) query.getSingleResult();
        return comp;
    }
    
    public boolean buscaRegistro(String registro){
        Query query = em.createQuery("Select p from Comprovante as p where p.registro = ?1", Comprovante.class);
        query.setParameter(1, registro);
        List<Contato> r = query.getResultList();
        return !r.isEmpty();
    }
    
    public List<Comprovante> todosPorFeira(Long id){  
        TypedQuery<Comprovante> query = em.createQuery("select p from Comprovante as p WHERE p.feira.idFeira = ?1 ", Comprovante.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
    
    public List<Comprovante> todos(){
        
        TypedQuery<Comprovante> query = em.createQuery("select p from Comprovante as p ", Comprovante.class);
        return query.getResultList();
    }
    
    public String excluir (int id)
    {
        try{
            Comprovante p = obter(id);
            em.remove(p);
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
}
