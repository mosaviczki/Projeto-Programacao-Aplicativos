package service;

import entities.Categoria;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.CategoriaDao;

import java.util.ArrayList;

public class CategoriaService {
    public void createCategoria(String nomeCategoria) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new CategoriaDao(conn).create(nomeCategoria);
    }

    public void updateCategoria(String nomeCategoria, String novoNome) throws SQLException, IOException {
        Categoria categoria = new CategoriaService().findCategoriaByName(nomeCategoria);

        if (categoria != null) {
            Connection conn = BancoDados.conectar();
            new CategoriaDao(conn).update(novoNome, categoria.getId());
        } else
            System.out.println("Nao encontrado");
    }

    public void deleteCategoria(String nomeCategoria) throws SQLException, IOException {
        Categoria categoria = new CategoriaService().findCategoriaByName(nomeCategoria);

        Connection conn = BancoDados.conectar();
        new CategoriaDao(conn).delete(categoria.getId());
    }

    public Categoria findCategoriaByName(String nomeCategoria) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new CategoriaDao(conn).findByName(nomeCategoria);
    }

    public Categoria findCategoriaById(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new CategoriaDao(conn).buscarPorId(id);
    }

    public ArrayList<Categoria> findAllCategorias() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new CategoriaDao(conn).findAll();
    }
}
