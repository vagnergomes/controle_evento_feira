/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Comprovante;
import br.com.feira.Entities.Contato;
import br.com.feira.Entities.Feira;
import br.com.feira.Entities.Historico;
import br.com.feira.Entities.PessoaJuridica;
import br.com.feira.Services.FeiraService;
import br.com.feira.Services.HistoricoService;
import br.com.feira.Services.PessoaJuridicaService;
import br.com.feira.Utils.MensagemUtil;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class PessoaJuridicaController implements Serializable{
    
    @Inject
    private PessoaJuridicaService service;
    
    @Inject FeiraService serviceFeira;
    @Inject HistoricoService serviceHist;
    
    private PessoaJuridica pj = new PessoaJuridica();
    private Contato contato = new Contato();
    private Comprovante comprovante = new Comprovante();
    private Feira feira = new Feira();
    private Historico hist = new Historico();
    private List<PessoaJuridica> lista;
    
    Date data = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public String dataAtual() {
          return dateFormat.format(data);
    }
    
    @PostConstruct
    public void init(){
        pj = new PessoaJuridica();
        contato = new Contato();
        comprovante = new Comprovante();
        feira = new Feira();
        hist = new Historico();
        lista = service.todos();
    }
    
    public void limpar(){
        pj.setCnpj("");
        pj.setNome("");
        contato.setEmail("");
        contato.setTelefone1("");
        contato.setTelefone2("");

    }

    public void redirecionaRelatorio() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro-realizado-pj.xhtml");
    }

    public void salvar() throws IOException {
        int ano = (data.getYear() + 1900);
        feira = serviceFeira.buscarFeiraAberta(ano);
        String nome = pj.getNome();
        String cnpj = pj.getCnpj().replaceAll("[- /._]", "");
        pj.setCnpj(cnpj);
        comprovante.setNome(nome);
        comprovante.setRegistro(cnpj);
        comprovante.setTipo("pj");
        comprovante.setPerfil("");
        comprovante.setFeira(feira);
        

        if (feira != null && feira.isInscricaoAberta()) {
            boolean exist = service.buscaCnpj(cnpj);
            pj.setDataCadastro(data);
            pj.setContato(contato);
            pj.setComprovante(comprovante);
            pj.setFeira(feira);
            if (!exist) {
                String erro = service.salvar(pj);
                getSession().setAttribute("dados", comprovante);

                if (erro == null) {
                    gravarHistorico();
                    MensagemUtil.addMensagemInfo("Cadastro Realizado!");
                    redirecionaRelatorio();
                } else {
                    MensagemUtil.addMensagemError("Erro ao salvar: " + erro);
                }
            } else {
                Long idPj = (Long) getSession().getAttribute("idPj");
                String erro = service.editar(pj, idPj);
                getSession().setAttribute("dados", comprovante);

                if (erro == null) {
                    gravarHistorico();
                    boolean edit = (boolean) getSession().getAttribute("edit");
                    if (!edit) {
                        redirecionaRelatorio();
                    } else {
                        MensagemUtil.addMensagemInfo("Cadastro alterado!");
                    }
                } else {
                    MensagemUtil.addMensagemError("Erro ao alterar: " + erro);
                }
            }
        } else {
            feira = new Feira();
            MensagemUtil.addMensagemError("Inscrições fechadas!");
        }
    }
        
    public void editar(String cnpj){
        cnpj = cnpj.replaceAll("[- /._]", "");
        if(cnpj.trim().equals("")){
            pj = new PessoaJuridica();
        }else{
            pj = service.obterPorCnpj(cnpj);
            contato = pj.getContato();
            comprovante = pj.getComprovante();
            getSession().setAttribute("idPj", pj.getIdPessoa());
            getSession().setAttribute("edit", true);
        }
    }

    public void editarPj_comprovante(Comprovante comp) throws IOException{
        int id = Integer.parseInt(comp.getIdComprovante().toString());
        pj = service.obterId(id);
        contato = pj.getContato();
        comprovante = pj.getComprovante();
        getSession().setAttribute("idPj", pj.getIdPessoa());
        getSession().setAttribute("edit", true);
    }
    
     public void gravarHistorico(){
        //hist.setIdPessoa(pj.getIdPessoa());
        hist.setTipo("PJ");
        hist.setNome(pj.getNome());
        hist.setRegistro(pj.getCnpj());
        hist.setEmail(contato.getEmail());
        hist.setTelefone1(contato.getTelefone1());
        hist.setTelefone2(contato.getTelefone2());
        hist.setDataCadastro(data);
        hist.setPerfil("");
        //hist.setPessoa(pj);
        hist.setFeira(feira);
        serviceHist.salvar(hist);
    }
    
    public PessoaJuridica getPj() {
        return pj;
    }

    public void setPf(PessoaJuridica pj) {
        this.pj = pj;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<PessoaJuridica> getLista() {
        return lista;
    }

    public void setLista(List<PessoaJuridica> lista) {
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

    public Historico getHist() {
        return hist;
    }

    public void setHist(Historico hist) {
        this.hist = hist;
    }
    
    

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
}
