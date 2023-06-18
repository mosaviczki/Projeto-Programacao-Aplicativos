package service;

import entities.Categoria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.CategoriaDao;

import java.util.ArrayList;

public class CategoriaService {
    public void createCategoria(Categoria categoria) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new CategoriaDao(conn).create(categoria);  
    }

    public void updateCategoria(Categoria categoria) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new CategoriaDao(conn).update(categoria);
    }

    public void deleteCategoria(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new CategoriaDao(conn).delete(id);
    }

    public ArrayList<Categoria> findAllCategoria(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new CategoriaDao(conn).findAll();
    }
}
