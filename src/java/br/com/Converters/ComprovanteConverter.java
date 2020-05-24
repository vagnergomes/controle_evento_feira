/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Converters;

import br.com.feira.Entities.Comprovante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author vagner.gomes
 */
@FacesConverter(value = "comprovanteConverter", forClass = Comprovante.class)
public class ComprovanteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null || value.isEmpty()) {
                return null;
            }

            Long id = Long.valueOf(value);

            Comprovante comprovante = new Comprovante();
            comprovante.setIdComprovante(id);

            return comprovante;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null
                || !(value instanceof Comprovante)) {
            return null;
        }

        return ((Comprovante) value).getIdComprovante().toString();
    }

}
