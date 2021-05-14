import java.util.HashMap;
import java.util.Random;

public class Partita {

    public enum Elemento { ARIA, TERRA, FUOCO, ACQUA, ETERE}; //enum contenente gli elementi

    private final String MSG_EVOCAZIONE_A = "Fase di evocazione GiocatoreA";
    private final String MSG_EVOCAZIONE_B = "Fase di evocazione GiocatoreB";
    private final String MSG_DANNO_A = "Il tamagolem del giocatoreA ha subito un danno di: ";
    private final String MSG_DANNO_B = "Il tamagolem del giocatoreB ha subito un danno di: ";
    private final String MSG_NO_DANNO = "Nessun danno subito: le due pietre erano delle stesso elemento!";

    private final int NUMERO_ELEMENTI = 5; //numero di elementi
    private final int VITA = 10; //vita massima dei tamagolem

    private HashMap<Integer,String> mapElementi = new HashMap<Integer, String>(); //Map contenente come valori i nomi degli elementi e come key il loro indice di posizione

    /*

    //matrice utilizzata come test del programma prima di aver implementato il codice che genera la matrice equilibrio
    private int equilibrio[][] = {
            { 0, 0, 4, 0, 0 },
            { 2, 0, 0, 2, 0 },
            { 0, 3, 0, 0, 3 },
            { 1, 0, 2, 0, 0 },
            { 1, 1, 0, 1, 0 }
    };

    */

    private Giocatore giocatoreA; //oggetto di classe Giocatore
    private Giocatore giocatoreB; //oggetto di classe Giocatore
    private int numeroGolem, numeroPietre, numeroPietreScorta, numeroPietrePerElemento;
    private int equilibrio[][] = new int[NUMERO_ELEMENTI][NUMERO_ELEMENTI];
    private int scortaPietre [] = new int [NUMERO_ELEMENTI];


    /**
     * Costruttore: <br>richiama i metodi necessari per settare:
     * <ul>
     *     <li>mapElementi</li>
     *     <li>equilibrio</li>
     *     <li>numeroPietre</li>
     *     <li>numeroGolem</li>
     *     <li>numeroPietreScorta</li>
     *     <li>numeroPietrePerElemento</li>
     *     <li>scortaPietre</li>
     * </ul>
     * Crea oggetti giocatoreA e giocatoreB.
     */
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


    /**
     * Genera HashMap mapElementi prendendo tutti i valori dell'enum Elemento
     * impostando come key i loro indici di posizione e come valori i nomi degli elementi.
     */
    public void generaMap(){

        Elemento elementi []= Elemento.values();

        for (int i=0; i<NUMERO_ELEMENTI; i++){
            mapElementi.put(i,elementi[i].toString());
        }

    }


    /**
     * Setta il valore di numeroPietre --> numero di pietre selezionabili durante l'evocazione.
     */
    public void setNumeroPietre(){
        double k = (NUMERO_ELEMENTI+1)/3;
        this.numeroPietre = (int)(Math.ceil(k) + 1);
    }


    /**
     * Setta il valore di numeroGolem --> numero di tamagolem a disposizione per giocatore.
     */
    public void setNumeroGolem(){
        double k = (NUMERO_ELEMENTI-1)*(NUMERO_ELEMENTI-2);
        k = k/(2*numeroPietre);
        this.numeroGolem = (int)Math.ceil(k);
    }


    /**
     * setta il valore di numeroPietreScorta --> numero totale di pietre presenti nella scorta comune.
     */
    public void setNumeroPietreScorta(){
        double k = 2 * numeroGolem * numeroPietre;
        k = k/NUMERO_ELEMENTI;
        this.numeroPietreScorta = (int)(Math.ceil(k) * NUMERO_ELEMENTI);
    }


    /**
     * setta il valore di numeroPietrePerElemento --> numero di pietre per ogni elemento presenti nella scorta.
     */
    public void setNumeroPietrePerElemento(){
        double k = 2*numeroGolem*numeroPietre;
        k = k/NUMERO_ELEMENTI;
        this.numeroPietrePerElemento = (int)Math.ceil(k);
    }


    /**
     * setta la matrice scortaPietre di dimensione pari al numero di elementi e contenente per ogni cella
     * il numero di pietre nella scorta comune associate al determinato elemento (corrispondenza indice di posizione elemento e numero cella).
     */
    public void setScortaPietre() {
        for (int i=0; i<NUMERO_ELEMENTI; i++){
            this.scortaPietre[i] = this.numeroPietrePerElemento;
        }
    }


    /**
     * Gestisce le fasi di evocazione e lo scontro tra tamagolem.<br><br>
     * Inizialmente verrà effettuata la fase di evocazione per entrambi i giocatori gestita da uno switch
     * (il giocatore che evocherà per primo sarà determinato da un metodo che genererà un numero casuale: se 0 partirà giocatoreA, se 1 partirà giocatore B).<br><br>
     *
     * Successivamente ci sarà la fase di scontro tra i tamagolem evocati: il calcolo danni viene gestito controllando i valori nella matrice di equilibrio
     * nelle celle di indice corrispndenti all'id delle pietre utilizzate dai tamagolem.<br>
     * Ogni volta che un tamagolem terminerà la vita, il giocatore associato effettuerà una nuova evocazione finchè avrà tamagolem a disposizione.<br>
     * Questa fase sarà ripetuta finchè il metodo evocazione di uno dei due giocatori non restituirà false (significa che non ci sono più tamagolem evocabili).
     *
     * @return String --> nome del giocatore vincitore.
     */
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


    /**
     * genera un numero casuale tra 0 e 1 per decidere quale giocatore inizierà ad evocare.
     *
     * @return intero --> 0 o 1
     */
    public int generaCasuale(){

        Random r = new Random();
        return r.nextInt(2);

    }


    /**
     * Genera la matrice di equilibrio seguendo il procedimento:<br>
     * genero riga 0 --> genero colonna 0<br>
     * genero riga 1 --> genero colonna 1<br>
     * genero riga 2 --> genero colonna 2<br>
     * ....<br>
     * genero riga numeroElementi-2 --> genero colonna numeroElementi-2<br><br>
     *
     * Per concludere imposto cella [n-2][n-1] o [n-1][n-2] a seconda del valore che mi manca per terminare il bilanciamento.
     *
     */
    public void generaEquilibrio() {

        Random r = new Random();
        int cellesettate, cellesettabili, contatorezeri, sommariga;

        for(int i=0; i<NUMERO_ELEMENTI-2; i++) {

            cellesettate = 0;
            cellesettabili = 0;

            //conto le celle settabili per ogni riga in modo da controllare che almeno una cella in ogni riga rimanga sempre vuota
            for (int j=i+1; j<NUMERO_ELEMENTI; j++) {
                cellesettabili ++;
            }
            cellesettabili--;

            //per ogni colonna j della riga i genero un numero casuale
            for (int j=0; j<NUMERO_ELEMENTI; j++) {

                if (j>i){//parto dalla colonna successiva ad i in modo che in j=i rimanga il valore 0 dato che un elemento non ha interazioni con se stesso

                    getNumeroCasualerighe(i,j,cellesettate,contaZeriRiga(i),cellesettabili,r);
                    if (equilibrio[i][j]!=0)//incremento il contatore delle celle settate fino ad ora se è stato inserito un numero
                        cellesettate++;

                }

            }

            contatorezeri = contaZeriRiga(i);
            sommariga = calcolaSommaRiga(i);

            //per ogni riga j della colonna i genero un numero casuale
            for(int j=i+1; j<NUMERO_ELEMENTI; j++) {
                if (contatorezeri>=1){
                    getNumeroCasualecolonna(j,i,contatorezeri,sommariga,r);
                    if (equilibrio[i][j] == 0 && i!=j){
                        contatorezeri--;
                    }
                }
            }

        }

        //calcolo la somma dell'ultima riga e dell'ultima colonna inserendo il numero opportuno in posizione [NUMERO_ELEMENTI-2][NUMERO_ELEMENTI-1]
        // o [NUMERO_ELEMENTI-1][NUMERO_ELEMENTI-2] per bilanciare la matrice equilibrio
        if (calcolaSommaColonna(NUMERO_ELEMENTI-1) < calcolaSommaRiga(NUMERO_ELEMENTI-1)){
            equilibrio[NUMERO_ELEMENTI-2][NUMERO_ELEMENTI-1] = calcolaSommaRiga(NUMERO_ELEMENTI-1) - calcolaSommaColonna(NUMERO_ELEMENTI-1);
        }else if (calcolaSommaRiga(NUMERO_ELEMENTI-1) < calcolaSommaColonna(NUMERO_ELEMENTI-1)){
            equilibrio[NUMERO_ELEMENTI-1][NUMERO_ELEMENTI-2] = calcolaSommaColonna(NUMERO_ELEMENTI-1) - calcolaSommaRiga(NUMERO_ELEMENTI-1);
        }


    }


    /**
     * stampa la matrice equilibrio.
     */
    public void stampaEquilibrio(){

        for (int i=0; i < NUMERO_ELEMENTI; i++) {
            for (int j = 0; j < NUMERO_ELEMENTI; j++) {
                System.out.print(equilibrio[i][j]);

            }
            System.out.println("");
        }

    }


    /**
     * Conta il numero di zeri presenti su una riga della matrice equilibrio passata per parametro partendo dalla colonna [riga+1]
     * @param riga
     * @return int --> contatorezeri
     */
    public int contaZeriRiga(int riga){
        int contatorezeri=0;

        for (int k=riga+1; k<NUMERO_ELEMENTI; k++) {
            if (equilibrio[riga][k]==0)
                contatorezeri ++;
        }

        return contatorezeri;

    }


    /**
     * Calcola la somma della riga passata per parametro della matrice equilibrio.
     * @param riga
     * @return int --> sommariga
     */
    public int calcolaSommaRiga(int riga){
        int sommariga = 0;
        for (int i=0; i<NUMERO_ELEMENTI; i++) {
            sommariga += equilibrio[riga][i];
        }
        return sommariga;
    }


    /**
     * Calcola la somma della colonna passata per parametro della matrice equilibrio.
     * @param colonna
     * @return int --> sommacolonna
     */
    public int calcolaSommaColonna(int colonna){
        int sommacolonna = 0;
        for (int i=0; i<NUMERO_ELEMENTI; i++) {
            sommacolonna += equilibrio[i][colonna];
        }
        return sommacolonna;
    }


    /**
     * Genera un numero casuale da inserire nella riga in posizione [riga][colonna] passate per parametro.
     * @param riga
     * @param colonna
     * @param cellesettate
     * @param contatorezeri
     * @param cellesettabili
     * @param r
     */
    public void getNumeroCasualerighe(int riga, int colonna, int cellesettate,int contatorezeri, int cellesettabili, Random r) {

        int sommariga, sommacolonna, numeroCasuale, sommaColonnaAttuale;

        sommacolonna = calcolaSommaColonna(riga);
        sommaColonnaAttuale = calcolaSommaColonna(riga+1); //calcola la somma della colonna dove andrà inserito il nuovo numero
        sommariga = calcolaSommaRiga(riga);

        //controllo che le somme non superino il valore VITA
        if (sommariga<VITA && sommacolonna<VITA && sommaColonnaAttuale<VITA){

            if(cellesettate<cellesettabili){
                    if(VITA-Math.max(sommariga,sommacolonna)>0) {
                        numeroCasuale = r.nextInt(VITA - Math.max(Math.max(sommacolonna,sommariga),sommaColonnaAttuale));
                        //controllo che sia stato aggiunto almeno un numero diverso da 0
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


    /**
     * Genera un numero casuale da inserire nella colonna in posizione [riga][colonna] passate per parametro).
     * @param riga
     * @param colonna
     * @param contazeri
     * @param sommariga
     * @param r
     */
    public void getNumeroCasualecolonna(int riga, int colonna, int contazeri, int sommariga, Random r) {

        int sommacolonna, max, numeroCasuale;

        sommacolonna = calcolaSommaColonna(colonna);

        if (sommacolonna < VITA) {

            //controllo che sia 0 il valore nella cella corrispondente ma sulla riga e che non sia la cella di interazione dello stesso elemento
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

