import db.BancoDados;
import test.Teste;

public class App {
    public static void main(String[] args) throws Exception {
        Teste.menu(BancoDados.conectar());   
    }
}
