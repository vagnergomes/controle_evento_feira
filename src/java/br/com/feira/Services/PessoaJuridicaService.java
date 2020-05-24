/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.PessoaJuridica;
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
public class PessoaJuridicaService {
    
    @PersistenceContext
    protected EntityManager em;
    
    
    public PessoaJuridicaService(){
        
    }
    
    public String salvar(PessoaJuridica pessoa){
        try{
            em.merge(pessoa);
            em.flush();
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
    
    public String editar(PessoaJuridica pessoa, Long idPj){
        try{
            pessoa.setIdPessoa(idPj);
            pessoa.getComprovante().setIdComprovante(idPj);
            pessoa.getContato().setIdContato(idPj);
            
            em.merge(pessoa);
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
    
    public PessoaJuridica obter(int id){
        return em.find(PessoaJuridica.class, id);
    }
    
    public PessoaJuridica obterId(int id){
        Query query = em.createQuery("Select p from PessoaJuridica as p where p.idPessoa = ?1", PessoaJuridica.class);
        query.setParameter(1, id);
        PessoaJuridica p = (PessoaJuridica) query.getSingleResult();
        return p;
    }
    
    public PessoaJuridica obterPorCnpj(String cnpj){
        Query query = em.createQuery("Select p from PessoaJuridica as p where p.cnpj = ?1", PessoaJuridica.class);
        query.setParameter(1, cnpj);
        PessoaJuridica p = (PessoaJuridica) query.getSingleResult();
        return p;
    }
    
    public boolean buscaCnpj(String cnpj){
        Query query = em.createQuery("Select p from PessoaJuridica as p where p.cnpj = ?1", PessoaJuridica.class);
        query.setParameter(1, cnpj);
        List<PessoaJuridica> r = query.getResultList();
        return !r.isEmpty();
    }
    
    public List<PessoaJuridica> todos(){
        
        TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica as p ", PessoaJuridica.class);
        return query.getResultList();
    }
    
    public String excluir (int id)
    {
        try{
            PessoaJuridica p = obter(id);
            em.remove(p);
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
}
