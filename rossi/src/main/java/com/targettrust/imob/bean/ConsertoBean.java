package main.java.com.targettrust.imob.bean;

import java.sql.Date;

public class ConsertoBean {
    
    private int consId = 0;
    private Date consData;
    private Date consDataEntrega;
    private int vendId = 0;
    private int clienteId = 0;
    private boolean consEntregue = false;
    private int produtoId = 0;
    private String consDescricao = "";
    private String consFiltro = "";
    private String prodDescricao = "";
    private String vendNome = "";
    private String clienteNome = "";
    private int consComboBoxEscolhido = 0;

    /**
     * @return the consId
     */
    public int getConsId() {
        return consId;
    }

    /**
     * @param consId the consId to set
     */
    public void setConsId(int consId) {
        this.consId = consId;
    }

    /**
     * @return the consData
     */
    public Date getConsData() {
        return consData;
    }

    /**
     * @param consData the consData to set
     */
    public void setConsData(Date consData) {
        this.consData = consData;
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
     * @return the consDescricao
     */
    public String getConsDescricao() {
        return consDescricao;
    }

    /**
     * @param consDescricao the consDescricao to set
     */
    public void setConsDescricao(String consDescricao) {
        this.consDescricao = consDescricao;
    }

    /**
     * @return the consFiltro
     */
    public String getConsFiltro() {
        return consFiltro;
    }

    /**
     * @param consFiltro the consFiltro to set
     */
    public void setConsFiltro(String consFiltro) {
        this.consFiltro = consFiltro;
    }

    /**
     * @return the consComboBoxEscolhido
     */
    public int getConsComboBoxEscolhido() {
        return consComboBoxEscolhido;
    }

    /**
     * @param consComboBoxEscolhido the consComboBoxEscolhido to set
     */
    public void setConsComboBoxEscolhido(int consComboBoxEscolhido) {
        this.consComboBoxEscolhido = consComboBoxEscolhido;
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
     * @return the consEntregue
     */
    public boolean isConsEntregue() {
        return consEntregue;
    }

    /**
     * @param consEntregue the consEntregue to set
     */
    public void setConsEntregue(boolean consEntregue) {
        this.consEntregue = consEntregue;
    }

    /**
     * @return the consDataEntrega
     */
    public Date getConsDataEntrega() {
        return consDataEntrega;
    }

    /**
     * @param consDataEntrega the consDataEntrega to set
     */
    public void setConsDataEntrega(Date consDataEntrega) {
        this.consDataEntrega = consDataEntrega;
    }

 
   
}
