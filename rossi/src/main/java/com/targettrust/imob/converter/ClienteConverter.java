package com.targettrust.imob.converter;

import com.targettrust.imob.bean.ClienteBean;
import com.targettrust.imob.rn.ClienteBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.print.DocFlavor;

@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        ClienteBD clienteBD = new ClienteBD();
        ClienteBean cliente = new ClienteBean();
        try {
            cliente = clienteBD.buscaPorId(Integer.parseInt(string));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ClienteConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClienteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        ClienteBean cliente = new ClienteBean();
        cliente = (ClienteBean) o;
        return String.valueOf(cliente.getClienteId());
    }

}
