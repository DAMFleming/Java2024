package unidad5.colecciones2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestColeccionesII {

	@Test
    void testEjercicio01() {
		Set<String> set = new HashSet<>(Set.of("a", "aa", "aaa", "aaaa", "aaaaa"));
		Colecciones.eliminarLasDeLongitudPar(set);
		assertTrue(set.stream().noneMatch(s -> s.length() % 2 == 0));
    }
	
	@Test
    void testEjercicio02() {
		assertFalse(Colecciones.contieneImpares(Set.of(0, 2, 4, 10, 6, 20)));
		assertTrue(Colecciones.contieneImpares(Set.of(0, 2, 4, 7, 10, 6, 11, 20)));
		assertFalse(Colecciones.contieneImpares(Set.of()));
    }
	
}
