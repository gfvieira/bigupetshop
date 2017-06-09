package src.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import src.modelo.Usuario;
import src.system.utilidades.ErroSql;

public class DaoLogin {

    private Statement statement;
    private static final String nomeClasse = "DaoAdmin";

    private Statement getStatement() {
        try {
            statement = ConnectDataBase.openConection().createStatement();
        } catch (SQLException ex) {
        }
        return statement;
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
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
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
            ErroSql.Gravar(nomeClasse, "loginUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
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
            getStatement().executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "insereSenha", insereTableSQL, e.getMessage());
            return false;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public Boolean lastAccess(Usuario usuario) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario_logon("
                + "nip, id_session, data, hora, ip) "
                + "VALUES ('" + usuario.getNip() + "', '" + usuario.getId_session() + "', "
                + "'" + date + "', '" + thisSec + "', '" + usuario.getIp_access() + "');";
        try {
            getStatement().executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "lastAccess", insereTableSQL, e.getMessage());
            return false;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public boolean zeraSenha(Usuario usuario) {
        String updateTableSQL = "UPDATE usuario_senha "
                + "SET statussenha='0'"
                + " WHERE nip = '" + usuario.getNip() + "';";
        try {
            getStatement().executeUpdate(updateTableSQL);
            return true;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "zeraSenha", updateTableSQL, e.getMessage());
            return false;
        } finally {
            ConnectDataBase.closeConnection();
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
            getStatement().executeUpdate(insereTableSQL);
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "auditoria", insereTableSQL, e.getMessage());
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

}
