package utils.collection;

/**
 * Representa una entrada (par clave-valor) en un mapa.
 * @param <Clave> El tipo de la clave.
 * @param <Valor> El tipo de valor.
 */
public class Entrada<Clave, Valor> {
    private Clave clave;
    private Valor valor;

    /**
     * Crea una nueva entrada con la clave y el valor especificados.
     * @param clave La clave de la entrada.
     * @param valor El valor asociado a la clave.
     */
    public Entrada(Clave clave, Valor valor) {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * Obtiene la clave de la entrada.
     * @return La clave de la entrada.
     */
    public Clave obtenerClave() {
        return clave;
    }

    /**
     * Obtiene el valor asociado a la clave.
     * @return El valor asociado a la clave.
     */
    public Valor obtenerValor() {
        return valor;
    }

    /**
     * Establece el valor asociado a la clave.
     * @param valor El nuevo valor a asociar a la clave.
     */
    public void establecerValor(Valor valor) {
        this.valor = valor;
    }
}
