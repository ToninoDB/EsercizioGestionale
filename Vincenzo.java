import java.util.*;
public class Vincenzo {
    
}
class Utente {
    String nome;
    String password;
    int punteggio;
    boolean bloccato;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
        this.punteggio = 0;

    }
}
class RegistroUtenti {
    private static ArrayList<Utente> utenti = new ArrayList<>();

    public static boolean registraUtente(String nome, String password) {
        for (Utente u : utenti) {
            if (u.nome.equalsIgnoreCase(nome)) return false; // Nome univoco richiesto
        }
        utenti.add(new Utente(nome, password));
        return true;
    }
    public static Utente login(String nome, String password) {
        for (Utente u : utenti) {
            if (u.nome.equalsIgnoreCase(nome) && u.password.equals(password)) {
                return u;
            }
        }
        return null;
    }
    
    
}

