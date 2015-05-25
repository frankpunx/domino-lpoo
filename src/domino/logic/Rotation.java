package domino.logic;

/**
 * Este tipo enumerado indica qual a orientacao da peca. Mais concretamente, indica qual a orientacao do primeiro valor da peca
 * @author ADC
 *
 */
public enum Rotation {

	// Rotacao de 0 graus
	NORTH, 
	
	// Rotacao de 90 graus
	EAST, 
	
	// Rotacao de 180 graus
	SOUTH, 
	
	// Rotacao de 270
	WEST;

	
	public static final Rotation calculateRotation(Rotation r, int nRots) {
		int newRot = r.ordinal() + nRots % 4;

		return Rotation.values()[newRot];
	}
}
