/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;


import br.com.feira.Entities.Feira;
import br.com.feira.Entities.Historico;
import br.com.feira.Utils.MensagemUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

/**
 *
 * @author vagner.gomes
 */
@Stateless
public class FeiraService implements Serializable{
    
    @PersistenceContext
    private EntityManager em;

    public FeiraService() {
    }
    
    public String salvar(Feira feira){
        try{
            em.merge(feira);
            em.flush();
            return null;
        }catch(Exception e){
            return "Erro: " + e.getMessage();
        }
    }
    
    public Feira obter(int id){
        return em.find(Feira.class, id);
    }
    
    public Feira buscarFeiraAberta(int ano){
            
        Query query = em.createQuery("Select p from Feira as p where p.ano = ?1 and p.inscricaoAberta = true", Feira.class);
        query.setParameter(1, ano);
        List<Feira> list = query.getResultList();
        if(list.isEmpty())
            return null;
        else 
            return (Feira)query.getResultList().get(0);
    }
        
    public List<Feira> todos(){
        TypedQuery<Feira> query = em.createQuery("Select p from Feira as p", Feira.class);
        return query.getResultList();
    }
    
}
