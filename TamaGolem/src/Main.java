import UniBSFpLib.src.it.unibs.fp.mylib.InputDati;

public class Main {

    public static void main(String[] args) {

        String vincitore;
        int scelta;
        InputDati input = new InputDati();
        Partita p= new Partita();

        //scelta = input.leggiIntero("Inserire 0 per iniziare una nuova partita!",0,0);

        //while (scelta == 0){

            //vincitore = p.battaglia();

            //System.out.println("Il vincitore è: " + vincitore);
            //System.out.println("L'equilibrio della partita era:");
            p.stampaEquilibrio();

            //scelta = input.leggiIntero("Inserire 0 per iniziare una nuova partita!\nInserire 1 per uscire!\n",0,1);

        //}


    }

}
