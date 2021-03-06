package main.java.com.targettrust.imob.bean;


public class ClienteBean {

    private int clienteId = 0;
    private String clienteNome = "";
    private String clienteCPF    = "";
    private String clienteEndereco = "";
    private String clienteTelefone = "";
    private String clienteCelular = "";
    private String clienteEmail = "";
    private String clienteFiltro = "";
    private int clienteComboBoxEscolhido = 0;
    
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
     * @return the clienteCPF
     */
    public String getClienteCPF() {
        return clienteCPF;
    }

    /**
     * @param clienteCPF the clienteCPF to set
     */
    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    /**
     * @return the clienteEndereco
     */
    public String getClienteEndereco() {
        return clienteEndereco;
    }

    /**
     * @param clienteEndereco the clienteEndereco to set
     */
    public void setClienteEndereco(String clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    /**
     * @return the clienteTelefone
     */
    public String getClienteTelefone() {
        return clienteTelefone;
    }

    /**
     * @param clienteTelefone the clienteTelefone to set
     */
    public void setClienteTelefone(String clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }

    /**
     * @return the clienteCelular
     */
    public String getClienteCelular() {
        return clienteCelular;
    }

    /**
     * @param clienteCelular the clienteCelular to set
     */
    public void setClienteCelular(String clienteCelular) {
        this.clienteCelular = clienteCelular;
    }

    /**
     * @return the clienteEmail
     */
    public String getClienteEmail() {
        return clienteEmail;
    }

    /**
     * @param clienteEmail the clienteEmail to set
     */
    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    /**
     * @return the prodFiltro
     */
    public String getClienteFiltro() {
        return clienteFiltro;
    }

    /**
     * @param prodFiltro the prodFiltro to set
     */
    public void setClienteFiltro(String clienteFiltro) {
        this.clienteFiltro = clienteFiltro;
    }

    public int getClienteComboBoxEscolhido() {
        return clienteComboBoxEscolhido;
    }

    public void setClienteComboBoxEscolhido(int clienteComboBoxEscolhido) {
        this.clienteComboBoxEscolhido = clienteComboBoxEscolhido;
    }
}
