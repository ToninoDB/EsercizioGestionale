import java.util.Random;
import java.util.Scanner;

public class Antonio {

    // Una serie di prove matematiche di difficoltà crescente
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

}
