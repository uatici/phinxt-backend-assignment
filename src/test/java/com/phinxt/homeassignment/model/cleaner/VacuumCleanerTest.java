package com.phinxt.homeassignment.model.cleaner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class VacuumCleanerTest {

    private VacuumCleaner vacuumCleaner;

    @BeforeEach
    public void setUp() {
        vacuumCleaner = new VacuumCleaner();
    }

    @Test
    public void testClean(CapturedOutput output) {
        vacuumCleaner.clean();
        assertThat(output).contains("Patch cleaned with Vacuum");
    }
}