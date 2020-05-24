/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author vagner.gomes
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", length = 14, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public class Pessoa implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPessoa;
    
    private String nome;  
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    
    //Mapeamentos
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Feira feira;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contato contato;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Comprovante comprovante;
    
//    @OneToMany(mappedBy = "Pessoa")
//    private List<Historico> historicos;
    
    
    public Pessoa(){
        
    }
   
    public void setIdPessoa(Long idPessoa){
        this.idPessoa = idPessoa;
    }
    public Long getIdPessoa(){
        return idPessoa;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
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

//    public List<Historico> getHistoricos() {
//        return historicos;
//    }
//
//    public void setHistoricos(List<Historico> historicos) {
//        this.historicos = historicos;
//    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = (int) (97 * hash + this.idPessoa);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        return true;
    }


    

}
