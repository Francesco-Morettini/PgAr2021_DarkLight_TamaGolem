import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Partita {

    public enum Elemento { ARIA, TERRA, FUOCO, ACQUA, ETERE};

    private final String MSG_EVOCAZIONE_A = "Fase di evocazione GiocatoreA";
    private final String MSG_EVOCAZIONE_B = "Fase di evocazione GiocatoreB";
    private final String MSG_DANNO_A = "Il tamagolem del giocatoreA ha subito un danno di: ";
    private final String MSG_DANNO_B = "Il tamagolem del giocatoreB ha subito un danno di: ";
    private final String MSG_NO_DANNO = "Nessun danno subito: le due pietre erano delle stesso elemento!";

    private final int NUMERO_ELEMENTI = 5;
    private final int VITA = 10;

    private HashMap<Integer,String> mapElementi = new HashMap<Integer, String>();
    /*private int equilibrio[][] = {
            { 0, 0, 4, 0, 0 },
            { 2, 0, 0, 2, 0 },
            { 0, 3, 0, 0, 3 },
            { 1, 0, 2, 0, 0 },
            { 1, 1, 0, 1, 0 }
    };*/
    private int equilibrio[][] = new int[NUMERO_ELEMENTI][NUMERO_ELEMENTI];
    private Giocatore giocatoreA;
    private Giocatore giocatoreB;
    private int numeroGolem, numeroPietre, numeroPietreScorta, numeroPietrePerElemento;
    private int scortaPietre [] = new int [NUMERO_ELEMENTI];




    public Partita() {
        generaMap();
        generaEquilibrio();
        setNumeroPietre();
        setNumeroGolem();
        setNumeroPietreScorta();
        setNumeroPietrePerElemento();
        setScortaPietre();
        giocatoreA = new Giocatore(numeroGolem,VITA, numeroPietre, mapElementi);
        giocatoreB = new Giocatore(numeroGolem, VITA, numeroPietre, mapElementi);
    }

    public void generaMap(){

        Elemento elementi []= Elemento.values();

        for (int i=0; i<NUMERO_ELEMENTI; i++){
            mapElementi.put(i,elementi[i].toString());
        }

    }

    public void setNumeroPietre(){
        double k = (NUMERO_ELEMENTI+1)/3;
        this.numeroPietre = (int)(Math.ceil(k) + 1);
    }

    public void setNumeroGolem(){
        double k = (NUMERO_ELEMENTI-1)*(NUMERO_ELEMENTI-2);
        k = k/(2*numeroPietre);
        this.numeroGolem = (int)Math.ceil(k);
    }

    public void setNumeroPietreScorta(){
        double k = 2 * numeroGolem * numeroPietre;
        k = k/NUMERO_ELEMENTI;
        this.numeroPietreScorta = (int)(Math.ceil(k) * NUMERO_ELEMENTI);
    }

    public void setNumeroPietrePerElemento(){
        double k = 2*numeroGolem*numeroPietre;
        k = k/NUMERO_ELEMENTI;
        this.numeroPietrePerElemento = (int)Math.ceil(k);
    }

    public void setScortaPietre() {
        for (int i=0; i<NUMERO_ELEMENTI; i++){
            this.scortaPietre[i] = this.numeroPietrePerElemento;
        }
    }

    //Aggiungere metodo per decrementare scortapietre
    public String battaglia(){

        boolean evocazioneEffettuataA = false, evocazioneEffettuataB = false;

        switch (generaCasuale()){
            case 0:
                System.out.println(MSG_EVOCAZIONE_A);
                evocazioneEffettuataA = giocatoreA.evocazione(scortaPietre);
                System.out.println(MSG_EVOCAZIONE_B);
                evocazioneEffettuataB = giocatoreB.evocazione(scortaPietre);
                break;

            case 1:
                System.out.println(MSG_EVOCAZIONE_B);
                evocazioneEffettuataB = giocatoreB.evocazione(scortaPietre);
                System.out.println(MSG_EVOCAZIONE_A);
                evocazioneEffettuataA = giocatoreA.evocazione(scortaPietre);
                break;
        }

        while (evocazioneEffettuataA == true && evocazioneEffettuataB == true) {

            int i = giocatoreA.getTamagolem().getIdPietra();
            int j = giocatoreB.getTamagolem().getIdPietra();

            if (equilibrio[i][j] != 0){
                int f = equilibrio[i][j];//equilibrio[giocatoreA.getTamagolem().getIdPietra()][giocatoreB.getTamagolem().getIdPietra()];
                System.out.println(MSG_DANNO_B + equilibrio[i][j]);
                giocatoreB.getTamagolem().setVita(equilibrio[i][j]);
            }else{

                if (i == j){
                    System.out.println(MSG_NO_DANNO);
                }else{
                    System.out.println(MSG_DANNO_A + equilibrio[j][i]);
                    giocatoreA.getTamagolem().setVita(equilibrio[j][i]);
                }

            }

            if (giocatoreA.getTamagolem().getVita() <= 0){
                System.out.println("Il Tamagolem del giocatoreA è stato sconfitto!");
                if(giocatoreA.getTamagolemEvocabili()>0){
                    System.out.println(MSG_EVOCAZIONE_A);
                    evocazioneEffettuataA = giocatoreA.evocazione(scortaPietre);
                }else {
                    evocazioneEffettuataA = false;
                }
            }else if (giocatoreB.getTamagolem().getVita() <= 0){
                System.out.println("Il Tamagolem del giocatoreB è stato sconfitto!");
                if (giocatoreB.getTamagolemEvocabili()>0){
                    System.out.println(MSG_EVOCAZIONE_B);
                    evocazioneEffettuataB = giocatoreB.evocazione(scortaPietre);
                }else{
                    evocazioneEffettuataB = false;
                }

            }

        }

        if (evocazioneEffettuataA == false)
            return "giocatoreB";
        else
            return "giocatoreA";

    }

    public int generaCasuale(){

        Random r = new Random();
        return r.nextInt(2);

    }


    public void generaEquilibrio() {

        Random r = new Random();
        int cellesettate, cellesettabili, contatorezeri, sommariga;

        for(int i=0; i<NUMERO_ELEMENTI-2; i++) {

            cellesettate = 0;
            cellesettabili = 0;

            for (int j=i+1; j<NUMERO_ELEMENTI; j++) {
                cellesettabili ++;
            }
            cellesettabili--;

            for (int j=0; j<NUMERO_ELEMENTI; j++) {

                if (j>i){

                    getNumeroCasualerighe(i,j,cellesettate,contaZeriRiga(i),cellesettabili,r);
                    if (equilibrio[i][j]!=0)
                        cellesettate++;

                }

            }

            contatorezeri = contaZeriRiga(i);
            sommariga = calcolaSommaRiga(i);


            for(int j=i+1; j<NUMERO_ELEMENTI; j++) {
                if (contatorezeri>=1){
                    getNumeroCasualecolonna(j,i,contatorezeri,sommariga,r);
                    if (equilibrio[i][j] == 0 && i!=j){
                        contatorezeri--;
                    }
                }
            }

        }

        if (calcolaSommaColonna(NUMERO_ELEMENTI-1) < calcolaSommaRiga(NUMERO_ELEMENTI-1)){
            equilibrio[NUMERO_ELEMENTI-2][NUMERO_ELEMENTI-1] = calcolaSommaRiga(NUMERO_ELEMENTI-1) - calcolaSommaColonna(NUMERO_ELEMENTI-1);
        }else if (calcolaSommaRiga(NUMERO_ELEMENTI-1) < calcolaSommaColonna(NUMERO_ELEMENTI-1)){
            equilibrio[NUMERO_ELEMENTI-1][NUMERO_ELEMENTI-2] = calcolaSommaColonna(NUMERO_ELEMENTI-1) - calcolaSommaRiga(NUMERO_ELEMENTI-1);
        }


    }



    public void stampaEquilibrio(){

        for (int i=0; i < NUMERO_ELEMENTI; i++) {
            for (int j = 0; j < NUMERO_ELEMENTI; j++) {
                System.out.print(equilibrio[i][j]);

            }
            System.out.println("");
        }

    }




    public int contaZeriRiga(int riga){
        int contatorezeri=0;

        for (int k=riga+1; k<NUMERO_ELEMENTI; k++) {
            if (equilibrio[riga][k]==0)
                contatorezeri ++;
        }

        return contatorezeri;

    }




    public int calcolaSommaRiga(int riga){
        int sommariga = 0;
        for (int i=0; i<NUMERO_ELEMENTI; i++) {
            sommariga += equilibrio[riga][i];
        }
        return sommariga;
    }




    public int calcolaSommaColonna(int colonna){
        int sommacolonna = 0;
        for (int i=0; i<NUMERO_ELEMENTI; i++) {
            sommacolonna += equilibrio[i][colonna];
        }
        return sommacolonna;
    }




    public void getNumeroCasualerighe(int riga, int colonna, int cellesettate,int contatorezeri, int cellesettabili, Random r) {

        int sommariga, sommacolonna, numeroCasuale, sommaColonnaAttuale;

        sommacolonna = calcolaSommaColonna(riga);
        sommaColonnaAttuale = calcolaSommaColonna(riga+1);
        sommariga = calcolaSommaRiga(riga);

        if (sommariga<VITA && sommacolonna<VITA && sommaColonnaAttuale<VITA){

            if(cellesettate<cellesettabili){
                    if(VITA-Math.max(sommariga,sommacolonna)>0) {
                        numeroCasuale = r.nextInt(VITA - Math.max(Math.max(sommacolonna,sommariga),sommaColonnaAttuale));
                        if (colonna == NUMERO_ELEMENTI-1 && contatorezeri>cellesettabili) {
                            switch (numeroCasuale) {
                                case 0:
                                    equilibrio[riga][colonna] = 1;
                                    break;

                                default:
                                    if (sommariga < sommacolonna)
                                        equilibrio[riga][colonna] = sommacolonna + 1;
                                    else
                                        equilibrio[riga][colonna] = numeroCasuale;
                                    break;

                            }
                        }else{
                            if (colonna == NUMERO_ELEMENTI-2){
                                if (sommacolonna<=sommaColonnaAttuale){
                                    equilibrio[riga][colonna] = 0;
                                }
                            }else{
                                if (contatorezeri == 2){
                                    if (numeroCasuale < sommacolonna && sommariga<sommacolonna)
                                        equilibrio[riga][colonna] = sommacolonna;
                                    else
                                        equilibrio[riga][colonna] = numeroCasuale;
                               }else{
                                    if (numeroCasuale < sommacolonna && sommariga<sommacolonna)
                                        equilibrio[riga][colonna] = sommacolonna/contatorezeri;
                                    else
                                        equilibrio[riga][colonna] = numeroCasuale; }
                            }

                        }

                    }

            }

       }

    }





    public void getNumeroCasualecolonna(int riga, int colonna, int contazeri, int sommariga, Random r) {

        int sommacolonna, max, numeroCasuale;

        sommacolonna = calcolaSommaColonna(colonna);

        if (sommacolonna < VITA) {

            if (equilibrio[colonna][riga] == 0 && colonna!=riga) {

                switch (contazeri){
                    case 1:
                        equilibrio[riga][colonna] = sommariga - sommacolonna;
                        break;

                    default:
                        if(sommariga-sommacolonna>0) {
                            max = (sommariga-sommacolonna)/contazeri;
                            numeroCasuale = r.nextInt(max);
                            if (numeroCasuale == 0){
                                equilibrio[riga][colonna] = 1;
                            }else {
                                equilibrio[riga][colonna] = numeroCasuale;
                            }
                        }
                        break;

                }

            }

        }

    }


}

