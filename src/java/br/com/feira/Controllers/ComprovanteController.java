/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Comprovante;
import br.com.feira.Entities.Feira;
import br.com.feira.Relatorios.Rel_Comprovante;
import br.com.feira.Services.ComprovanteService;
import br.com.feira.Utils.MensagemUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ComprovanteController implements Serializable{
   
    @Inject
    private ComprovanteService service;
    
    private Comprovante comprovante;
    private List<Comprovante> lista;
    
    private Feira feira;
    private String perfil;

    
    @PostConstruct
    public void init(){
      comprovante = new Comprovante();
      lista = new ArrayList<>();
      feira = new Feira();
      perfil = new String();
      
    }
    
    public String salvar(){
        String erro = service.salvar(comprovante);
        
        if(erro == null){
            MensagemUtil.addMensagemInfo("Comprovante alterado.");
            return null;
        }else{
            MensagemUtil.addMensagemError("Erro ao salvar: " + erro);
            return erro;
        }
    }
    
    public void gerarComprovanteSessao(boolean download) throws IOException{
        Rel_Comprovante rel = new Rel_Comprovante();
        List<HashMap> list = new ArrayList(); 
        HashMap hm = new HashMap();
        
        Comprovante c = (Comprovante) getSession().getAttribute("dados");
        System.out.println("----IdComprovante: " + c.getIdComprovante());
        hm.put("idComprovante", c.getIdComprovante());
        hm.put("nome", c.getNome());
        hm.put("registro", c.getRegistro());
        hm.put("tipo", c.getTipo());
        
        list.add(hm);
        rel.getComprovante(list, download);
    }
    
    public void gerarComprovanteId(Long id, boolean download) throws IOException{
        Rel_Comprovante rel = new Rel_Comprovante();
        List<HashMap> list = new ArrayList(); 
        HashMap hm = new HashMap();
        
        Comprovante c = service.buscarComprovate(id);
        
        hm.put("idComprovante", c.getIdComprovante());
        hm.put("nome", c.getNome());
        hm.put("registro", c.getRegistro());
        hm.put("tipo", c.getTipo());
        
        list.add(hm);
        rel.getComprovante(list, download);
    }
    
    public void sessaoComprovantes(Feira f){
        Long id = f.getIdFeira();
        getSession().setAttribute("idfeira_pesq", id);
//        lista = service.todosPorFeira(id);
    }
    
    public ComprovanteService getService() {
        return service;
    }

    public void setService(ComprovanteService service) {
        this.service = service;
    }

    public List<Comprovante> getLista() {
        Long id = (Long) getSession().getAttribute("idfeira_pesq");
        lista = service.todosPorFeira(id);
        return lista;
    }

    public void setLista(List<Comprovante> lista) {
        this.lista = lista;
    }

    public Comprovante getComprovante() {
        return comprovante;
    }

    public void setComprovante(Comprovante comprovante) {
        this.comprovante = comprovante;
    }
    
    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
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
