package it.univaq.disim.lpo.risiko.core.datamodel;

import java.util.List;
import java.io.Serializable;

public class Gioco implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fase;
    private List<Giocatore> giocatori;
    private Mappa mappa;
    private List<Giocatore> ordineGiocatori;
    private List<CartaTerritorio> carteTerritorio;
    private List<CartaObiettivo> carteObiettivo;
    private boolean armateDistribuite;
    private boolean partitaInCorso = true;
    private MazzoDiCarte mazzoDiCarte;
    private boolean ritornaAlMenu = false;
    private int currentPlayerIndex = 0;
    private TurnState currentTurnState = new TurnState();
    private String logFileName;
    private int roundCount;
    private boolean isLoadedGame;

    public Gioco(String fase, List<Giocatore> giocatori, Mappa mappa, int faccia_dado, List<CartaTerritorio> carteTerritorio, List<CartaObiettivo> carteObiettivo) {
        this.fase = fase;
        setGiocatori(giocatori);
        this.mappa = mappa;
        this.armateDistribuite = false;
        this.carteTerritorio = carteTerritorio;
        this.carteObiettivo = carteObiettivo;
        this.ordineGiocatori = giocatori;
        this.roundCount = 1;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public boolean isLoadedGame() {
        return isLoadedGame;
    }

    public void setLoadedGame(boolean isLoadedGame) {
        this.isLoadedGame = isLoadedGame;
    }

    public void incrementRoundCount() {
        this.roundCount++;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public TurnState getCurrentTurnState() {
        return currentTurnState;
    }

    public void setCurrentTurnState(TurnState currentTurnState) {
        this.currentTurnState = currentTurnState;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int index) {
        this.currentPlayerIndex = index;
    }

    public boolean isRitornaAlMenu() {
        return ritornaAlMenu;
    }

    public void setRitornaAlMenu(boolean ritornaAlMenu) {
        this.ritornaAlMenu = ritornaAlMenu;
    }

    public MazzoDiCarte getMazzoDiCarte() {
        return mazzoDiCarte;
    }

    public void setMazzoDiCarte(MazzoDiCarte mazzoDiCarte) {
        this.mazzoDiCarte = mazzoDiCarte;
    }

    public boolean isPartitaInCorso() {
        return partitaInCorso;
    }

    public void setPartitaInCorso(boolean partitaInCorso) {
        this.partitaInCorso = partitaInCorso;
    }

    public boolean isArmateDistribuite() {
        return armateDistribuite;
    }

    public void setArmateDistribuite(boolean armateDistribuite) {
        this.armateDistribuite = armateDistribuite;
    }

    public List<Giocatore> getOrdineGiocatori() {
        return ordineGiocatori;
    }

    public void setOrdineGiocatori(List<Giocatore> ordineGiocatori) {
        this.ordineGiocatori = ordineGiocatori;
    }

    public String getFase() {
        return this.fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public List<Giocatore> getGiocatori() {
        return this.giocatori;
    }

    public void setGiocatori(List<Giocatore> giocatori) {
        if (giocatori == null) {
            throw new IllegalArgumentException("La lista dei giocatori non può essere null.");
        }
        if (giocatori.size() < 2 || giocatori.size() > 6) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 6.");
        }
        this.giocatori = giocatori;
    }

    public Mappa getMappa() {
        return this.mappa;
    }

    public void setMappa(Mappa mappa) {
        this.mappa = mappa;
    }

    public List<CartaTerritorio> getCarteTerritorio() {
        return this.carteTerritorio;
    }

    public void setCarteTerritorio(List<CartaTerritorio> carteTerritorio) {
        this.carteTerritorio = carteTerritorio;
    }

    public List<CartaObiettivo> getCarteObiettivo() {
        return this.carteObiettivo;
    }

    public void setCarteObiettivo(List<CartaObiettivo> carteObiettivo) {
        this.carteObiettivo = carteObiettivo;
    }
    
}