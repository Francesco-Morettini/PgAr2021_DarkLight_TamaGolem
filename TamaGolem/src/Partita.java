public class Partita {
    private int equilibrio[][] = new int[5][5];
    private int cellesettate = 0;

    public Partita() {
        generaEquilibrio();
    }

    /*public void generaEquilibrio()
    {

        for(int i=0;i<5;i++)
            for(int j=0;j<5;j++)
            {
                if(j>i) {
                    equilibrio[i][j] = numeriCasualiGiusti(i,j);

                }
            }

        // stampa matrice
        for(int i=0;i<5;i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(equilibrio[i][j]);

            }
            System.out.println("");
        }



    }
    private int numeriCasualiGiusti(int riga,int colonna){
        int sommariga=0 ,sommacolonna=0;

        for(int j=0;j<5;j++)
            sommariga+=equilibrio[riga][j];

        for(int i=0;i<5;i++)
            sommacolonna+=equilibrio[i][colonna];

        if(sommariga>=10 || sommacolonna>=10)
            return 0;
        else
            return (int) (Math.random() * (9-sommariga)-1) + 1;

    }*/
    private void generaEquilibrio() {
        int i ;
        //setto la prima riga
    for(i=0;i<5;i++) {

            for (int j = 1; j < 5; j++) {
                if(j>i)
                getNumeroCasualerighe(i, j);
            }
            cellesettate = 0;
            // setto la prima colonna

            for (int j = 1; j < 5; j++) {

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
        int somma = 0;

        for (int j = 0; j < 5; j++)
            somma += equilibrio[riga][j];

            if (somma <10 && cellesettate <= 3 - riga)
            {
                cellesettate++;
                equilibrio[riga][colonna]= (int) (Math.random() * (10 - somma)) + 0;
            }

    }

    private void getNumeroCasualecolonna(int colonna) {
        int sommariga = 0, sommacolonna = 0, contatorezeri = 0;
        for (int j = 1; j < 5; j++) {
            sommariga += equilibrio[colonna][j];
        }
        for (int j = 1; j < 5; j++) {
            sommacolonna += equilibrio[j][colonna];
        }
        for (int j = 1; j < 5; j++) {
            if (equilibrio[colonna][j] == 0) {
                contatorezeri++;
            }
        }
            if (sommacolonna < 10) {
                for (int j = 1; j < 5; j++) {
                    if (equilibrio[colonna][j] == 0) {
                        switch (contatorezeri)
                        {
                            case 1:
                                equilibrio[j][colonna]=sommariga;
                                break;
                            default:
                                equilibrio[j][colonna]=(int) (Math.random() * (sommariga - sommacolonna)) + 1;
                                contatorezeri--;
                                sommariga-=equilibrio[j][colonna];
                                break;
                        }

                    }

                }
            }

        }
    }

