/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Services;

import br.com.feira.Entities.Historico;
import br.com.feira.Utils.MensagemUtil;
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
public class HistoricoService {
    
    @PersistenceContext
    private EntityManager em;

    public HistoricoService() {
    }
    
    public String salvar(Historico historico){
        try{
            em.merge(historico);
            em.flush();
            return null;
        }catch(Exception ex){
            return "Erro: " + ex.getMessage();
        }
    }
     
     public List<Object[]> buscarParticipantes(Long id, String perfil) {
        try{
        StoredProcedureQuery query = em
                .createStoredProcedureQuery("selecionar_participantes")
                .registerStoredProcedureParameter(
                        "id",
                        Long.class,
                        ParameterMode.IN
                )
                .registerStoredProcedureParameter(
                        "perfil",
                        String.class,
                        ParameterMode.IN
                )
                .setParameter("id", id)
                .setParameter("perfil", perfil);

        query.execute();
        List<Object[]> list = query.getResultList();
        return list;
        }catch(Exception e){
            MensagemUtil.addMensagemError("Erro: " + e.getMessage());
            return null;
        }
    }
    
    public List<Historico> todos(){
        TypedQuery<Historico> query = em.createQuery("select p from Historico as p ", Historico.class);
        return query.getResultList();
    }
    
}
