public class Pietra {
    private int index;
    private String nome;

    public Pietra(int index,String nome)
    {
        this.index=index;
        this.nome=nome;
    }

    /**
     * restituisce l'id della pietra
     * @return index
     */
    public int getIndex(){
        return index;
    }


}
