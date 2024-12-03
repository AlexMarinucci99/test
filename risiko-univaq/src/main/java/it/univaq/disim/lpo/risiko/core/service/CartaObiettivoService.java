package it.univaq.disim.lpo.risiko.core.service;

import java.util.List;
import it.univaq.disim.lpo.risiko.core.datamodel.CartaObiettivo;
import it.univaq.disim.lpo.risiko.core.datamodel.Giocatore;

/**
 * Interfaccia per i servizi relativi alle carte obiettivo.
 */
public interface CartaObiettivoService {

    /**
     * Genera una lista di carte obiettivo casuali.
     *
     * @param numeroObiettivi il numero di carte obiettivo da generare.
     * @return una lista di carte obiettivo generate casualmente.
     */
    List<CartaObiettivo> generaObiettiviCasuali(int numeroObiettivi);

    /**
     * Assegna casualmente le carte obiettivo ai giocatori.
     *
     * @param giocatori        la lista dei giocatori.
     * @param obiettiviCasuali la lista di carte obiettivo da assegnare.
     */
    void assegnaObiettiviCasuali(List<Giocatore> giocatori, List<CartaObiettivo> obiettiviCasuali);
    
}