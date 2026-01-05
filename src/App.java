/**
 * Clase que resuelve el problema de las N-reinas mediante backtracking.
 * El problema consiste en colocar N reinas en un tablero de NxN sin que
 * se ataquen entre sí (ni en la misma fila, columna o diagonal).
 * 
 * @author M. Paz Jimenez Martin
 * @version 1.0
 */

public class App {

	/**
     * Método principal que solicita al usuario el tamaño del tablero
     * y ejecuta el algoritmo para encontrar todas las soluciones posibles.
     * 
     * @param args argumentos de la línea de comandos (no utilizados)
     * @throws Exception si ocurre un error durante la lectura de la entrada
     */
    public static void main(String[] args) throws Exception {
		int altura = 0;
		boolean correcto = false;
		do {
			try {
				System.out.print("Introduzca la altura del tablero (mínimo 4): ");
				altura = Integer.parseInt(System.console().readLine());
				correcto = altura >= 4;
			} catch (NumberFormatException e) {
				System.out.println("El número debe ser un entero.");
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error inesperado.");
			}
		} while (!correcto);
		int[] posiciones = new int[altura];
		posiciones = inicializaTablero(posiciones, -1);
		calculaPosiciones(posiciones, altura, 0);
    }

	/**
     * Calcula todas las posiciones válidas para colocar N reinas en un tablero
     * de NxN utilizando el algoritmo de backtracking.
     * 
     * El algoritmo explora recursivamente cada fila del tablero, intentando
     * colocar una reina en cada columna. Si encuentra una posición válida,
     * avanza a la siguiente fila. Si no encuentra ninguna posición válida,
     * retrocede (backtracking) a la fila anterior para probar otras opciones.
     * 
     * @param posiciones array que almacena la columna en la que está colocada
     *                   la reina para cada fila. El índice representa la fila
     *                   y el valor representa la columna.
     * @param altura tamaño del tablero (NxN)
     * @param fila fila actual en la que se intenta colocar una reina
     */
	public static void calculaPosiciones(int[] posiciones, int altura, int fila) {
		if (fila == altura) {
			muestraPosiciones(posiciones);
			return;
		}
		for (int col = 0; col < altura; col++) {
			if (esValida(posiciones, fila, col)) {
				posiciones[fila] = col; 
				calculaPosiciones(posiciones, altura, fila + 1);
				posiciones[fila] = -1;
			}
		}
	}

	/**
     * Verifica si es válido colocar una reina en la posición (fila, col)
     * sin que entre en conflicto con las reinas ya colocadas en filas anteriores.
     * 
     * Comprueba:
     * - Que no haya otra reina en la misma columna
     * - Que no haya otra reina en las diagonales
     * 
     * @param posiciones array con las posiciones actuales de las reinas
     * @param fila fila en la que se quiere colocar la nueva reina
     * @param col columna en la que se quiere colocar la nueva reina
     * @return true si la posición es válida, false en caso contrario
     */
	public static boolean esValida(int[] posiciones, int fila, int col) {
		for (int pos = 0; pos < fila; pos++) {
			if (posiciones[pos] == col)
				return false;
			if (Math.abs(fila - pos) == Math.abs(col - posiciones[pos]))
				return false;
		}
		return true;
	}

	/**
     * Inicializa un array con un valor específico en todas sus posiciones.
     * 
     * @param tablero array a inicializar
     * @param valor valor con el que se inicializará cada posición del array
     * @return el array inicializado
     */
	public static int[] inicializaTablero(int[] tablero, int valor) {
		for (int i = 0; i < tablero.length; i++)
			tablero[i] = valor;
		return tablero;
	}

	/**
     * Muestra por consola una solución válida del problema de las N-reinas.
     * Imprime un array donde cada índice representa una fila y cada valor
     * representa la columna en la que está colocada la reina en esa fila.
     * 
     * Ejemplo: [1, 3, 0, 2] significa:
     * - Fila 0: reina en columna 1
     * - Fila 1: reina en columna 3
     * - Fila 2: reina en columna 0
     * - Fila 3: reina en columna 2
     * 
     * @param posiciones array con las posiciones de las reinas que forman
     *                   una solución válida
     */
	public static void muestraPosiciones(int[] posiciones) {
		System.out.print( "[");
		for (int i = 0; i < posiciones.length; i++)
			System.out.printf("%d%s", posiciones[i], i < posiciones.length - 1 ? ", " : "]");
		System.out.println();
	}
}
