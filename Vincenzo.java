import java.util.*;




public class Vincenzo {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Utente utenteAttuale = null;
      // ciclo while per entrare nell'app  
        while (utenteAttuale == null) {
            // creazione del menu
            System.out.println("1. Registrati  2. Login  3. Esci");
            int scelta = obj.nextInt();
            obj.nextLine(); 
            
            if (scelta == 1) {
                System.out.println("Inserisci nome:");
                String nome = obj.nextLine();
             // controllo che il nome non sia già stato inserito   
                if (!RegistroUtenti.verificaNomeUtente(nome)) {
                    System.out.println("Nome utente già in uso.");
                    continue;
                }
                
                System.out.println("Inserisci password:");
                String password = obj.nextLine();
                // inserimento degli elementi
                RegistroUtenti.registraUtente(nome, password);
                System.out.println("Registrazione avvenuta con successo!");

                // login
            } else if (scelta == 2) {
                System.out.println("Inserisci nome:");
                String nome = obj.nextLine();
                System.out.println("Inserisci password:");
                String password = obj.nextLine();
                
                utenteAttuale = RegistroUtenti.login(nome, password);
                if (utenteAttuale == null) {
                    System.out.println("Credenziali errate.");
                } else {
                    System.out.println("Login effettuato con successo!");
                    System.out.println("Nome utente: " + utenteAttuale.nome + " | Password: " + utenteAttuale.password);
                }
            } else {
                return;
            }
        }
        
        
        
    }
} //classe degli utenti
class Utente {
    String nome;
    String password;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
    
    }
}

class RegistroUtenti {
    //creazione arreylist
    private static ArrayList<Utente> utenti = new ArrayList<>();
//controllo che il nome utente non esista già
    public static boolean verificaNomeUtente(String nome) {
        for (Utente u : utenti) {
            if (u.nome.equalsIgnoreCase(nome)) return false;
        }
        return true;
    }

    public static boolean registraUtente(String nome, String password) {
        utenti.add(new Utente(nome, password));
        return true;
    }
    // controllo che il login sia andato a buon fine
    public static Utente login(String nome, String password) {
        for (Utente u : utenti) {
            if (u.nome.equalsIgnoreCase(nome) && u.password.equals(password)) {
                return u;
            }
        }
        return null;
    }
}