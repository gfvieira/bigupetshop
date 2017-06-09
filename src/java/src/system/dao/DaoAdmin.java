package src.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.system.utilidades.ErroSql;
import src.modelo.Usuario;

public class DaoAdmin {

    private Statement statement;
    private static final String nomeClasse = "DaoAdmin";

    private Statement getStatement() {
        try {
            statement = ConnectDataBase.openConection().createStatement();
        } catch (SQLException ex) {
        }
        return statement;
    }

    public Usuario buscaUsuario(Usuario usuario) {
        String selectTableSQL = "SELECT a.nip, a.ativo, c.valor, c.descricao AS graduacao, "
                + "a.nome, a.guerra, a.setor, a.ramal, a.tipoacesso, a.admin, "
                + "d.descricao AS tipoacessonome, a.acesso, b.descricao AS acessonome "
                + "FROM usuario a, aux_postograduacao c, aux_acesso b, aux_acesso_tipo d "
                + "WHERE a.nip='" + usuario.getNip() + "' AND "
                + "a.graduacao = c.valor AND "
                + "a.acesso = b.valor AND "
                + "b.tipo = d.valor;";
        Usuario usuarioRetorno = null;
        try {
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(usuario.getNip());
                usuarioRetorno.setNome(rs.getString("nome"));
                usuarioRetorno.setAdmin(rs.getInt("admin"));
                usuarioRetorno.setGraduacao(rs.getInt("valor"));
                usuarioRetorno.setGraduacaoNome(rs.getString("graduacao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setTipoAcesso(rs.getInt("tipoacesso"));
                usuarioRetorno.setAcesso(rs.getInt("acesso"));
                usuarioRetorno.setTipoAcessoNome(rs.getString("tipoacessonome"));
                usuarioRetorno.setAcessoNome(rs.getString("acessonome"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
            }
            return usuarioRetorno;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "buscaUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public boolean insereUsuario(Usuario usuario) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario( "
                + "nip, admin, ativo, nome, graduacao, guerra, setor, home, titulo, "
                + "ramal, tipoacesso, acesso, data, hora, ip) "
                + "VALUES ('" + usuario.getNip() + "', '" + usuario.getAdmin() + "', '1', "
                + "'" + usuario.getNome() + "', '" + usuario.getGraduacao() + "', "
                + "'" + usuario.getGuerra() + "', '" + usuario.getSetor() + "', "
                + "'" + usuario.getHome() + "', '" + usuario.getTitulo() + "', "
                + "'" + usuario.getRamal() + "', "
                + "'" + usuario.getTipoAcesso() + "', '" + usuario.getAcesso() + "', "
                + "'" + date + "', '" + thisSec + "', '" + usuario.getIp_access() + "');";
        try {
            getStatement().executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "insereUsuario", insereTableSQL, e.getMessage());
            return false;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public ArrayList<Usuario> listaUsuario() {
        String selectTableSQL = "SELECT a.ativo, a.nip, c.descricao AS graduacao, a.guerra, a.setor, a.ramal, b.descricao, a.acesso"
                + " FROM usuario a, aux_acesso b, aux_postograduacao c "
                + "WHERE a.graduacao = c.valor AND "
                + "a.acesso = b.valor;";
        Usuario usuarioRetorno;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(rs.getString("nip"));
                usuarioRetorno.setGraduacaoNome(rs.getString("graduacao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setAcessoNome(rs.getString("descricao"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
                lista.add(usuarioRetorno);
            }
            return lista;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "listaUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public boolean atualizaUsuario(Usuario usuario) {
        String insereTableSQL = "UPDATE usuario SET "
                + "admin='" + usuario.getAdmin() + "', "
                + "ativo='" + usuario.getAtivo() + "', "
                + "nip='" + usuario.getNip() + "', "
                + "nome='" + usuario.getNome() + "', "
                + "graduacao='" + usuario.getGraduacao() + "', "
                + "guerra='" + usuario.getGuerra() + "', "
                + "home='" + usuario.getHome() + "', "
                + "titulo='" + usuario.getTitulo() + "', "
                + "ramal='" + usuario.getRamal() + "', "
                + "tipoacesso='" + usuario.getTipoAcesso() + "', "
                + "acesso='" + usuario.getAcesso() + "' "
                + "WHERE nip='" + usuario.getNip() + "';";
        try {
            getStatement().executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "atualizaUsuario", insereTableSQL, e.getMessage());
            return false;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public boolean auditoria(String nip, String descricao, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario_auditoria("
                + "nip, descricao, data, hora, ip) "
                + "VALUES ('" + nip + "', '" + descricao + "','" + date + "', '" + thisSec + "', '" + ip + "');";
        try {
            getStatement().executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "auditoria", insereTableSQL, e.getMessage());
            return false;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public boolean insereSenha(String nip, String senha, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO usuario_senha("
                + "nip, statussenha, senha, data, hora, ip) "
                + "VALUES ('" + nip + "', '1', '" + senha + "', '" + date + "', '" + thisSec + "', '" + ip + "');";
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

    public ArrayList<Usuario> listaUsuarioDesativado() {
        String selectTableSQL = "SELECT a.ativo, a.nip, c.descricao AS graduacao, a.guerra, a.setor, a.ramal, b.descricao, a.acesso"
                + " FROM usuario a, aux_acesso b, aux_postograduacao c "
                + "WHERE a.ativo = '0' AND "
                + "a.graduacao = c.valor AND "
                + "a.acesso = b.valor;";
        Usuario usuarioRetorno;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(rs.getString("nip"));
                usuarioRetorno.setGraduacaoNome(rs.getString("graduacao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setAcessoNome(rs.getString("descricao"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
                lista.add(usuarioRetorno);
            }
            return lista;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "listaUsuarioDesativado", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public ArrayList<Usuario> listaUsuario(int tipo) {
        String selectTableSQL = "SELECT "
                + "a.ativo, "
                + "a.nip, "
                + "c.descricao AS graduacao, "
                + "a.guerra, "
                + "a.setor, "
                + "a.ramal, "
                + "b.descricao, "
                + "a.acesso"
                + " FROM usuario a, aux_acesso b, aux_postograduacao c "
                + "WHERE a.tipoacesso = '" + tipo + "' AND "
                + "a.graduacao = c.valor AND "
                + "a.acesso = b.valor;";
        Usuario usuarioRetorno;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(rs.getString("nip"));
                usuarioRetorno.setGraduacaoNome(rs.getString("graduacao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setAcessoNome(rs.getString("descricao"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
                lista.add(usuarioRetorno);
            }
            return lista;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "listaUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public ArrayList<Usuario> listaUsuarioDoDIa() {
        Date date = new Date();
        String selectTableSQL = "SELECT *"
                + " FROM usuario_logon "
                + "WHERE data = '" + date + "';";
        Usuario usuarioRetorno;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(rs.getString("nip"));
                usuarioRetorno.setGraduacaoNome(rs.getString("graduacao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setAcessoNome(rs.getString("descricao"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
                lista.add(usuarioRetorno);
            }
            return lista;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "listaUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }

    public ArrayList<Usuario> listaUsuarioPeriodo() {
        Date date = new Date();
        String selectTableSQL = "SELECT * "
                + "FROM usuario_logon "
                + "WHERE data = '" + date + "';";
        Usuario usuarioRetorno = null;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            ResultSet rs = getStatement().executeQuery(selectTableSQL);
            while (rs.next()) {
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNip(rs.getString("nip"));
                usuarioRetorno.setGraduacaoNome(rs.getString("graduacao"));
                usuarioRetorno.setGuerra(rs.getString("guerra"));
                usuarioRetorno.setSetor(rs.getString("setor"));
                usuarioRetorno.setRamal(rs.getString("ramal"));
                usuarioRetorno.setAcessoNome(rs.getString("descricao"));
                usuarioRetorno.setAtivo(rs.getInt("ativo"));
                lista.add(usuarioRetorno);
            }
            return lista;
        } catch (SQLException e) {
            ErroSql.Gravar(nomeClasse, "listaUsuario", selectTableSQL, e.getMessage());
            return null;
        } finally {
            ConnectDataBase.closeConnection();
        }
    }
}
