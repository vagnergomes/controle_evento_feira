/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author vagner.gomes
 */

@Entity
public class Perfil implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPerfil;
    
    @Column(nullable=false)
    private String descricao;

    
    //Mapeamentos
    @OneToMany(mappedBy = "Perfil")
    private List<PessoaFisica> pfs;
    
    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdTipo(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PessoaFisica> getPfs() {
        return pfs;
    }

    public void setPfs(List<PessoaFisica> pfs) {
        this.pfs = pfs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idPerfil);
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
        final Perfil other = (Perfil) obj;
        if (!Objects.equals(this.idPerfil, other.idPerfil)) {
            return false;
        }
        return true;
    }

    
}
