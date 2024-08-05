package com.phinxt.homeassignment.model.cleaner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class MopCleanerTest {

    private MopCleaner mopCleaner;

    @BeforeEach
    public void setUp() {
        mopCleaner = new MopCleaner();
    }

    @Test
    public void testClean(CapturedOutput output) {
        mopCleaner.clean();
        assertThat(output).contains("Patch cleaned with Mop");
    }
}