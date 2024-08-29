package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Direccion;
import com.example.ejemplodaoypatrones.entities.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DireccionDAO {
    private Connection conn;

    public DireccionDAO(Connection conn) {
        this.conn = conn;
    }
    public void insert(Direccion direccion) throws Exception {
    	String query = "INSERT INTO Direccion (idDireccion, nombre, edad, numero) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, direccion.getIdDireccion());
            ps.setString(2, direccion.getCiudad()); 
            ps.setString(3, direccion.getCalle()); 
            ps.setInt(4, direccion.getNumero());
            ps.executeUpdate();
            System.out.println("Persona insertada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public Direccion find(Integer pk) {
    	String query = "SELECT d.ciudad, d.calle, d.numero " +
                "FROM Direccion d " +
                "WHERE d.idDireccion = ?";
        Direccion direccionById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String ciudad = rs.getString("ciudad");
                String calle = rs.getString("calle");
                int numero = rs.getInt("numero");

                // Crear una nueva instancia de Persona con los datos recuperados de la consulta
                direccionById = new Direccion(pk, ciudad, calle,numero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return direccionById;
    }


    public boolean update(Direccion dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    public List<Direccion> selectList() {
        String query = "SELECT * " +
                "FROM Direccion ";
        Persona personaById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Direccion> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Crear una nueva instancia de Persona con los datos recuperados de la consulta
            listado = new ArrayList<Direccion>();
            while (rs.next()) { // Verificar si hay resultados
                int idDireccion = rs.getInt("idDireccion");
                String ciudad = rs.getString("ciudad");
                String calle = rs.getString("calle");
                int numero = rs.getInt("numero");
                Direccion direccion = new Direccion(idDireccion, ciudad, calle, numero);
                listado.add(direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

}
