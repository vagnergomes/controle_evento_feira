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
import br.com.feira.Entities.PessoaFisica;
import br.com.feira.Entities.Perfil;
import br.com.feira.Services.FeiraService;
import br.com.feira.Services.HistoricoService;
import br.com.feira.Services.PessoaFisicaService;
import br.com.feira.Services.PerfilService;
import br.com.feira.Utils.MensagemUtil;
import br.com.feira.Utils.ValidaCPF;
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
public class PessoaFisicaController implements Serializable {

    @Inject private PessoaFisicaService service;
    @Inject private FeiraService serviceFeira;
    @Inject private HistoricoService serviceHist;
    @Inject private PerfilService servicePerfil;

    private PessoaFisica pf = new PessoaFisica();
    private Contato contato = new Contato();
    private Comprovante comprovante = new Comprovante();
    private Feira feira = new Feira();
    private Historico hist = new Historico();
    private Perfil perfil = new Perfil();
    private List<PessoaFisica> lista;
    
    ValidaCPF validaCpf = new ValidaCPF();

    Date data = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public String dataAtual() {
        return dateFormat.format(data);
    }

    @PostConstruct
    public void init() {
        pf = new PessoaFisica();
        contato = new Contato();
        comprovante = new Comprovante();
        feira = new Feira();
        hist = new Historico();
        perfil = new Perfil();
        lista =  service.todos();
    }

    public void limpar() {
        pf.setCpf("");
        pf.setNome("");
        contato.setEmail("");
        contato.setTelefone1("");
        contato.setTelefone2("");
    }

    public void redirecionaRelatorio() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro-realizado-pf.xhtml");
    }
    
    public void salvar() throws IOException{
        int ano = (data.getYear() + 1900);
        feira = serviceFeira.buscarFeiraAberta(ano);
        perfil = servicePerfil.obter(pf.getPerfil().getIdPerfil());
        String nome = pf.getNome();
        String cpf = pf.getCpf().replaceAll("[- /._]", "");
        pf.setCpf(cpf);
        comprovante.setNome(nome);
        comprovante.setRegistro(cpf);
        comprovante.setTipo("pf");
        comprovante.setPerfil(perfil.getDescricao());
        comprovante.setFeira(feira);
        
        if (feira != null && feira.isInscricaoAberta()) {
            boolean exist = service.buscaCpf(cpf);
            pf.setDataCadastro(data);
            pf.setContato(contato);
            pf.setComprovante(comprovante);
            pf.setFeira(feira);
            
            if (!exist) {
                String erro = service.salvar(pf);
                getSession().setAttribute("dados", comprovante);

                if (erro == null) {
                    gravarHistorico();
                    MensagemUtil.addMensagemInfo("Cadastro Realizado!");
                    redirecionaRelatorio();
                } else {
                    MensagemUtil.addMensagemError("Erro ao salvar: " + erro);
                }
            } else {                
                Long idPf = (Long) getSession().getAttribute("idPf");
                String erro = service.editar(pf, idPf);
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
        }else{
            feira = new Feira();
            MensagemUtil.addMensagemError("Inscrições fechadas!");
        }
    }
    
     public void editar(String cpf){        
        cpf = cpf.replaceAll("[- /._]", "");
        //boolean valido = validaCpf.validaCPF(cpf);
        //System.out.println(valido);
        //fazer condição de cpf inválido pela classe ValidaCPF
        
        if(cpf.trim().equals("")){
            pf = new PessoaFisica();
        }else{
            pf = service.obterPorCpf(cpf);
            contato = pf.getContato();
            comprovante = pf.getComprovante();
            getSession().setAttribute("idPf", pf.getIdPessoa());
            getSession().setAttribute("edit", true);
        }
    }
     
     public void editarPf_comprovante(Comprovante comp) throws IOException{
        int id = Integer.parseInt(comp.getIdComprovante().toString());
        pf = service.obterId(id);
        contato = pf.getContato();
        comprovante = pf.getComprovante();
        perfil = pf.getPerfil();
        getSession().setAttribute("idPf", pf.getIdPessoa());
        getSession().setAttribute("edit", true);
    }
     
     
    public void gravarHistorico(){
        //hist.setIdPessoa(pf.getIdPessoa());
        hist.setTipo(pf.getPerfil().getDescricao());
        hist.setNome(pf.getNome());
        hist.setRegistro(pf.getCpf());
        hist.setEmail(contato.getEmail());
        hist.setTelefone1(contato.getTelefone1());
        hist.setTelefone2(contato.getTelefone2());
        hist.setDataCadastro(data);
        hist.setPerfil(perfil.getDescricao());
        //hist.setPessoa(pf);
        hist.setFeira(feira);
        serviceHist.salvar(hist);
    }

    public PessoaFisica getPf() {
        return pf;
    }

    public void setPf(PessoaFisica pf) {
        this.pf = pf;
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

    public List<PessoaFisica> getLista() {
        return lista;
    }

    public void setLista(List<PessoaFisica> lista) {
        this.lista = lista;
    }

    public Historico getHist() {
        return hist;
    }

    public void setHist(Historico hist) {
        this.hist = hist;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

}
