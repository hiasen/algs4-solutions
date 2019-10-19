import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;


class TestPercolationStats {
    void assertThrowsIllegalArgument(Executable thunk) {
        assertThrows(IllegalArgumentException.class, thunk, "This code should throw IllegalArgumentException");
    }

    @Test
    void constructor_ThrowsIllegalArgumentException_When_nEqualsZero() {
        assertThrowsIllegalArgument(() -> new PercolationStats(0, 10000));
    }
    @Test
    void constructor_ThrowsIllegalArgumentException_When_nNegative() {
        assertThrowsIllegalArgument(() -> new PercolationStats(-1000, 10000));
    }
    @Test
    void constructor_ThrowsIllegalArgumentException_When_TrialsIsNegative() {
        assertThrowsIllegalArgument(() -> new PercolationStats(10, -1000));
    }
    @Test
    void constructor_ThrowsIllegalArgumentException_When_TrialsIsZero() {
        assertThrowsIllegalArgument(() -> new PercolationStats(10, 0));
    }

    @Test
    void mainMethod() {
        assertDoesNotThrow(() -> PercolationStats.main(new String[]{"10", "1000"}));
    }


    @Test
    void mean_BetweenZeroAndOne_() {
        PercolationStats ps = new PercolationStats(10, 1000);
        double mean = ps.mean();
        assertTrue(mean > 0.0 && mean < 1.0, "Mean should be between 0 and 1.");
    }
    @Test
    void stddev_NonNegative() {
        PercolationStats ps = new PercolationStats(10, 1000);
        double stddev = ps.stddev();
        assertTrue(stddev >= 0, "Standard deviation should be non negative.");
    }
    @Test
    void ConfidenceIntervalShouldIncludeMean() {
        PercolationStats ps = new PercolationStats(10, 1000);
        double mean = ps.mean();
        double cLo = ps.confidenceLo();
        double cHi = ps.confidenceHi();
        assertTrue(cLo <= mean && mean <= cHi, "Mean should lie inside the confidence interval.");
    }
}
