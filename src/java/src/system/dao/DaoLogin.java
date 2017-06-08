package src.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import src.system.utilidades.ErroSql;
import src.modelo.Usuario;

public class DaoLogin {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;
    private ErroSql erro = new ErroSql();
    private final String classe;

    public DaoLogin() {
        this.classe = "DaoLogin";
        connectDataBase = new ConnectDataBase();
    }

    public Usuario loginUsuario(Usuario usuario) {
        String selectTableSQL = "SELECT "
                + "a.nip, a.ativo, "
                + "c.valor, c.descricao, a.nome, "
                + "a.guerra, a.setor, a.ramal, a.admin, "
                + "a.home, a.acesso, b.data, "
                + "a.tipoacesso, a.titulo "
                + " FROM usuario a, usuario_senha b, aux_postograduacao c WHERE "
                + "a.nip='" + usuario.getNip() + "' AND "
                + "a.ativo='1' AND "
                + "a.nip = b.nip AND "
                + "b.senha='" + usuario.getSenha() + "' AND "
                + "a.graduacao = c.valor AND "
                + "b.statussenha='1';";
        Usuario usuarioRetorno = null;
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(usuario.getNip());
                usuarioRetorno.setSenha(usuario.getSenha());
                usuarioRetorno.setAdmin(rs.getInt("admin"));
                usuarioRetorno.setNome(rs.getString("nome"));
                usuarioRetorno.setGraduacao(rs.getInt("valor"));
                usuarioRetorno.setGraduacaoNome(rs.getString("descricao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setHome(rs.getString("home"));
                usuarioRetorno.setTitulo(rs.getString("titulo"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setTipoAcesso(rs.getInt("tipoacesso"));
                usuarioRetorno.setAcesso(rs.getInt("acesso"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
                usuarioRetorno.setDataSenha(rs.getDate("data"));
            }
            return usuarioRetorno;
        } catch (SQLException e) {
            erro.Gravar(classe, "loginUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public Boolean insereSenha(Usuario usuario) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario_senha("
                + "nip, statussenha, senha, data, hora, ip)"
                + "VALUES ('" + usuario.getNip() + "', '1', '" + usuario.getNip() + "', "
                + "'" + date + "', '" + thisSec + "', '" + usuario.getIp_access() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            statement.close();
            connectDataBase.closeConnection();
            return true;
        } catch (SQLException e) {
            erro.Gravar(classe, "insereSenha", insereTableSQL, e.getMessage());
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public Boolean lastAccess(Usuario usuario) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario_logon("
                + "nip, id_session, data, hora, ip) "
                + "VALUES ('"+usuario.getNip()+"', '"+usuario.getId_session()+"', "
                + "'"+date+"', '"+thisSec+"', '"+usuario.getIp_access()+"');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            statement.close();
            connectDataBase.closeConnection();
            return true;
        } catch (SQLException e) {
            erro.Gravar(classe, "lastAccess", insereTableSQL, e.getMessage());
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean zeraSenha(Usuario usuario) {
        String updateTableSQL = "UPDATE usuario_senha "
                + "SET statussenha='0'"
                + " WHERE nip = '" + usuario.getNip() + "';";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(updateTableSQL);
            statement.close();
            connectDataBase.closeConnection();
            return true;
        } catch (SQLException e) {
            erro.Gravar(classe, "zeraSenha", updateTableSQL, e.getMessage());
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void auditoria(Usuario usuario, String msg) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario_auditoria("
                + "Nip, descricao, data, hora, ip) "
                + "VALUES ('" + usuario.getNip() + "', '" + msg + "', "
                + "'" + date + "', '" + thisSec + "', '" + usuario.getIp_access() + "');";
        try {
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            statement.close();
            connectDataBase.closeConnection();
        } catch (SQLException e) {
            erro.Gravar(classe, "auditoria", insereTableSQL, e.getMessage());
        } finally {
            connectDataBase.closeConnection();
        }
    }

}
