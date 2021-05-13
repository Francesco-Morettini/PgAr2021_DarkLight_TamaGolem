import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.Scanner;

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
    private static final String MSG_ACQUA="";
    public Giocatore(int tamagolemEvocabili, int vita,int pietreassegnabili) {
        this.tamagolemEvocabili = tamagolemEvocabili;
        this.vita=vita;
        this.pietreassegnabili=pietreassegnabili;

    }

    public Tamagolem getTamagolem(){
        return  this.tamagolem;
    }


    public boolean evocazione(int scortapietre[]) {
        int scelta,npietre=pietreassegnabili;
        InputDati scanner = new InputDati();
        if (tamagolemEvocabili > 0) {
            tamagolem= new Tamagolem(vita);
            tamagolemEvocabili--;
            do {
                System.out.println(SCEGLI_LA_PIETRA_DA_ASSEGNARE_AL_TAMAGOLEM);
                if (scortapietre[0] > 0)
                    System.out.println(ACQUA_PIETRE_DISPONIBILI +scortapietre[0]);
                if (scortapietre[1] > 0)
                    System.out.println(FUOCO_PIETRE_DISPONIBILI +scortapietre[1]);
                if (scortapietre[2] > 0)
                    System.out.println(ARIA_PIETRE_DISPONIBILI +scortapietre[2]);
                if (scortapietre[3] > 0)
                    System.out.println(TERRA_PIETRE_DISPONIBILI +scortapietre[3]);
                if (scortapietre[4] > 0)
                    System.out.println(ETERE_PIETRE_DISPONIBILI +scortapietre[4]);
                scelta= scanner.leggiIntero("");
                switch (scelta)
                {
                    case 0:
                        scortapietre[scelta]--;
                        npietre--;
                        tamagolem.addPietre(scelta);
                        break;
                    case 1:
                        scortapietre[scelta]--;
                        npietre--;
                        tamagolem.addPietre(scelta);
                        break;
                    case 2:
                        scortapietre[scelta]--;
                        npietre--;
                        tamagolem.addPietre(scelta);
                        break;
                    case 3:
                        scortapietre[scelta]--;
                        npietre--;
                        tamagolem.addPietre(scelta);
                        break;
                    case 4:
                        scortapietre[scelta]--;
                        npietre--;
                        tamagolem.addPietre(scelta);
                        break;
                    default:
                        System.out.println(ERRORE_INSERIRE_UN_VALORE_CONSONO);
                        break;

                }

            } while (npietre > 0);
        } else {
            return false;
        }
        return true;
    }
}