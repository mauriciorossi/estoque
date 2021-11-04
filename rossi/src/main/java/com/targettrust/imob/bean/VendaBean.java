
package com.targettrust.imob.bean;

import java.sql.Date;

public class VendaBean {
    
    private int vendaId = 0;
    private int clienteId = 0;
    private int produtoId = 0;
    private int vendedoraId = 0;
    private Date vendaData;
    private String vendaFormaPgto = "";
    private double vendaValorTotal =  0;
    private String vendaFiltro = "";
    private int vendaComboBoxEscolhido = 0;

    /**
     * @return the vendaId
     */
    public int getVendaId() {
        return vendaId;
    }

    /**
     * @param vendaId the vendaId to set
     */
    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    /**
     * @return the clienteId
     */
    public int getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the produtoId
     */
    public int getProdutoId() {
        return produtoId;
    }

    /**
     * @param produtoId the produtoId to set
     */
    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    /**
     * @return the vendaData
     */
    public Date getVendaData() {
        return vendaData;
    }

    /**
     * @param vendaData the vendaData to set
     */
    public void setVendaData(Date vendaData) {
        this.vendaData = vendaData;
    }

    /**
     * @return the vendaFormaPgto
     */
    public String getVendaFormaPgto() {
        return vendaFormaPgto;
    }

    /**
     * @param vendaFormaPgto the vendaFormaPgto to set
     */
    public void setVendaFormaPgto(String vendaFormaPgto) {
        this.vendaFormaPgto = vendaFormaPgto;
    }

    /**
     * @return the vendaValorTotal
     */
    public double getVendaValorTotal() {
        return vendaValorTotal;
    }

    /**
     * @param vendaValorTotal the vendaValorTotal to set
     */
    public void setVendaValorTotal(double vendaValorTotal) {
        this.vendaValorTotal = vendaValorTotal;
    }

    /**
     * @return the vendaFiltro
     */
    public String getVendaFiltro() {
        return vendaFiltro;
    }

    /**
     * @param vendaFiltro the vendaFiltro to set
     */
    public void setVendaFiltro(String vendaFiltro) {
        this.vendaFiltro = vendaFiltro;
    }

    /**
     * @return the vendaComboBoxEscolhido
     */
    public int getVendaComboBoxEscolhido() {
        return vendaComboBoxEscolhido;
    }

    /**
     * @param vendaComboBoxEscolhido the vendaComboBoxEscolhido to set
     */
    public void setVendaComboBoxEscolhido(int vendaComboBoxEscolhido) {
        this.vendaComboBoxEscolhido = vendaComboBoxEscolhido;
    }

    /**
     * @return the vendedoraId
     */
    public int getVendedoraId() {
        return vendedoraId;
    }

    /**
     * @param vendedoraId the vendedoraId to set
     */
    public void setVendedoraId(int vendedoraId) {
        this.vendedoraId = vendedoraId;
    }

  
}
