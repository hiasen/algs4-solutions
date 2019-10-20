import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


class TestPercolationStats {
    void assertThrowsIllegalArgument(Executable thunk) {
        assertThrows(IllegalArgumentException.class, thunk,
                "This code should throw IllegalArgumentException");
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
    void mean_BetweenZeroAndOne() {
        PercolationStats ps = new PercolationStats(10, 1000);
        double mean = ps.mean();
        assertTrue(mean > 0.0 && mean < 1.0, "Mean should be between 0 and 1.");
    }

    @Test
    void stddev_NonNegative() {
        PercolationStats ps = new PercolationStats(10, 1000);
        assertTrue(ps.stddev() >= 0, "Standard deviation should be non negative.");
    }

    @Test
    void confidenceIntervalShouldIncludeMean() {
        PercolationStats ps = new PercolationStats(10, 1000);
        double mean = ps.mean();
        assertTrue(ps.confidenceLo() <= mean && mean <= ps.confidenceHi(),
                "Mean should lie inside the confidence interval.");
    }
}
