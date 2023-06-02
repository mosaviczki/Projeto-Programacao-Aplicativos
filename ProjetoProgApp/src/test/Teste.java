package test;

import java.sql.Connection;
import java.util.Scanner;

import db.CategoriaDao;
import entities.Categoria;

public class Teste {
    public static void menu(Connection conn) throws Exception {
        System.out.println("Escolha uma opção:");

        System.out.println("1 - Cadastrar categoria");

        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();

        switch(opcao){
            case 1:
                System.out.println("Digite o nome da categoria:");
                String nome = input.next();
                Categoria categoria = new Categoria();

                categoria.setNome(nome);
                new CategoriaDao(conn).create(categoria);

                break;
            default:
                System.out.println("Opção inválida");
        }

        input.close();
    }
}
