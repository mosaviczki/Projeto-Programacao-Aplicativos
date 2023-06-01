package run;

import db.BancoDados;

public class App {
    public static void main(String[] args) throws Exception {
        BancoDados.conectar();
    }
}
