package Gestionale;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App_final {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        mostraMenuPrincipale(obj);

    }

    // Metodo per il menu principale
    public static void mostraMenuPrincipale(Scanner scanner) {
        int scelta;
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            stampaMenuPrincipale();
            scelta = controlloInputInteri(scanner);

            switch (scelta) {
                case 1:
                    RegistroUtenti.registraUtente();
                    break;
                case 2:
                    Utente utenteLoggato = RegistroUtenti.login();
                    if (utenteLoggato != null) {
                        mostraSottoMenu(scanner, utenteLoggato);
                    }
                    break;
                case 3:
                    RegistroUtenti.stampaUtentiRegistrati();
                    break;
                case 4:
                    System.out.println("Uscita dal sistema!");
                    exitMainMenu = true;
                    break;
                default:
                    System.out.println("Opzione non valida! Riprova.");
            }
        }
    }

    // Metodo di stampa del menu principale
    public static void stampaMenuPrincipale() {
        System.out.println("\n================================");
        System.out.println("         MENU PRINCIPALE        ");
        System.out.println("================================");
        System.out.println(" 1. Registrazione");
        System.out.println(" 2. Login");
        System.out.println(" 3. Stampa del registro");
        System.out.println(" 3. Esci");
        System.out.println("================================");
        System.out.print("Scegli un'opzione (1-3): ");
    }

    // Metodo per controllare l'input intero positivo
    public static int controlloInputInteri(Scanner scanner) {
        while (true) {
            // Controllo se l'input è un intero
            if (!scanner.hasNextInt()) {
                System.out.print("Devi inserire un numero intero. Riprova: ");
                scanner.next(); // Scarta l'input errato
                continue;
            }

            int valore = scanner.nextInt();
            if (valore >= 0) {
                return valore; // Ritorna solo se è un numero valido
            }

            System.out.print("Il numero non può essere negativo. Riprova: ");
        }
    }

    // Metodo per il sotto-menu per eliminare una città
    public static void mostraSottoMenu(Scanner scanner, Utente utenteLoggato) {
        int sceltaSottomenu;
        boolean ritornoAlMenuPrincipale = false;

        do {
            stampaSottoMenu();
            sceltaSottomenu = controlloInputInteri(scanner);

            switch (sceltaSottomenu) {
                case 1:
                    RegistroUtenti.modificaDatiUtente(utenteLoggato);
                    break;
                case 2:
                    Minigioco minigioco = new Minigioco();
                    minigioco.avviaGioco();
                    break;
                case 0:
                    System.out.println("Tornando al menu principale...");
                    ritornoAlMenuPrincipale = true;
                    break;
                default:
                    System.out.println("Opzione non valida! Riprova.");
            }
        } while (!ritornoAlMenuPrincipale);
    }

    // Metodo di stampa del sottomenu
    public static void stampaSottoMenu() {
        System.out.println("\n================================");
        System.out.println("         SOTTO-MENU             ");
        System.out.println("================================");
        System.out.println(" 1. Modifica");
        System.out.println(" 2. Minigioco");
        System.out.println(" 0. Torna al menu principale");
        System.out.println("================================");
        System.out.print("Scegli un'opzione (0-2): ");
    }

}

// Classe Utente con nome e password
class Utente {
    String nome;
    String password;

    public Utente(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

}

// Classe RegistroUtenti per la registrazione e il login
class RegistroUtenti {
    // Creazione arreylist
    static ArrayList<Utente> utenti = new ArrayList<>();

    // Controllo che il nome utente non esista già
    public static boolean verificaNomeUtente(String nome) {
        for (Utente u : utenti) {
            if (u.nome.equalsIgnoreCase(nome))
                return false;
        }
        return true;
    }

    // Registrazione di un nuovo utente
    @SuppressWarnings("resource")
    public static Utente registraUtente() {
        Scanner obj = new Scanner(System.in);
        String nome;

        do {
            System.out.println("Inserisci nome:");
            nome = obj.nextLine();

            if (!RegistroUtenti.verificaNomeUtente(nome)) {
                System.out.println("Nome utente già in uso. Scegli un altro nome.");
            }
        } while (!RegistroUtenti.verificaNomeUtente(nome)); // Continua finché il nome non è univoco

        System.out.println("Inserisci password:");
        String password = obj.nextLine();

        Utente utente = new Utente(nome, password);
        utenti.add(utente);
        System.out.println("Registrazione completata con successo!");
        return utente;
    }

    // Controllo che il login sia andato a buon fine
    @SuppressWarnings("resource")
    public static Utente login() {
        Scanner obj = new Scanner(System.in);
        String nome, password;
        Utente utenteLoggato = null;

        System.out.println("Login - Inserisci le tue credenziali:");

        // Richiesta nome utente
        boolean utenteEsiste;
        do {
            System.out.print("Nome utente: ");
            nome = obj.nextLine();
            utenteEsiste = false;

            for (Utente u : utenti) {
                if (u.nome.equalsIgnoreCase(nome)) {
                    utenteEsiste = true;
                    break;
                }
            }

            if (!utenteEsiste) {
                System.out.println("Nome utente non trovato. Riprova.");
            }

        } while (!utenteEsiste);

        // Richiesta password
        System.out.print("Password: ");
        password = obj.nextLine();

        // Verifica credenziali
        for (Utente u : utenti) {
            if (u.nome.equalsIgnoreCase(nome) && u.password.equals(password)) {
                utenteLoggato = u;
                break;
            }
        }

        if (utenteLoggato != null) {
            System.out.println("Login effettuato con successo! Benvenuto, " + utenteLoggato.nome + "!");
        } else {
            System.out.println("Password errata. Accesso negato.");
        }

        return utenteLoggato;
    }

    // Metodo per stampare tutti gli utenti registrati
    public static void stampaUtentiRegistrati() {
        if (utenti.isEmpty()) {
            System.out.println("Nessun utente registrato.");
        } else {
            System.out.println("Utenti registrati:");
            for (Utente u : utenti) {
                System.out.println("Nome: " + u.nome + ", Password: " + u.password);
            }
        }
    }

    // Metodo per modificare i dati dell'utente
    @SuppressWarnings("resource")
    public static void modificaDatiUtente(Utente utente) {
        Scanner obj = new Scanner(System.in);
        String nuovoNome, nuovaPassword;

        // Modifica del nome utente
        do {
            System.out.println("Inserisci il nuovo nome utente:");
            nuovoNome = obj.nextLine();

            if (nuovoNome.equalsIgnoreCase(utente.nome)) {
                System.out.println("Il nuovo nome utente non può essere uguale al precedente. Riprova.");
            } else if (!verificaNomeUtente(nuovoNome)) {
                System.out.println("Nome utente già in uso. Scegli un altro nome.");
            }
        } while (nuovoNome.equalsIgnoreCase(utente.nome) || !verificaNomeUtente(nuovoNome));

        // Modifica della password
        System.out.println("Inserisci la nuova password:");
        nuovaPassword = obj.nextLine();

        // Aggiornamento dei dati dell'utente
        utente.nome = nuovoNome;
        utente.password = nuovaPassword;

        System.out.println("Dati utente aggiornati con successo!");
    }
}

// Classe Minigioco per il gioco matematico
class Minigioco {
    int difficolta;
    int punteggio;
    int risultatoAtteso;
    Random rand;

    public Minigioco() {
        this.difficolta = 1;
        this.punteggio = 0;
        this.rand = new Random();
    }

    // Metodo per generare la domanda in base alla difficoltà attuale
    public String generaDomanda() {
        int num1, num2, num3;
        String domanda = "";

        switch (difficolta) {
            case 1: // Facile (Addizione e Sottrazione)
                num1 = rand.nextInt(20) + 1;
                num2 = rand.nextInt(20) + 1;
                num3 = rand.nextInt(10) + 1;
                risultatoAtteso = num1 + num2 - num3;
                domanda = num1 + " + " + num2 + " - " + num3 + " = ?";
                break;

            case 2: // Intermedio (Moltiplicazione)
                num1 = rand.nextInt(10) + 1;
                num2 = rand.nextInt(10) + 1;
                risultatoAtteso = num1 * num2;
                domanda = num1 + " × " + num2 + " = ?";
                break;

            case 3: // Difficile (Operazioni Complesse)
                num1 = rand.nextInt(20) + 1;
                num2 = rand.nextInt(20) + 1;
                num3 = rand.nextInt(10) + 1;
                risultatoAtteso = (num1 + num2) * num3;
                domanda = "(" + num1 + " + " + num2 + ") × " + num3 + " = ?";
                break;

            default:
                return "Errore: Difficoltà non valida";
        }
        return domanda;
    }

    // Metodo di verifica della risposta
    public boolean verificaRisposta(int rispostaUtente) {
        if (rispostaUtente == risultatoAtteso) {
            punteggio += 10;
            System.out.println("Risposta corretta! Punteggio: " + punteggio);
            return true;
        } else {
            punteggio -= 5;
            if (punteggio < -30) {
                System.out.println("Hai perso! Troppi errori. Punteggio finale: " + punteggio);
                return false;
            }
            System.out.println("Risposta sbagliata! Il risultato corretto era: " + risultatoAtteso + ". Punteggio: "
                    + punteggio);
            return false;
        }
    }

    // Metodo per giocare con l'utente
    @SuppressWarnings("resource")
    public void avviaGioco() {
        Scanner input = new Scanner(System.in);

        System.out.println("Minigioco matematico!");
        System.out.println("Rispondi correttamente alle domande per avanzare di difficoltà.");
        System.out.println("Se il punteggio scende sotto -30, perdi!\n");

        // Ciclo per generare le domande e giocare effettivamente con l'utente
        while (punteggio > -30 && difficolta <= 3) {
            String domanda = this.generaDomanda();
            boolean rispostaCorretta = false;

            while (!rispostaCorretta && punteggio > -30) {
                System.out.println("Difficoltà " + difficolta + ": " + domanda);
                System.out.print("Inserisci la tua risposta: ");

                if (input.hasNextInt()) {
                    int risposta = input.nextInt();
                    rispostaCorretta = verificaRisposta(risposta);

                    if (rispostaCorretta) {
                        difficolta++;
                    }
                } else {
                    System.out.println("Inserisci un numero valido!");
                    input.next();
                }
            }
        }

        // Controllo se il giocatore ha risposto correttamente a tutte le domande o no
        if (difficolta > 3) {
            System.out.println("Complimenti! Hai superato tutte le difficoltà e hai vinto!");
        } else {
            System.out.println("Game over! Hai perso.");
        }
    }
}