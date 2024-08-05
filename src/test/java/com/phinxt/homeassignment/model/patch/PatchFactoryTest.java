package com.phinxt.homeassignment.model.patch;

import com.phinxt.homeassignment.dto.RunRobotRequest;
import com.phinxt.homeassignment.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PatchFactoryTest {

    private PatchFactory patchFactory;

    @BeforeEach
    public void setUp() {
        patchFactory = new PatchFactory();
    }

    @Test
    public void testCreateCrumbPatch() {
        RunRobotRequest.PatchDetail patchDetail = new RunRobotRequest.PatchDetail();
        patchDetail.setCoords(Arrays.asList(1, 1));
        patchDetail.setType("Crumb");

        Patch patch = patchFactory.createPatch(patchDetail);

        assertNotNull(patch);
        assertTrue(patch instanceof CrumbPatch);
        assertEquals(new Position(1, 1), patch.getPosition());
    }

    @Test
    public void testCreateLiquidPatch() {
        RunRobotRequest.PatchDetail patchDetail = new RunRobotRequest.PatchDetail();
        patchDetail.setCoords(Arrays.asList(2, 2));
        patchDetail.setType("Liquid");

        Patch patch = patchFactory.createPatch(patchDetail);

        assertNotNull(patch);
        assertTrue(patch instanceof LiquidPatch);
        assertEquals(new Position(2, 2), patch.getPosition());
    }

    @Test
    public void testCreateDustPatch() {
        RunRobotRequest.PatchDetail patchDetail = new RunRobotRequest.PatchDetail();
        patchDetail.setCoords(Arrays.asList(3, 3));
        patchDetail.setType("Dust");

        Patch patch = patchFactory.createPatch(patchDetail);

        assertNotNull(patch);
        assertTrue(patch instanceof DustPatch);
        assertEquals(new Position(3, 3), patch.getPosition());
    }

    @Test
    public void testCreateUnknownPatchType() {
        RunRobotRequest.PatchDetail patchDetail = new RunRobotRequest.PatchDetail();
        patchDetail.setCoords(Arrays.asList(4, 4));
        patchDetail.setType("UNKNOWN");

        Patch patch = patchFactory.createPatch(patchDetail);

        assertNotNull(patch);
        assertTrue(patch instanceof DustPatch);
        assertEquals(new Position(4, 4), patch.getPosition());
    }

    @Test
    public void testCreatePatches() {
        RunRobotRequest.PatchDetail patchDetail1 = new RunRobotRequest.PatchDetail();
        patchDetail1.setCoords(Arrays.asList(1, 1));
        patchDetail1.setType("Crumb");

        RunRobotRequest.PatchDetail patchDetail2 = new RunRobotRequest.PatchDetail();
        patchDetail2.setCoords(Arrays.asList(2, 2));
        patchDetail2.setType("Liquid");

        List<RunRobotRequest.PatchDetail> patchDetails = Arrays.asList(patchDetail1, patchDetail2);

        List<Patch> patches = patchFactory.createPatches(patchDetails);

        assertNotNull(patches);
        assertEquals(2, patches.size());
        assertTrue(patches.get(0) instanceof CrumbPatch);
        assertTrue(patches.get(1) instanceof LiquidPatch);
    }
}