/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Feira;
import br.com.feira.Entities.Historico;
import br.com.feira.Services.FeiraService;
import br.com.feira.Services.HistoricoService;
import br.com.feira.Utils.MensagemUtil;
import java.io.IOException;
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
public class FeiraController implements Serializable{

    @Inject
    private FeiraService service;
    
    @Inject private HistoricoService serviceHist;
    
    private List<Historico> participantes;
    private String teste;
    
    private Feira feira;
    private List<Feira> lista;
    
    public FeiraController(){    
    }
    
    @PostConstruct
    public void init(){
        feira = new Feira();
        lista = service.todos();
        participantes = new ArrayList<>();
        teste = "0";
    }
       
    public void salvar() throws IOException{
        String erro =  service.salvar(feira);
        
        if(erro == null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("feiras.xhtml");
            MensagemUtil.addMensagemInfo("Feira salva.");
        }else{
            MensagemUtil.addMensagemInfo(erro);
        }
    }
    
    public void editar(Feira feira){
        this.feira = feira;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }

    public List<Feira> getLista() {
        return lista;
    }

    public void setLista(List<Feira> lista) {
        this.lista = lista;
    }

    public List<Historico> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Historico> participantes) {
        this.participantes = participantes;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
    
    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
}
