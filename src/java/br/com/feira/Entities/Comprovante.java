/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner.gomes
 */
@Entity
@XmlRootElement
public class Comprovante implements Serializable {
          
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprovante;
    
    private String nome;
    
    private String registro;
    
    private String tipo;
    
    private String perfil;

    
      //Mapeamentos
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Feira feira;
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdComprovante() {
        return idComprovante;
    }

    public void setIdComprovante(Long idComprovante) {
        this.idComprovante = idComprovante;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }
    
    
    
}
