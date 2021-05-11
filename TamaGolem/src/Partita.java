import java.util.Random;

public class Partita {
    private int equilibrio[][] = new int[5][5];
    private int cellesettate=0;
    private Random r= new Random();

    public Partita() {
        generaEquilibrio();
    }

    private void generaEquilibrio() {
        int i ;

    for(i=0;i<5;i++) {

            for (int j =0; j < 5; j++) {
                if(j>i)
                getNumeroCasualerighe(i, j);
            }
            cellesettate=0;
            // setto la prima colonna
            //prima era 1 la j
            for (int j =0 ; j < 5; j++) {

                getNumeroCasualecolonna(i);
            }
    }

        //stampa array
        for (i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(equilibrio[i][j]);

            }
            System.out.println("");
        }
    }

    private void getNumeroCasualerighe(int riga,int colonna) {
        int sommariga = 0,sommacolonna=0;
        for (int j = 0; j < 5; j++) {
            sommacolonna += equilibrio[j][riga];
        }
        for (int j = 0; j < 5; j++)
            sommariga += equilibrio[riga][j];

            if (sommariga <10 && (cellesettate < 3 - riga || cellesettate==0) && sommacolonna<10 )//cellesettate (cellesettate < 3 - riga || cellesettate=0)
            {

                if(10-Math.max(sommariga,sommacolonna)>0) {
                    cellesettate++;

                    equilibrio[riga][colonna] = r.nextInt(10 - Math.max(sommariga, sommacolonna));
                }
            }

    }

    private void getNumeroCasualecolonna(int colonna) {
        int sommariga = 0, sommacolonna = 0, contatorezeri = -1;
        for (int j = 0; j < 5; j++) {
            sommariga += equilibrio[colonna][j];
        }
        for (int j = 0; j < 5; j++) {
            sommacolonna += equilibrio[j][colonna];
        }
        for (int j = 0; j < 5; j++) {
            if (equilibrio[colonna][j] == 0 ) {
                contatorezeri++;
            }
        }
            if (sommacolonna < 10  ) {
                for (int j = 1; j < 5; j++) {
                    if (equilibrio[colonna][j] == 0 && j!=colonna) {
                        switch (contatorezeri)
                        {
                            case 1:
                                equilibrio[j][colonna]=sommariga;
                                break;
                            default:
                                if(sommariga-sommacolonna>0) {
                                    equilibrio[j][colonna] = r.nextInt(sommariga - sommacolonna) + 1;
                                    contatorezeri--;
                                    sommariga -= equilibrio[j][colonna];
                                }
                                break;

                        }

                    }

                }
            }

        }
    }

