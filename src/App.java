public class App {
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

	public static boolean esValida(int[] posiciones, int fila, int col) {
		for (int pos = 0; pos < fila; pos++) {
			if (posiciones[pos] == col)
				return false;
			if (Math.abs(fila - pos) == Math.abs(col - posiciones[pos]))
				return false;
		}
		return true;
	}

	public static int[] inicializaTablero(int[] tablero, int valor) {
		for (int i = 0; i < tablero.length; i++)
			tablero[i] = valor;
		return tablero;
	}

	public static void muestraPosiciones(int[] posiciones) {
		System.out.print( "[");
		for (int i = 0; i < posiciones.length; i++)
			System.out.printf("%d%s", posiciones[i], i < posiciones.length - 1 ? ", " : "]");
		System.out.println();
	}
}
