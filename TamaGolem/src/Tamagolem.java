import java.util.ArrayList;

public class Tamagolem {
    private ArrayList<Pietra> pietreSelezionate;
    private int vitaDisponibile;
    private Pietra pietra;
    public Tamagolem(int vita) {
        pietreSelezionate = new ArrayList<Pietra>();
        this.vitaDisponibile = vita; //set vita da parametro
    }

    public int getVitaDisponibile() {
        return vitaDisponibile;
    }

    public void setVitaDisponibile(int danno) {
        vitaDisponibile = vitaDisponibile - danno;
    }
    public void addPietre(int elemento)
    {
        if(elemento==0)
        {
            pietreSelezionate.add(pietra= new Pietra(elemento,"ACQUA"));
        }
        if(elemento==1)
        {
            pietreSelezionate.add(pietra= new Pietra(elemento,"FUOCO"));
        }
        if(elemento==2)
        {
            pietreSelezionate.add(pietra= new Pietra(elemento,"ARIA"));
        }
        if(elemento==3)
        {
            pietreSelezionate.add(pietra= new Pietra(elemento,"TERRA"));
        }
        if(elemento==4)
        {
            pietreSelezionate.add(pietra= new Pietra(elemento,"PSICHE"));
        }


    }

}
