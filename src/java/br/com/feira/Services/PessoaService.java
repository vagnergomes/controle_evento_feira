/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.Pessoa;
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
public class PessoaService {
    
    @PersistenceContext
    protected EntityManager em;
    
    
    public PessoaService(){
        
    }
    
    public String salvar(Pessoa pessoa){
        try{
            em.merge(pessoa);
            em.flush();
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
    
    public Pessoa obter(int id){
        return em.find(Pessoa.class, id);
    }
    
    public List<Pessoa> todos(){
        
        TypedQuery<Pessoa> query = em.createQuery("select p from Pessoa as p ", Pessoa.class);
        return query.getResultList();
    }
    
    public String excluir (int id)
    {
        try{
            Pessoa p = obter(id);
            em.remove(p);
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
}
