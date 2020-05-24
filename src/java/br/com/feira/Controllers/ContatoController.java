/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Controllers;

import br.com.feira.Entities.Contato;
import br.com.feira.Relatorios.RelatorioContato;
import br.com.feira.Services.ContatoService;
import br.com.feira.webservices.ContatoClient;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;


/**
 *
 * @author vagner.gomes
 */
@Named
@RequestScoped
public class ContatoController implements Serializable{
    
    @Inject
    private ContatoService service;
    
    private Contato contato;
    private List<Contato> lista = new ArrayList<>();
    
    //Dependencias REST
    private WebTarget webTarget;
    private WebTarget webTarget2;
    private Client client;
    private Client client2;
    private static final String BASE_URI = "http://localhost:8080/br.com.feira/rest";
    
    @PostConstruct
    public void init(){
        contato = new Contato();
        lista = service.todos();
        
        //Inicializando variavel para REST
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("contato");
        
        client2 = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget2 = client2.target(BASE_URI).path("comprovante");
    }
    
    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }
    
    public String nome(){
        WebTarget resource = webTarget2;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }
   

    public void gerarRelatorioAction() throws IOException{
        RelatorioContato relatorio = new RelatorioContato();
        relatorio.getRelatorio(lista);
    }
    

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getLista() {
        return lista;
    }

    public void setLista(List<Contato> lista) {
        this.lista = lista;
    }   
    
}
