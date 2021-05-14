import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

import java.util.HashMap;

public class Giocatore {
    public static final String ACQUA_PIETRE_DISPONIBILI = "Premi  per 0 per acqua : pietre disponibili -> ";
    public static final String FUOCO_PIETRE_DISPONIBILI = "Premi  per 1 per fuoco : pietre disponibili ->  ";
    public static final String ARIA_PIETRE_DISPONIBILI = "Premi  per 2 per aria : pietre disponibili -> ";
    public static final String TERRA_PIETRE_DISPONIBILI = "Premi  per 3 per terra : pietre disponibili -> ";
    public static final String ETERE_PIETRE_DISPONIBILI = "Premi  per 4 per etere : pietre disponibili -> ";
    public static final String SCEGLI_LA_PIETRA_DA_ASSEGNARE_AL_TAMAGOLEM = " scegli la pietra da assegnare al tamagolem:";
    public static final String ERRORE_INSERIRE_UN_VALORE_CONSONO = "errore, inserire un valore consono";
    private int tamagolemEvocabili;
    private int tamagolemEliminati = 0;
    private Tamagolem tamagolem;
    private int vita;
    private int pietreassegnabili;
    private HashMap<Integer,String> mapElementi;
//prova

    public Giocatore(int tamagolemEvocabili, int vita, int pietreassegnabili, HashMap mapElementi) {
        this.tamagolemEvocabili = tamagolemEvocabili;
        this.vita=vita;
        this.pietreassegnabili=pietreassegnabili;
        this.mapElementi = mapElementi;

    }

    /**
     * ritorna il tamagolem creato
     * @return Tamagolem
     */
    public Tamagolem getTamagolem(){
        return  this.tamagolem;
    }

    /**
     * ritorna il valore di tamagolem che posso essere evocati
     * @return tamagolemEvocabili
     */
    public int getTamagolemEvocabili(){
        return this.tamagolemEvocabili;
    }

    /**
     *crea un oggetto di tipo tamagolem e gli assegna una arraylist di pietre
     * @param scortapietre
     * @return boolean
     */
    public boolean evocazione(int scortapietre[]) {
        int scelta,npietre=pietreassegnabili;
        InputDati scanner = new InputDati();
        //controllo che il giocatore possegga ancora dei tamagolem da poter evocare
        if (tamagolemEvocabili > 0) {
            tamagolem = new Tamagolem(vita);
            tamagolemEvocabili--;
            do {

                System.out.println(SCEGLI_LA_PIETRA_DA_ASSEGNARE_AL_TAMAGOLEM);

                do {
                    mapElementi.forEach((id, nome) -> {
                        System.out.println(id + ") "+ nome +" ( Pietre disponibili nella scorta: " + scortapietre[id] + " )");
                    });
                    scelta= scanner.leggiIntero("Inserire l'id corrispondente all'elemento tra le pietre disponibili:",0,mapElementi.size()-1);
                   //controllo la presenza di pietre di un determinato elemento
                    if (scortapietre[scelta]<=0){
                        System.out.println("La pietra selezionata non è disponibile!");
                    }
                }while (scortapietre[scelta]<=0);

                scortapietre[scelta]--;
                npietre--;
                tamagolem.addPietre(scelta,mapElementi.get(scelta));

            } while (npietre > 0);
        } else {
            return false;
        }
        return true;
    }
}