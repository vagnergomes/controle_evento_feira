/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Usuario;
import br.com.feira.Services.UsuarioService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Vagner
 */
@Named
@SessionScoped
public class LoginController extends SessaoHelper implements Serializable {
    
    @Inject
    private UsuarioService service;
  
    private Usuario usuario; 
    
   
    @PostConstruct
    public void init() {
        usuario = new Usuario();
      
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                usuario = service.consultaPorUsuario(((User) authentication.getPrincipal()).getUsername());
            }
        }
        
    }

    public void reset() {
        init();
    }

    public String nomeDoUsuario() {
        return usuario.getUsuario();
    }

    public boolean validaSeAdmin() {
        return usuario.getRole().equals("Administrador"); 
    }

    public boolean validaSeCliente() {
        return usuario.getRole().equals("Cliente");
    }

    //GET SET
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
