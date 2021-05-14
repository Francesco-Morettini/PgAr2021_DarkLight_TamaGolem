import java.util.ArrayList;

public class Tamagolem {
    private ArrayList<Pietra> pietreSelezionate;
    private int vitaDisponibile;
    private Pietra pietra;

    public Tamagolem(int vita) {
        pietreSelezionate = new ArrayList<Pietra>();
        this.vitaDisponibile = vita; //set vita da parametro
    }

    /**
     * restituisce la vita disponibile del tamagolem
     * @return vitaDisponibile
     */
    public int getVita() {
        return vitaDisponibile;
    }

    /**
     * decrementa la vita del tamagolem in base al valore del parametro passato
     * @param danno
     */
    public void setVita(int danno) {
        this.vitaDisponibile -= danno;
    }

    /**
     * inserisce una pietra all'interno dell'arraylist pietra del golem
     * @param id
     * @param nome
     */
    public void addPietre(int id, String nome) {
            pietreSelezionate.add(pietra = new Pietra(id,nome));

    }

    /**
     * posiziona l'ultimo elemento dell'arraylist in prima posizione e ne restituisce l'id
     * @return index
     */
    public int getIdPietra(){

        Pietra pietra = pietreSelezionate.get(pietreSelezionate.size()-1);
        pietreSelezionate.remove(pietreSelezionate.size()-1);
        pietreSelezionate.add(0,pietra);

        return pietra.getIndex();

    }

}
