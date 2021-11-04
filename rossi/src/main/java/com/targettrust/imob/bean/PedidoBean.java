
package main.java.com.targettrust.imob.bean;

import java.sql.Date;

public class PedidoBean {
    private int pedID = 0;
    private int clienteId = 0;
    private int produtoId = 0;
    private Date pedData;
    private String pedFiltro = "";
    private boolean pedInformado = false;
    private int pedComboBoxEscolhido = 0;
    private int vendID = 0;
    private String pedDescricao = "";
    private String vendNome = "";
    private String clienteNome = "";
    private String prodDescricao = "";

    /**
     * @return the pedID
     */
    public int getPedID() {
        return pedID;
    }

    /**
     * @param pedID the pedID to set
     */
    public void setPedID(int pedID) {
        this.pedID = pedID;
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
     * @return the pedData
     */
    public Date getPedData() {
        return pedData;
    }

    /**
     * @param pedData the pedData to set
     */
    public void setPedData(Date pedData) {
        this.pedData = pedData;
    }

    /**
     * @return the pedFiltro
     */
    public String getPedFiltro() {
        return pedFiltro;
    }

    /**
     * @param pedFiltro the pedFiltro to set
     */
    public void setPedFiltro(String pedFiltro) {
        this.pedFiltro = pedFiltro;
    }

    /**
     * @return the pedComboBoxEscolhido
     */
    public int getPedComboBoxEscolhido() {
        return pedComboBoxEscolhido;
    }

    /**
     * @param pedComboBoxEscolhido the pedComboBoxEscolhido to set
     */
    public void setPedComboBoxEscolhido(int pedComboBoxEscolhido) {
        this.pedComboBoxEscolhido = pedComboBoxEscolhido;
    }

    /**
     * @return the pedInformado
     */
    public boolean isPedInformado() {
        return pedInformado;
    }

    /**
     * @param pedInformado the pedInformado to set
     */
    public void setPedInformado(boolean pedInformado) {
        this.pedInformado = pedInformado;
    }

    /**
     * @return the vendID
     */
    public int getVendID() {
        return vendID;
    }

    /**
     * @param vendID the vendID to set
     */
    public void setVendID(int vendID) {
        this.vendID = vendID;
    }

    /**
     * @return the pedDescricao
     */
    public String getPedDescricao() {
        return pedDescricao;
    }

    /**
     * @param pedDescricao the pedDescricao to set
     */
    public void setPedDescricao(String pedDescricao) {
        this.pedDescricao = pedDescricao;
    }

    /**
     * @return the vendNome
     */
    public String getVendNome() {
        return vendNome;
    }

    /**
     * @param vendNome the vendNome to set
     */
    public void setVendNome(String vendNome) {
        this.vendNome = vendNome;
    }

    /**
     * @return the clienteNome
     */
    public String getClienteNome() {
        return clienteNome;
    }

    /**
     * @param clienteNome the clienteNome to set
     */
    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    /**
     * @return the prodDescricao
     */
    public String getProdDescricao() {
        return prodDescricao;
    }

    /**
     * @param prodDescricao the prodDescricao to set
     */
    public void setProdDescricao(String prodDescricao) {
        this.prodDescricao = prodDescricao;
    }
   
}
