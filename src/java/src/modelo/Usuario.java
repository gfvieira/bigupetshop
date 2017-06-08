package src.modelo;

import java.util.Date;

public class Usuario {

    /**
     * @return the home
     */
    public String getHome() {
        return home;
    }

    /**
     * @param home the home to set
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String nip;
    private int admin;
    private int ativo;
    private String senha;
    private String nome;
    private int graduacao;
    private String graduacaoNome;
    private String guerra;
    private String setor;
    private String home;
    private String titulo;
    private String ramal;
    private int tipoAcesso;
    private String tipoAcessoNome;
    private int acesso;
    private String acessoNome;
    private String lastAccess;
    private String ip_access;
    private String id_session;
    private Date dataSenha;

    /**
     * @return the dataSenha
     */
    public Date getDataSenha() {
        return dataSenha;
    }

    /**
     * @param dataSenha the dataSenha to set
     */
    public void setDataSenha(Date dataSenha) {
        this.dataSenha = dataSenha;
    }

    /**
     * @return the acessoNome
     */
    public String getAcessoNome() {
        return acessoNome;
    }

    /**
     * @param acessoNome the acessoNome to set
     */
    public void setAcessoNome(String acessoNome) {
        this.acessoNome = acessoNome;
    }

    /**
     * @return the tipoAcessoNome
     */
    public String getTipoAcessoNome() {
        return tipoAcessoNome;
    }

    /**
     * @param tipoAcessoNome the tipoAcessoNome to set
     */
    public void setTipoAcessoNome(String tipoAcessoNome) {
        this.tipoAcessoNome = tipoAcessoNome;
    }

    /**
     * @return the admin
     */
    public int getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
     /**
     * @return the nip
     */
    public String getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the graduacao
     */
    public int getGraduacao() {
        return graduacao;
    }

    /**
     * @param graduacao the graduacao to set
     */
    public void setGraduacao(int graduacao) {
        this.graduacao = graduacao;
    }

    /**
     * @return the graduacaoNome
     */
    public String getGraduacaoNome() {
        return graduacaoNome;
    }

    /**
     * @param graduacaoNome the graduacaoNome to set
     */
    public void setGraduacaoNome(String graduacaoNome) {
        this.graduacaoNome = graduacaoNome;
    }

    /**
     * @return the guerra
     */
    public String getGuerra() {
        return guerra;
    }

    /**
     * @param guerra the guerra to set
     */
    public void setGuerra(String guerra) {
        this.guerra = guerra;
    }

    /**
     * @return the setor
     */
    public String getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    /**
     * @return the ramal
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * @param ramal the ramal to set
     */
    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    /**
     * @return the tipoAcesso
     */
    public int getTipoAcesso() {
        return tipoAcesso;
    }

    /**
     * @param tipoAcesso the tipoAcesso to set
     */
    public void setTipoAcesso(int tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    /**
     * @return the acesso
     */
    public int getAcesso() {
        return acesso;
    }

    /**
     * @param acesso the acesso to set
     */
    public void setAcesso(int acesso) {
        this.acesso = acesso;
    }

    /**
     * @return the lastAccess
     */
    public String getLastAccess() {
        return lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the ativo
     */
    public int getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the ip_access
     */
    public String getIp_access() {
        return ip_access;
    }

    /**
     * @param ip_access the ip_access to set
     */
    public void setIp_access(String ip_access) {
        this.ip_access = ip_access;
    }

    /**
     * @return the id_session
     */
    public String getId_session() {
        return id_session;
    }

    /**
     * @param id_session the id_session to set
     */
    public void setId_session(String id_session) {
        this.id_session = id_session;
    }

}
