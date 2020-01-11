package net.tiny.feature.svg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BrandImageServiceTest {

    @Test
    public void testGenerateChart() throws Exception {
        BrandImageService servie = new BrandImageService();
        assertNotNull(servie);
        String svg = new String(servie.generateSvg("fa", "fa-recycle", "red", "gray"));
        System.out.println(svg);
        assertTrue(svg.contains("background-color:#6c757d"));
        assertTrue(svg.contains("fill:#dc3545"));
    }
}
