import java.util.Random;

public class Partita {

    private final int N = 5;
    private int equilibrio[][] = new int[N][N];





    public Partita() {
        //generaEquilibrio();
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

