/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Perfil;
import br.com.feira.Services.PerfilService;
import br.com.feira.Utils.MensagemUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vagner.gomes
 */

@Named
@RequestScoped
public class PerfilController implements Serializable {
    
    @Inject private PerfilService service;
    
    private Perfil perfil;
    private List<Perfil> lista;

    public PerfilController() {
    }
    
    
    @PostConstruct
    public void init(){
        perfil = new Perfil();
        lista = service.todos();
    }

    
    public void salvar(){
        String erro = service.salvar(perfil);
        
        if(erro == null){
            MensagemUtil.addMensagemInfo("Tipo de cadastro salvo.");
            perfil = new Perfil();
        }else{
            MensagemUtil.addMensagemError("Erro ao salvara: " + erro);
        }
    }
    
    
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getLista() {
        return lista;
    }

    public void setLista(List<Perfil> lista) {
        this.lista = lista;
    }
    
    
    
    
    
}
