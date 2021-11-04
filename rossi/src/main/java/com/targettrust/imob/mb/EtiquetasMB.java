package com.targettrust.imob.mb;

import com.targettrust.imob.bean.EtiquetaBean;
import com.targettrust.imob.rn.EtiquetasBD;
import com.targettrust.imob.util.Mensagem;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EtiquetasMB {

    private EtiquetaBean etiqueta;

    public EtiquetasMB() {
        etiqueta = new EtiquetaBean();
    }

    public String geraEtiquetas() throws Exception {
        try {
            EtiquetasBD etiqueta = new EtiquetasBD();
            etiqueta.geraEtiquetas();
            return "gotoDownload";
        } catch (ClassNotFoundException e) {
            Mensagem.error(e.getMessage());
        } catch (IllegalAccessException e) {
            Mensagem.error(e.getMessage());
        } catch (InstantiationException e) {
            Mensagem.error(e.getMessage());
        } catch (SQLException e) {
            Mensagem.error(e.getMessage());
        }
        return null;
    }

    public boolean excluiEtiquetas() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("excluindo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        EtiquetasBD etiquetas = new EtiquetasBD();
        boolean excluiu = false;
        excluiu = etiquetas.excluiEtiquetas();
        if(excluiu){
            Mensagem.add("Etiquetas excluidas com sucesso!");
            return true;
        }else{
            return false;
         }
      }

    /**
     * @return the etiqueta
     */
    public EtiquetaBean getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(EtiquetaBean etiqueta) {
        this.etiqueta = etiqueta;
    }

}
