package Exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryServiceTest {
    byte[] array = null;

    @Test
    void memServiceTest() {
        int size = MemoryService.getAvailableMemorySize();
        array = new byte[size];
        array = null;
        try {
            array = new byte[size + 1];
            fail("Expected out of memory exception");
        } catch (OutOfMemoryError ignored) {
        }
    }
}
