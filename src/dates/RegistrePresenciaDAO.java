package dates;

import java.util.ArrayList;

public class RegistrePresenciaDAO {
    private ArrayList registre = new ArrayList<Presencia>();

    public ArrayList<Presencia> getMap() {
        return this.registre;
    }

    public void addStart(Presencia p) {
        registre.add(p);
    }

    public void addEnd(Presencia p) {
        for (int i = 0; i < registre.size(); i++) {
            Presencia pr = (Presencia) registre.get(i);
            if (pr.getIdtreballador() == p.getIdtreballador() && pr.getHoraEntrada() == p.getHoraEntrada()) {
                registre.remove(registre.get(i));
                pr.setHoraSortida(p.getHoraSortida());
                registre.add(pr);
            }    
        }
    }

    public Presencia get(Presencia p) {
        for (int i = 0; i < registre.size(); i++) {
            Presencia pr = (Presencia) registre.get(i);
            if (pr.getIdtreballador() == p.getIdtreballador() && pr.getHoraEntrada() == p.getHoraEntrada()) {
                return pr;
            }    
        }
        return null;
    }
}
