package gestaoescolar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
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

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }

    public int salvar(Professor prof) {
        if (!conectar()) {
            return -1;
        }
        String sql = "INSERT INTO professores (id, materias_id) VALUES (?, ?)";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, prof.getId());
            st.setInt(2, prof.getMateria().getId());
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    public Professor consultar(int id) {
        if (!conectar()) {
            return null;
        }
        String sql = "SELECT * FROM professores WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Professor prof = new Professor();
                prof.setId(rs.getInt("id"));
                
                
                Materia materia = new Materia();
                materia.setId(rs.getInt("materia_id"));
                prof.setMateria(materia);
                
                return prof;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Professor> listarProfessoresPorMateria(int materiaId) {
        List<Professor> listaProfessores = new ArrayList<>();
        if (!conectar()) {
            return listaProfessores;
        }
        String sql = "SELECT * FROM professores WHERE materias_id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, materiaId);
            rs = st.executeQuery();
            while (rs.next()) {
                Professor prof = new Professor();
                prof.setId(rs.getInt("id"));
                
                
                Materia materia = new Materia();
                materia.setId(rs.getInt("materias_id"));
                prof.setMateria(materia);
                
                listaProfessores.add(prof);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar professores: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return listaProfessores;
    }
}




