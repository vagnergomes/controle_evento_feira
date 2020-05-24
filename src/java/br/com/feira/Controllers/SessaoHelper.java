/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vagner
 */
public class SessaoHelper implements Serializable {

    @ManagedProperty(value = "#{sessao}")
    protected Sessao sessao;

    public void redirecionar(String s) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(s);
        } catch (IOException ex) {
            Logger.getLogger(SessaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}
