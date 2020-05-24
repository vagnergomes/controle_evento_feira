/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Pessoa;
import br.com.feira.Services.PessoaService;
import br.com.feira.Utils.MensagemUtil;
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
public class PessoaController {
   
    @Inject
    private PessoaService service;
    
    private Pessoa pessoa;
    
    @PostConstruct
    public void init(){
      pessoa = new Pessoa();  
    }
    
    public void salvar(){
        String erro = service.salvar(pessoa);
        
        if(erro == null){
            MensagemUtil.addMensagemInfo("Salvo com sucesso");
        }else{
            System.out.println("ERRO: " +erro);
            MensagemUtil.addMensagemError("Erro ao salvar: " +erro);
        }
        
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    
}
