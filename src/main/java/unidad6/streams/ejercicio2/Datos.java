package unidad6.streams.ejercicio2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Datos {
	
	/* -----------------------------------------------------------------------------------------------------------------
	 *   Retorna una secuencia con las líneas del cuento "La Biblioteca de Babel" de Jorge Luis Borges.
	 *  ----------------------------------------------------------------------------------------------------------------
	 */
	static Stream<String> getLineas() {
		try {
			Path path = Path.of(Datos.class.getResource("La Biblioteca de Babel.txt").toURI());
			return Files.lines(path);
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
