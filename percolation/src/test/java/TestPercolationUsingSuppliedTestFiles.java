import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPercolationUsingSuppliedTestFiles {
    private final static String PERCOLATE_FOLDER = "percolates";
    private final static String NOPERCOLATE_FOLDER = "nopercolates";

    @Test
    void percolating() {
        String folder = getClass().getResource(PERCOLATE_FOLDER).getFile();
        File[] files = new File(folder).listFiles();
        if (files == null) return;
        for (File tada: files) {
            assertTrue(runPercolation(tada), tada.getAbsolutePath());
        }
    }
    @Test
    void notPercolating() {
        String folder = getClass().getResource(NOPERCOLATE_FOLDER).getFile();
        File[] files = new File(folder).listFiles();
        if (files == null) return;
        for (File tada: files) {
            assertFalse(runPercolation(tada), tada.getAbsolutePath());
        }
    }

    private boolean runPercolation(File file) {
        try {
            Scanner sc = new Scanner(file);
            int n = sc.nextInt();
            Percolation p = new Percolation(n);
            while (sc.hasNext()) {
                int row = sc.nextInt();
                int col = sc.nextInt();
                p.open(row, col);
            }
            return p.percolates();
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
