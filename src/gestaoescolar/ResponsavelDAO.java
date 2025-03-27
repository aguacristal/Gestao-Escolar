/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaoescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliana
 */
public class ResponsavelDAO {
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
     public int salvar(Responsavel res) {
        if (!conectar()) {
            return -1; // Indica erro de conexão
        }

        int status;
        try {
            st = conn.prepareStatement("INSERT INTO responsaveis (id, telefone, endereco) VALUES (?, ?, ?)");
            st.setInt(1, res.getId());
            st.setString(2, res.getTelefone());
            st.setString(3, res.getEndereco());
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
    public Responsavel consultar(int id) {
        if (!conectar()) {
            return null;
        }

        try {
            Responsavel res = new Responsavel();
            st = conn.prepareStatement("SELECT * FROM responsaveis WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                res.setId(rs.getInt("id"));
                res.setTelefone(rs.getString("telefone"));
                res.setEndereco(rs.getString("endereco"));
               return res;
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
    public List<Responsavel> getId(String id) {
        List<Responsavel> listaRes = new ArrayList<>();
        
        if (!conectar()) { 
            return listaRes; 
        }

        String sql = "SELECT * FROM responsaveis WHERE id LIKE ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, "%" + id + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Responsavel res = new Responsavel();
                res.setId(rs.getInt("id"));
                res.setTelefone(rs.getString("telefone"));
                res.setEndereco(rs.getString("endereco"));
             

                listaRes.add(res);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            desconectar();
        }
        return listaRes;
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




