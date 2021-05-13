import java.util.ArrayList;

public class Tamagolem {
    private ArrayList<String> pietreSelezionate;
    private int vitaDisponibile;

    public Tamagolem(int vita) {
        pietreSelezionate = new ArrayList<String>();
        this.vitaDisponibile = vita; //set vita da parametro
    }

    public int getVitaDisponibile() {
        return vitaDisponibile;
    }

    public void setVitaDisponibile(int danno) {
        vitaDisponibile = vitaDisponibile - danno;
    }


}
