/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Temporal;

/**
 *
 * @author Vagner
 */
@ManagedBean
@SessionScoped
public class Sessao implements Serializable {

    private long IdSessaoL;
    private long IdSessaoL2;
    private short IdSessaoS;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Data;
    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date Horario;
    
    private String usuario;

    public long getIdSessaoL() {
        return IdSessaoL;
    }

    public void setIdSessaoL(long IdSessaoL) {
        this.IdSessaoL = IdSessaoL;
    }

    public long getIdSessaoL2() {
        return IdSessaoL2;
    }

    public void setIdSessaoL2(long IdSessaoL2) {
        this.IdSessaoL2 = IdSessaoL2;
    }

    public short getIdSessaoS() {
        return IdSessaoS;
    }

    public void setIdSessaoS(short IdSessaoS) {
        this.IdSessaoS = IdSessaoS;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public Date getHorario() {
        return Horario;
    }

    public void setHorario(Date Horario) {
        this.Horario = Horario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
