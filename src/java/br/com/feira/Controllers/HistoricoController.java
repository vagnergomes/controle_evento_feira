/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Feira;
import br.com.feira.Entities.Historico;
import br.com.feira.Entities.Perfil;
import br.com.feira.Services.HistoricoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vagner.gomes
 */

@Named
@RequestScoped
public class HistoricoController implements Serializable {
    
    @Inject
    private HistoricoService service;

    private Historico historico;
    private List<Historico> lista;
    private List<Historico> participantes;
    private String perfil;

    public HistoricoController() {
    }

    @PostConstruct
    public void init() {
        historico = new Historico();
        lista = service.todos();
        participantes = new ArrayList<>();
        perfil = "";
    }
    
    public void sessaoFeira(Feira feira){
        getSession().setAttribute("feira", feira);
        //participantesFeira();
    }

    public void participantesFeira() {
        Feira feira = (Feira) getSession().getAttribute("feira");
        List<Object[]> list = service.buscarParticipantes(feira.getIdFeira(), perfil);
        for (Object obj : list) {
            Object[] o = (Object[]) obj;
            Historico h = new Historico();
            h.setIdHistorico((Long) o[0]);
            h.setDataCadastro((Date) o[1]);
            h.setEmail((String) o[2]);
            h.setNome((String) o[3]);
            h.setRegistro((String) o[4]);
            h.setTelefone1((String) o[5]);
            h.setTelefone2((String) o[6]);
            h.setTipo((String) o[7]);
            h.setPerfil((String) o[8]);

            participantes.add(h);
        }
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public List<Historico> getLista() {
        return lista;
    }

    public void setLista(List<Historico> lista) {
        this.lista = lista;
    }

    public List<Historico> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Historico> participantes) {
        this.participantes = participantes;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
     public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    
}
