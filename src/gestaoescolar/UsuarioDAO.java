
package gestaoescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO {
     private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    // Método para conectar ao banco de dados
   public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_escolar_integrada", "root", "cla12345ra");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
     public int salvar(Usuario usu) {
        if (!conectar()) {
            return -1; // Indica erro de conexão
        }

        int status;
        try {
            st = conn.prepareStatement("INSERT INTO usuarios (nome, email, senha, tipo, data_cadastro) VALUES (?, ?, ?, ?, ?)");
            st.setString(1, usu.getNome());
            st.setString(2, usu.getEmail());
           st.setString(3, usu.getSenha());
            st.setString(4, usu.getTipo());
             st.setString(5, usu.getDataCadastro());
            status = st.executeUpdate();
            return status; // Retorna 1 se for bem-sucedido
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    // Método para consultar um filme pelo ID
    public Usuario consultar(int id) {
        if (!conectar()) {
            return null;
        }

        try {
            Usuario usu = new Usuario();
            st = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                usu.setId(rs.getInt("id"));
                usu.setNome(rs.getString("nome"));
                usu.setEmail(rs.getString("email"));
               usu.setSenha(rs.getString("senha"));
               usu.setTipo(rs.getString("tipo"));
               usu.setDataCadastro(rs.getString("data_cadastro"));
                return usu;
            } else {
                return null; // Retorna null se não encontrar o filme
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar: " + ex.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }


    // Método para buscar filmes pelo nome (retorna uma lista)
    public List<Usuario> getNome(String nome) {
        List<Usuario> listaUsu = new ArrayList<>();
        
        if (!conectar()) { 
            return listaUsu; 
        }

        String sql = "SELECT * FROM usuarios WHERE nome LIKE ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setNome(rs.getString("nome"));
                usu.setEmail(rs.getString("email"));
               usu.setSenha(rs.getString("senha"));
               usu.setTipo(rs.getString("tipo"));
               usu.setDataCadastro(rs.getString("data_cadastro"));

                listaUsu.add(usu);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            desconectar();
        }
        return listaUsu;
    }

    // Método para desconectar do banco de dados
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            // Pode ser ignorado para evitar mensagens desnecessárias ao usuário
        }
    }
}

