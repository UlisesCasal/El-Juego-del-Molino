package Modelo;

import java.io.Serializable;

public enum Eventos implements Serializable {
    FICHAAGREGADA,
    FICHASACADA,
    FICHAMOVIDA,
    FINPARTIDA,
    SACARFICHA,
    NOSACADA,
    FICHANOMOVIDA,//Cambiar a se produjo raya, por lo tanto cuando se produzca un raya debera de eliminar una ficha.
    SINFICHASPARAAGREGAR,
    INICIARPARTIDA,
    ESPERANDOJUGADORES,
    CAMBIODETURNO,
    FICHANOAGREGADA,
    SERIALIZADO,
    MENU,
    DESCONEXION

}
