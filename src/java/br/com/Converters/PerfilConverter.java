/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Converters;

import br.com.feira.Entities.Perfil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author vagner.gomes
 */
@FacesConverter(value = "perfilConverter", forClass = Perfil.class)
public class PerfilConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }

            Long id = Long.valueOf(value);

            Perfil perfil = new Perfil();
            perfil.setIdTipo(id);

            return perfil;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null
                || !(value instanceof Perfil)) {
            return null;
        }

        return ((Perfil) value).getIdPerfil().toString();
    }

//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        try {
//            if (value == null || value.isEmpty()) {
//                return null;
//            }
//
//            Long id = Long.valueOf(value);
//
//            Perfil tipo = new Perfil();
//            tipo.setIdTipo(id);
//
//            return tipo;
//        } catch (NumberFormatException e) {
//            return null;
//        }
//    }
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value == null
//                || !(value instanceof Perfil)) {
//            return null;
//        }
//
//        return ((Perfil) value).getIdTipo().toString();
//    }
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        return value == null ? "" : value.toString();
//    }
}
