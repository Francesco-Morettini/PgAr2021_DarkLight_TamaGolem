import java.util.ArrayList;
import java.util.Scanner;

public class Giocatore {
    private int tamagolemEvocabili;
    private int tamagolemEliminati=0;
    private String username = new String();
    private Tamagolem tamagolem;
    public Giocatore(String username,int tamagolemEvocabili)
    {
        this.tamagolemEvocabili=tamagolemEvocabili;
        this.username=username;
    }
    public boolean evocazione(int vita, int scortapietre[],int pietreassegnabili)
    {
        int scelta;

        if(tamagolemEvocabili>0) {
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


            }while(pietreassegnabili>0)
        }
        else
            false;
    }
}
