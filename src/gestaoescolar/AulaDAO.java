package gestaoescolar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO {
    private Connection conn;

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
    public int salvar(Aula aul) {
        if (!conectar()) {
            return -1; // Indica erro de conexão
        }

        String sql = "INSERT INTO aulas (materia_id, professor_id, data, hora_inicio, hora_fim) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, aul.getMateria().getId());
            st.setInt(2, aul.getProfessor().getId());
            st.setString(3, aul.getData());
            st.setString(4, aul.getHoraInicio());
            st.setString(5, aul.getHoraFim());

            int status = st.executeUpdate();
            if (status > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    aul.setId(rs.getInt(1)); // Atualiza o ID gerado no objeto
                }
            }
            return status; // Retorna 1 se for bem-sucedido
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    // Método para consultar uma aula pelo ID
    public Aula consultar(int id) {
        if (!conectar()) {
            return null;
        }

        String sql = "SELECT id, materia_id, professor_id, data, hora_inicio, hora_fim FROM aulas WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Aula aul = new Aula();
                    aul.setId(rs.getInt("id"));
                    aul.setMateria(buscarMateriaPorId(rs.getInt("materia_id")));
                    aul.setProfessor(buscarProfessorPorId(rs.getInt("professor_id")));
                    aul.setData(rs.getString("data"));
                    aul.setHoraInicio(rs.getString("hora_inicio"));
                    aul.setHoraFim(rs.getString("hora_fim"));

                    return aul;
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
    public List<Aula> listarTodas() {
        List<Aula> aulas = new ArrayList<>();
        if (!conectar()) {
            return aulas;
        }

        String sql = "SELECT * FROM aulas";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Aula aul = new Aula();
                aul.setId(rs.getInt("id"));
                aul.setMateria(buscarMateriaPorId(rs.getInt("materia_id")));
                aul.setProfessor(buscarProfessorPorId(rs.getInt("professor_id")));
                aul.setData(rs.getString("data"));
                aul.setHoraInicio(rs.getString("hora_inicio"));
                aul.setHoraFim(rs.getString("hora_fim"));
                aulas.add(aul);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar aulas: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return aulas;
    }

    // Método para buscar uma matéria pelo ID (Simulado)
    private Materia buscarMateriaPorId(int materiaId) {
        // Simulação de busca (Você deve implementar a busca real no banco)
        Materia materia = new Materia();
        materia.setId(materiaId);
        materia.setNome("Matéria Exemplo"); // Simulado
        return materia;
    }

    // Método para buscar um professor pelo ID (Simulado)
    private Professor buscarProfessorPorId(int professorId) {
        // Simulação de busca (Você deve implementar a busca real no banco)
        Professor professor = new Professor();
        professor.setId(professorId);
        return professor;
    }
}
