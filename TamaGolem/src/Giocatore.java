import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.Scanner;

public class Giocatore {
    private int tamagolemEvocabili;
    private int tamagolemEliminati = 0;
    private Tamagolem tamagolem;

    public Giocatore(int tamagolemEvocabili) {
        this.tamagolemEvocabili = tamagolemEvocabili;
    }

    public boolean evocazione(int vita, int scortapietre[], int pietreassegnabili) {
        int scelta;
        InputDati scanner = new InputDati();
        if (tamagolemEvocabili > 0) {
            do {
                System.out.println("Benvenuto scegli la pietra da assegnare al tamagolem:");
                if (scortapietre[0] > 0)
                    System.out.println("Premi  per 0 per acqua");
                if (scortapietre[1] > 0)
                    System.out.println("Premi  per 1 per fuoco");
                if (scortapietre[2] > 0)
                    System.out.println("Premi  per 2 per aria");
                if (scortapietre[3] > 0)
                    System.out.println("Premi  per 3 per terra");
                if (scortapietre[4] > 0)
                    System.out.println("Premi  per 4 per psiche");
             //   scelta= scanner.

            } while (pietreassegnabili > 0);
        } else {
            return false;
        }
        return true;
    }
}