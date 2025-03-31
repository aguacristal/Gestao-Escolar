package gestaoescolar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecuperacaoDAO {
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

    public int salvar(Recuperacao rec) {
        if (!conectar()) {
            return -1;
        }
        String sql = "INSERT INTO recuperacoes (id, aluno_id, materia_id, nota, data_recuperacao) VALUES (?, ?, ?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, rec.getId());
            st.setInt(2, rec.getAluno().getId());
            st.setInt(3, rec.getMateria().getId());
            st.setDouble(4, rec.getNota());
            st.setString(5, rec.getDataRecuperacao());
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    public Recuperacao consultar(int id) {
        if (!conectar()) {
            return null;
        }
        String sql = "SELECT * FROM recuperacoes WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Recuperacao rec = new Recuperacao();
                rec.setId(rs.getInt("id"));
                
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("aluno_id"));
                rec.setAluno(aluno);
                
                Materia materia = new Materia();
                materia.setId(rs.getInt("materia_id"));
                rec.setMateria(materia);
                
                rec.setNota(rs.getDouble("nota"));
                rec.setDataRecuperacao(rs.getString("data_recuperacao"));
                
                return rec;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return null;
    }

    public List<Recuperacao> listarRecuperacoesPorAluno(int alunoId) {
        List<Recuperacao> listaRecuperacoes = new ArrayList<>();
        if (!conectar()) {
            return listaRecuperacoes;
        }
        String sql = "SELECT * FROM recuperacoes WHERE aluno_id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, alunoId);
            rs = st.executeQuery();
            while (rs.next()) {
                Recuperacao rec = new Recuperacao();
                rec.setId(rs.getInt("id"));
                
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("aluno_id"));
                rec.setAluno(aluno);
                
                Materia materia = new Materia();
                materia.setId(rs.getInt("materia_id"));
                rec.setMateria(materia);
                
                rec.setNota(rs.getDouble("nota"));
                rec.setDataRecuperacao(rs.getString("data_recuperacao"));
                
                listaRecuperacoes.add(rec);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar recuperações: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return listaRecuperacoes;
    }
}