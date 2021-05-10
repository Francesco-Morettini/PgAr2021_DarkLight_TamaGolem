public class Partita {
    private int equilibrio[][]= new int[5][5];
    public Partita(){
        generaEquilibrio(equilibrio);
    }
    public void generaEquilibrio(int equilibrio[][])
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
            return (int) (Math.random() * (10-sommariga)-1) + 1;

    }
}
