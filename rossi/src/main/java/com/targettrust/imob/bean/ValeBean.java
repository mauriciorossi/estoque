package com.targettrust.imob.bean;

import java.util.Date;
public class ValeBean {
    private int valeId = 0;
    private String valeNomeCliente = "";
    private int vendId = 0;
    private int clienteId = 0;
    private double valeValor = 0;
    private Date valeData;
    private Date valeDataUtilizacao;
    private String valeFiltro = "";
    private int valeComboBoxEscolhido = 0;

    /**
     * @return the valeId
     */
    public int getValeId() {
        return valeId;
    }

    /**
     * @param valeId the valeId to set
     */
    public void setValeId(int valeId) {
        this.valeId = valeId;
    }

    /**
     * @return the vendId
     */
    public int getVendId() {
        return vendId;
    }

    /**
     * @param vendId the vendId to set
     */
    public void setVendId(int vendId) {
        this.vendId = vendId;
    }

    /**
     * @return the valeValor
     */
    public double getValeValor() {
        return valeValor;
    }

    /**
     * @param valeValor the valeValor to set
     */
    public void setValeValor(double valeValor) {
        this.valeValor = valeValor;
    }

    /**
     * @return the valeData
     */
    public Date getValeData() {
        return valeData;
    }

    /**
     * @param valeData the valeData to set
     */
    public void setValeData(Date valeData) {
        this.valeData = valeData;
    }

    /**
     * @return the valeDataUtilizacao
     */
    public Date getValeDataUtilizacao() {
        return valeDataUtilizacao;
    }

    /**
     * @param valeDataUtilizacao the valeDataUtilizacao to set
     */
    public void setValeDataUtilizacao(Date valeDataUtilizacao) {
        this.valeDataUtilizacao = valeDataUtilizacao;
    }

    /**
     * @return the valeFiltro
     */
    public String getValeFiltro() {
        return valeFiltro;
    }

    /**
     * @param valeFiltro the valeFiltro to set
     */
    public void setValeFiltro(String valeFiltro) {
        this.valeFiltro = valeFiltro;
    }

    /**
     * @return the valeComboBoxEscolhido
     */
    public int getValeComboBoxEscolhido() {
        return valeComboBoxEscolhido;
    }

    /**
     * @param valeComboBoxEscolhido the valeComboBoxEscolhido to set
     */
    public void setValeComboBoxEscolhido(int valeComboBoxEscolhido) {
        this.valeComboBoxEscolhido = valeComboBoxEscolhido;
    }

    /**
     * @return the valeNomeCliente
     */
    public String getValeNomeCliente() {
        return valeNomeCliente;
    }

    /**
     * @param valeNomeCliente the valeNomeCliente to set
     */
    public void setValeNomeCliente(String valeNomeCliente) {
        this.valeNomeCliente = valeNomeCliente;
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

}
