
package gestaoescolar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class MateriaDAO {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
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

    // Método para desconectar do banco de dados
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }

    // Método para salvar uma aula no banco de dados
    public int salvar(Materia mat) {
        if (!conectar()) {
            return -1; // Indica erro de conexão
        }
        int status;
        try{ 
            st = conn.prepareStatement("INSERT INTO materias (id, nome, descricao) VALUES (?, ?, ?)");
            st.setInt(1, mat.getId());
            st.setString(2, mat.getNome());
            st.setString(3, mat.getDescricao());
            status = st.executeUpdate();
            return status; // Retorna 1 se for bem-sucedido
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    // Método para consultar uma aula pelo ID
    public Materia consultar(int id) {
        if (!conectar()) {
            return null;
        }

        String sql = "SELECT id, nome, descricao FROM materias WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Materia mat = new Materia();
                    mat.setId(rs.getInt("id"));
                    mat.setNome(rs.getString("nome"));
                    mat.setDescricao(rs.getString("descricao"));

                    return mat;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return null;
    }

    // Método para listar todas as aulas
   public List<Materia> getNome(String nome) {
        List<Materia> listaMateria = new ArrayList<>();
        
        if (!conectar()) { 
            return listaMateria; 
        }

        String sql = "SELECT * FROM materias WHERE nome LIKE ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Materia mat = new Materia();
                mat.setId(rs.getInt("id"));
                mat.setNome(rs.getString("nome"));
                mat.setDescricao(rs.getString("descricao"));
               

                listaMateria.add(mat);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            desconectar();
        }
        return listaMateria;
    }
}
