package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

    private static final String URL      = "jdbc:mysql://localhost:3306/liga?useSSL=false&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = ""; 

    private static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertar(Jugador j) throws SQLException {
        String sql = "INSERT INTO JUGADOR (NIF_jugador, nombre, apellidos, fecha_nacimiento, " +
                "club, sueldo, numero, posicion, goles, asistencias, codigo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, j.getNif());
            ps.setString(2, j.getNombre());
            ps.setString(3, j.getApellidos());
            ps.setString(4, j.getFecha());
            ps.setString(5, j.getClub());
            ps.setDouble(6, j.getSueldo());
            ps.setInt   (7, j.getNumero());
            ps.setString(8, j.getPosicion());
            ps.setInt   (9, j.getGoles());
            ps.setInt   (10, j.getAsistencias());
            ps.setInt   (11, 1); 
            ps.executeUpdate();
        }
    }

    public static List<Jugador> listar() throws SQLException {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT NIF_jugador, nombre, apellidos, DATE_FORMAT(fecha_nacimiento,'%Y-%m-%d'), " +
                "club, sueldo, numero, posicion, goles, asistencias FROM JUGADOR";
        try (Connection con = getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Jugador(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getDouble(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
            }
        }
        return lista;
    }
}