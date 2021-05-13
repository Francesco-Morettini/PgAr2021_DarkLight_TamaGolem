import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Partita {

    public enum Elemento { ARIA, TERRA, FUOCO, ACQUA, ETERE};

    private final int NUMERO_ELEMENTI = 5;
    private final int VITA = 10;


    private int equilibrio[][] = {
            { 0, 0, 4, 0, 0 },
            { 2, 0, 0, 2, 0 },
            { 0, 3, 0, 0, 3 },
            { 1, 0, 2, 0, 0 },
            { 1, 1, 0, 1, 0 }
    };
    private Giocatore giocatoreA;
    private Giocatore giocatoreB;
    private int numeroGolem, numeroPietre, numeroPietreScorta, numeroPietrePerElemento;
    private int scortaPietre [] = new int [NUMERO_ELEMENTI];




    public Partita() {
        //generaEquilibrio();
        giocatoreA = new Giocatore(numeroGolem,VITA, numeroPietre);
        giocatoreB = new Giocatore(numeroGolem, VITA, numeroPietre);
    }

    public void setNumeroPietre(){
        this.numeroPietre = Math.round(((NUMERO_ELEMENTI+1)/3)+1);
    }

    public void setNumeroGolem(){
        this.numeroGolem = Math.round(((NUMERO_ELEMENTI-1)*(NUMERO_ELEMENTI-2))/(2*numeroPietre));
    }

    public void setNumeroPietreScorta(){
        this.numeroPietreScorta = Math.round(((2*numeroGolem*numeroPietre)/NUMERO_ELEMENTI)*NUMERO_ELEMENTI);
    }

    public void setNumeroPietrePerElemento(){
        this.numeroPietrePerElemento = Math.round(((2*numeroGolem*numeroPietre)/NUMERO_ELEMENTI));
    }

    public void setScortaPietre() {
        for (int i=0; i<NUMERO_ELEMENTI; i++){
            this.scortaPietre[i] = this.numeroPietrePerElemento;
        }
    }

    public Giocatore battaglia(){

        boolean evocazioneEffettuataA = false, evocazioneEffettuataB = false;
        InputDati input = new InputDati();

        switch (generaCasuale()){
            case 0:
                System.out.println("Fase di evocazione GiocatoreA");
                evocazioneEffettuataA = giocatoreA.evocazione(scortaPietre);
                System.out.println("Fase di evocazione GiocatoreB");
                evocazioneEffettuataB = giocatoreB.evocazione(scortaPietre);
                break;

            case 1:
                System.out.println("Fase di evocazione GiocatoreB");
                evocazioneEffettuataB = giocatoreB.evocazione(scortaPietre);
                System.out.println("Fase di evocazione GiocatoreA");
                evocazioneEffettuataA = giocatoreA.evocazione(scortaPietre);
                break;
        }

        while (evocazioneEffettuataA == true && evocazioneEffettuataB == true) {

            if (equilibrio[giocatoreA.getTamagolem().getIdPietra()][giocatoreB.getTamagolem().getIdPietra()] != 0){
                System.out.println("Il tamagolem del giocatoreB ha subito un danno di: " + equilibrio[giocatoreA.getTamagolem().getIdPietra()][giocatoreB.getTamagolem().getIdPietra()]);
                giocatoreB.getTamagolem().setVita(equilibrio[giocatoreA.getTamagolem().getIdPietra()][giocatoreB.getTamagolem().getIdPietra()]);
            }else{
                if (giocatoreA.getTamagolem().getIdPietra()==giocatoreB.getTamagolem().getIdPietra()){
                    System.out.println("Nessun danno subito: le due pietre erano delle stesso elemento");
                }else{
                    giocatoreA.getTamagolem().setVita(equilibrio[giocatoreB.getTamagolem().getIdPietra()][giocatoreA.getTamagolem().getIdPietra()]);
                }
            }

            if (giocatoreA.getTamagolem().getVita() <= 0){
                evocazioneEffettuataA = giocatoreA.evocazione(scortaPietre);
            }else if (giocatoreB.getTamagolem().getVita() <= 0){
                evocazioneEffettuataB = giocatoreB.evocazione(scortaPietre);
            }

        }

        if (evocazioneEffettuataA == false)
            return giocatoreA;
        else
            return giocatoreB;

    }

    public int generaCasuale(){

        Random r = new Random();
        return r.nextInt(1);

    }

    /*
    public void generaEquilibrio() {

        Random r = new Random();
        int cellesettate, cellesettabili, contatorezeri, sommariga;

        for(int i=0; i<3; i++) {

            cellesettate = 0;
            cellesettabili = 0;

            for (int j=i+1; j<5; j++) {
                cellesettabili ++;
            }
            cellesettabili--;

            for (int j=0; j<5; j++) {

                if (j>i){

                    getNumeroCasualerighe(i,j,cellesettate,contaZeriRiga(i),cellesettabili,r);
                    if (equilibrio[i][j]!=0)
                        cellesettate++;

                }

            }

            contatorezeri = contaZeriRiga(i);
            sommariga = calcolaSommaRiga(i);


            for(int j=i+1; j<5; j++) {
                if (contatorezeri>=1){
                    getNumeroCasualecolonna(j,i,contatorezeri,sommariga,r);
                    if (equilibrio[i][j] == 0 && i!=j){
                        contatorezeri--;
                    }
                    if (contatorezeri>1){
                        sommariga -= equilibrio[j][i];
                    }
                }
            }

        }

    }
*/

/*
    public void stampaEquilibrio(){

        for (int i=0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(equilibrio[i][j]);

            }
            System.out.println("");
        }

    }
*/


/*
    public int contaZeriRiga(int riga){
        int contatorezeri=0;

        for (int k=riga+1; k<5; k++) {
            if (equilibrio[riga][k]==0)
                contatorezeri ++;
        }

        return contatorezeri;

    }
*/


/*
    public int calcolaSommaRiga(int riga){
        int sommariga = 0;
        for (int i=0; i<5; i++) {
            sommariga += equilibrio[riga][i];
        }
        return sommariga;
    }
*/


/*
    public int calcolaSommaColonna(int colonna){
        int sommacolonna = 0;
        for (int i=0; i<5; i++) {
            sommacolonna += equilibrio[i][colonna];
        }
        return sommacolonna;
    }
*/


/*
    public void getNumeroCasualerighe(int riga, int colonna, int cellesettate,int contatorezeri, int cellesettabili, Random r) {

        int sommariga, sommacolonna, numeroCasuale;

        sommacolonna = calcolaSommaColonna(riga);
        sommariga = calcolaSommaRiga(riga);

        if (sommariga<10 && sommacolonna<10){

            if(cellesettate<cellesettabili){
                    if(10-Math.max(sommariga,sommacolonna)>0) {
                        //genera numero casuale maggiore di 0
                        numeroCasuale = r.nextInt(10 - Math.max(sommariga, sommacolonna));
                        //n-1
                        if (colonna == 4 && contatorezeri>cellesettabili) {
                            switch (numeroCasuale) {
                                case 0:
                                    equilibrio[riga][colonna] = 1;
                                    break;

                                default:
                                    if (numeroCasuale < sommacolonna)
                                        equilibrio[riga][colonna] = sommacolonna;
                                    else
                                        equilibrio[riga][colonna] = numeroCasuale;
                                    break;

                            }
                        }else{
                                if (numeroCasuale < sommacolonna)
                                    equilibrio[riga][colonna] = sommacolonna;
                                else
                                    equilibrio[riga][colonna] = numeroCasuale;
                            }

                    }

            }

       }

    }
*/



/*
    public void getNumeroCasualecolonna(int riga, int colonna, int contazeri, int sommariga, Random r) {

        int sommacolonna;

        sommacolonna = calcolaSommaColonna(colonna);

        if (sommacolonna < 10) {

            if (equilibrio[colonna][riga] == 0 && colonna!=riga) {

                switch (contazeri){
                    case 1:
                        equilibrio[riga][colonna]=sommariga - sommacolonna;
                        break;

                    default:
                        if(sommariga-sommacolonna>0) {
                            equilibrio[riga][colonna] = r.nextInt(sommariga - sommacolonna) - contazeri;
                        }
                        break;

                }

            }

        }

    }
*/

}

