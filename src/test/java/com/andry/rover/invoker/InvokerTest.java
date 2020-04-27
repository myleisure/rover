package com.andry.rover.invoker;

import com.andry.rover.exception.RoverPositionInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvokerTest {

    private Path workingDIr;
    private IInvoker invoker;

    @BeforeEach
    public void init() {
        this.workingDIr = Path.of("src/test/resources");
    }

    @Test
    @DisplayName("Cas nominal sans probleme")
    public void process() throws FileNotFoundException, RoverPositionInvalidException {
        this.invoker = this.createInvoker("test-file.txt");
        assertDoesNotThrow(() -> this.invoker.initProcess());
        List<String> newPositins = this.invoker.process();
        assertTrue(newPositins.contains("1 3 N"));
        assertTrue(newPositins.contains("5 1 E"));
    }

    @Test
    @DisplayName("Dans le cas ou le rover est hors du plateau")
    public void processWithOutOfBound() throws FileNotFoundException {
        this.invoker = this.createInvoker("out-of-bound.txt");
        assertDoesNotThrow(() -> this.invoker.initProcess());
        assertThrows(RoverPositionInvalidException.class, () -> this.invoker.process());
    }

    private IInvoker createInvoker(String filename) throws FileNotFoundException {
        return new Invoker(new BufferedReader(new FileReader(workingDIr.resolve(filename).toFile())));
    }
}