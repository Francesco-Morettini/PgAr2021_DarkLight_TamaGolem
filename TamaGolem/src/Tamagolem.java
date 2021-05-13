import java.util.ArrayList;

public class Tamagolem {
    private ArrayList<Pietra> pietreSelezionate;
    private int vitaDisponibile;
    private Pietra pietra;

    public Tamagolem(int vita) {
        pietreSelezionate = new ArrayList<Pietra>();
        this.vitaDisponibile = vita; //set vita da parametro
    }

    public int getVita() {
        return vitaDisponibile;
    }

    public void setVita(int danno) {
        this.vitaDisponibile -= danno;
    }

    public void addPietre(int id, String nome) {
            pietreSelezionate.add(pietra = new Pietra(id,nome));

    }

    public int getIdPietra(){

        Pietra pietra = pietreSelezionate.get(pietreSelezionate.size()-1);
        pietreSelezionate.remove(pietreSelezionate.size()-1);
        pietreSelezionate.add(0,pietra);

        return pietra.getIndex();

    }

}
