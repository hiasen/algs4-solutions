import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestPercolationStats {
    void assertThrowsIllegalArgument(Executable thunk) {
        assertThrows(IllegalArgumentException.class, thunk);
    }

    @Test
    void testConstructor() {
        new PercolationStats(10, 1000);
    }

    @Test
    void testConstructorThrowsException() {
        assertThrowsIllegalArgument(() -> new PercolationStats(0, 10000));
        assertThrowsIllegalArgument(() -> new PercolationStats(-1000, 10000));

        assertThrowsIllegalArgument(() -> new PercolationStats(10, -1000));
        assertThrowsIllegalArgument(() -> new PercolationStats(10, 0));

    }

    @Test
    void mainMethod() {
        PercolationStats.main(new String[]{"10", "1000"});
    }

    @Test
    void hasCorrectMethods() {
        PercolationStats ps = new PercolationStats(10, 1000);
        double mean = ps.mean();
        double stddev = ps.stddev();
        assertTrue(mean > 0.0 && mean < 1.0);
        assertTrue(stddev >= 0);

        double cLo = ps.confidenceLo();
        double cHi = ps.confidenceHi();
        assertTrue(cLo <= mean);
        assertTrue(cLo >= 0);
        assertTrue(cHi >= mean);
        assertTrue(cHi <= 1);
    }
}
