import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.Scanner;

public class Giocatore {
    private int tamagolemEvocabili;
    private int tamagolemEliminati = 0;
    private Tamagolem tamagolem;
    private int vita;
    private int pietreassegnabili;

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
                System.out.println(" scegli la pietra da assegnare al tamagolem:");
                if (scortapietre[0] > 0)
                    System.out.println("Premi  per 0 per acqua : pietre disponibili -> "+scortapietre[0]);
                if (scortapietre[1] > 0)
                    System.out.println("Premi  per 1 per fuoco : pietre disponibili ->  "+scortapietre[1]);
                if (scortapietre[2] > 0)
                    System.out.println("Premi  per 2 per aria : pietre disponibili -> "+scortapietre[2]);
                if (scortapietre[3] > 0)
                    System.out.println("Premi  per 3 per terra : pietre disponibili -> "+scortapietre[3]);
                if (scortapietre[4] > 0)
                    System.out.println("Premi  per 4 per psiche : pietre disponibili -> "+scortapietre[4]);
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
                        System.out.println("errore, inserire un valore consono");
                        break;

                }

            } while (npietre > 0);
        } else {
            return false;
        }
        return true;
    }
}