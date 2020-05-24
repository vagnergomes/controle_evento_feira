/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.PessoaFisica;
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
public class PessoaFisicaService {
    
    @PersistenceContext
    protected EntityManager em;
    
    
    public PessoaFisicaService(){
        
    }
    
    public String salvar(PessoaFisica pessoa){
        try{
            em.merge(pessoa);
            em.flush();
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
    
    public String editar(PessoaFisica pessoa, Long idPj){
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
    
    public PessoaFisica obter(int id){
        return em.find(PessoaFisica.class, id);
    }
    
        public PessoaFisica obterId(int id){
        Query query = em.createQuery("Select p from PessoaFisica as p where p.idPessoa = ?1", PessoaFisica.class);
        query.setParameter(1, id);
        PessoaFisica p = (PessoaFisica) query.getSingleResult();
        return p;
    }
    
    public PessoaFisica obterPorCpf(String cpf){
        Query query = em.createQuery("Select p from PessoaFisica as p where p.cpf = ?1", PessoaFisica.class);
        query.setParameter(1, cpf);
        PessoaFisica p = (PessoaFisica) query.getSingleResult();
        return p;
    }
    
    public boolean buscaCpf(String cpf){
        Query query = em.createQuery("Select p.idPessoa, p.cpf from PessoaFisica as p where p.cpf = ?1", PessoaFisica.class);
        query.setParameter(1, cpf);
        List<PessoaFisica> r = query.getResultList();
        return !r.isEmpty();
    }
    
    public List<PessoaFisica> todos(){
        
        TypedQuery<PessoaFisica> query = em.createQuery("select p from PessoaFisica as p ", PessoaFisica.class);
        return query.getResultList();
    }
    
    public String excluir (int id)
    {
        try{
            PessoaFisica p = obter(id);
            em.remove(p);
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
}
