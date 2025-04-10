package gestaoescolar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {
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

    public int salvar(Nota nota) {
        if (!conectar()) {
            return -1;
        }
        String sql = "INSERT INTO notas (id, aluno_id, materia_id, nota, data_lancamento) VALUES (?, ?, ?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, nota.getId());
            st.setInt(2, nota.getAluno().getId());
            st.setInt(3, nota.getMateria().getId());
            st.setDouble(4, nota.getNota());
            st.setString(5, nota.getDataLancamento());
            
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    public Nota consultar(int id) {
        if (!conectar()) {
            return null;
        }
        String sql = "SELECT * FROM notas WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setNota(rs.getDouble("nota"));
                nota.setDataLancamento(rs.getString("data_lancamento"));
                
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("aluno_id"));
                nota.setAluno(aluno);
                
                Materia materia = new Materia();
                materia.setId(rs.getInt("materia_id"));
                nota.setMateria(materia);
                
                return nota;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Nota> listarNotasPorAluno(int alunoId) {
        List<Nota> listaNotas = new ArrayList<>();
        if (!conectar()) {
            return listaNotas;
        }
        String sql = "SELECT * FROM notas WHERE aluno_id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, alunoId);
            rs = st.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setId(rs.getInt("id"));
                nota.setNota(rs.getDouble("nota"));
                nota.setDataLancamento(rs.getString("data_lancamento"));
                
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("aluno_id"));
                nota.setAluno(aluno);
                
                Materia materia = new Materia();
                materia.setId(rs.getInt("materia_id"));
                nota.setMateria(materia);
                
                listaNotas.add(nota);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar notas: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return listaNotas;
    }
}

