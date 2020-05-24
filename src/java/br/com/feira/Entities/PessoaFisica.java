/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author vagner.gomes
 */
@Entity
@PrimaryKeyJoinColumn(name="IdPessoa")
@DiscriminatorValue("PessoaFisica")
public class PessoaFisica extends Pessoa{
     
    @Column(nullable=false, length = 11)
    private String cpf;
    
    
    /**
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
   **/
    
    public PessoaFisica(){
        
    }
    
    //Mapeamentos
    @ManyToOne
    private Perfil perfil;

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public String getCpf(){
        return cpf;
    } 

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
}
