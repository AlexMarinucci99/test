package it.univaq.disim.lpo.risiko.core;

import it.univaq.disim.lpo.risiko.core.datamodel.*;
import it.univaq.disim.lpo.risiko.core.service.*;
import it.univaq.disim.lpo.risiko.core.service.impl.*;
import it.univaq.disim.lpo.risiko.core.utils.OutputUtils;
import java.util.List;
import org.fusesource.jansi.AnsiConsole;

/**
 * Classe principale Runner per l'avvio del gioco.
 */
public class Runner {
    public static void main(String[] args) {
        // Installa AnsiConsole per supportare i colori ANSI nella console
        AnsiConsole.systemInstall();

        // Creazione delle istanze dei servizi necessari
        GiocoService giocoService = new GiocoServiceImpl();
        GiocatoreService giocatoreService = new GiocatoreServiceImpl();

        boolean running = true; // Flag per controllare il ciclo principale del gioco

        // Ciclo principale del gioco
        while (running) {
            try {
                // Inizializza la partita (nuova o caricata)
                Gioco gioco = giocoService.inizializzaPartita();

                // Distribuisce le armate iniziali solo se non sono già state distribuite
                if (!gioco.isArmateDistribuite()) {
                    int numeroGiocatori = gioco.getOrdineGiocatori().size();
                    int armatePerGiocatore = giocatoreService.calcolaArmatePerGiocatore(numeroGiocatori);
                    giocatoreService.distribuzioneInizialeArmate(gioco.getOrdineGiocatori(), armatePerGiocatore);

                    // Imposta il flag per evitare la ridistribuzione
                    gioco.setArmateDistribuite(true);
                }

                // Ciclo interno del gioco: esegue i turni dei giocatori
                while (gioco.isPartitaInCorso() && !gioco.isRitornaAlMenu()) {
                    List<Giocatore> ordineGiocatori = gioco.getOrdineGiocatori();

                    // Ottiene il giocatore corrente
                    Giocatore giocatore = ordineGiocatori.get(gioco.getCurrentPlayerIndex());

                    // Esegue il turno del giocatore
                    boolean continua = giocoService.turnoGiocatore(giocatore, gioco);
                    if (!continua || !gioco.isPartitaInCorso() || gioco.isRitornaAlMenu()) {
                        if (gioco.isPartitaInCorso()) {
                            // Logga la fine del turno
                            FileServiceImpl.getInstance().writeLog("Turno di " + giocatore.getNome().toUpperCase() + " terminato.");
                            FileServiceImpl.getInstance().writeLog("");
                        } else {
                            FileServiceImpl.getInstance().writeLog("GIOCO TERMINATO");
                        }
                        break; // Esce dal ciclo interno se il gioco è terminato o si torna al menu
                    }
                    // Aggiorna l'indice del giocatore corrente
                    int nextPlayerIndex = (gioco.getCurrentPlayerIndex() + 1) % ordineGiocatori.size();
                    gioco.setCurrentPlayerIndex(nextPlayerIndex);

                    // Controlla se un round è stato completato (tutti i giocatori hanno giocato)
                    if (nextPlayerIndex == 0) {
                        gioco.incrementRoundCount(); // Incrementa il contatore dei round

                        // Mostra messaggio di completamento del round
                        OutputUtils.println("\n══════════════════", OutputUtils.ANSI_BRIGHT_GREEN, OutputUtils.ANSI_BOLD);
                        OutputUtils.println("ROUND " + (gioco.getRoundCount() - 1) + " COMPLETATO", OutputUtils.ANSI_BRIGHT_PURPLE, OutputUtils.ANSI_BOLD);
                        OutputUtils.println("══════════════════", OutputUtils.ANSI_BRIGHT_GREEN, OutputUtils.ANSI_BOLD);
                        try {
                            Thread.sleep(2000); // Pausa per migliorare la leggibilità
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // Controlla se l'utente ha scelto di tornare al menu
                if (gioco.isRitornaAlMenu()) {
                    continue; // Torna all'inizio del ciclo principale
                }

                // Controlla se la partita è terminata
                if (!gioco.isPartitaInCorso()) {
                    OutputUtils.print("\nLa partita è terminata, vuoi tornare al menu iniziale? (S/N): ", OutputUtils.ANSI_CYAN, OutputUtils.ANSI_BOLD);
                    String risposta = SingletonMain.getInstance().readString();
                    if (risposta.equalsIgnoreCase("n")) {
                        System.out.println();
                        running = false; // Termina il gioco
                    }
                    // Se l'utente sceglie "s", il gioco riparte
                }
            } catch (InizializzaPartitaException e) {
                System.out.println("Errore durante l'inizializzazione della partita: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
            }
        }
        // Disinstalla AnsiConsole al termine del gioco
        AnsiConsole.systemUninstall();
    }

}