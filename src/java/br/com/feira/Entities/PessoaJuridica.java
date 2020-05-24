/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author vagner.gomes
 */
@Entity
@PrimaryKeyJoinColumn(name="IdPessoa")
@DiscriminatorValue("PessoaJuridica")
public class PessoaJuridica extends Pessoa implements Serializable{
     
    @Column(nullable=false, length = 14)
    private String cnpj;
    
    public PessoaJuridica(){
        
    }
    
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }
    
    public String getCnpj(){
        return cnpj;
    }

}
