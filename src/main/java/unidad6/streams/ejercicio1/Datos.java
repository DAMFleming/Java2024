package unidad6.streams.ejercicio1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Datos {
	
	/* -----------------------------------------------------------------------------------------------------------------
	 *   Retorna una secuencia con todas las palabras de la lengua Española.
	 *  ----------------------------------------------------------------------------------------------------------------
	 */
	static Stream<String>getPalabras() {
		try {
			Path path = Path.of(Datos.class.getResource("palabras.txt").toURI());
			return Files.lines(path);
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
