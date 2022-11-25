package Modelo;

import java.util.List;

public class Molino {
    Ficha ficha1;
    Ficha ficha2;
    Ficha ficha3;

    public Molino(){
        this.ficha1 = null;
        this.ficha2 = null;
        this.ficha3 = null;
    }

    public void setMolino(List<Ficha> fichas){
        ficha1 = fichas.get(0);
        ficha2 = fichas.get(1);
        ficha3 = fichas.get(2);
    }

    public Ficha[] getFichas(){
        Ficha[] fichas = new Ficha[3];
        fichas[0] = ficha1;
        fichas[1] = ficha2;
        fichas[2] = ficha3;
        return fichas;
    }

    public boolean pertenece(Ficha ficha) {
        boolean salida = false;
        if (ficha.getPosicion() == ficha1.getPosicion()){
            salida = true;
        } else if (ficha.getPosicion() == ficha2.getPosicion()) {
            salida = true;
        } else if (ficha.getPosicion() == ficha3.getPosicion()) {
            salida = true;
        }
        return salida;
    }
}
