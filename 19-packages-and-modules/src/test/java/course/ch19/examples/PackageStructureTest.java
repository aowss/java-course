package course.ch19.examples;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackageStructureTest {

    @Test
    void buildPackageNameReversesAndLowercases() {
        assertEquals("com.example.myapp.service",
                PackageStructure.buildPackageName("example.com", "myapp", "service"));
    }

    @Test
    void buildPackageNameThreeLevelDomain() {
        assertEquals("uk.co.example.shop.model",
                PackageStructure.buildPackageName("example.co.uk", "shop", "model"));
    }

    @Test
    void buildPackageNameRejectsNullDomain() {
        assertThrows(IllegalArgumentException.class,
                () -> PackageStructure.buildPackageName(null, "proj", "model"));
    }

    @Test
    void buildPackageNameRejectsBlankProject() {
        assertThrows(IllegalArgumentException.class,
                () -> PackageStructure.buildPackageName("example.com", "  ", "model"));
    }

    @Test
    void isValidPackageNameAcceptsValid() {
        assertTrue(PackageStructure.isValidPackageName("com.example.myapp"));
    }

    @Test
    void isValidPackageNameAcceptsSingleSegment() {
        assertTrue(PackageStructure.isValidPackageName("utils"));
    }

    @Test
    void isValidPackageNameRejectsUppercase() {
        assertFalse(PackageStructure.isValidPackageName("com.Example.myapp"));
    }

    @Test
    void isValidPackageNameRejectsEmpty() {
        assertFalse(PackageStructure.isValidPackageName(""));
    }

    @Test
    void isValidPackageNameRejectsNull() {
        assertFalse(PackageStructure.isValidPackageName(null));
    }

    @Test
    void isValidPackageNameRejectsTrailingDot() {
        assertFalse(PackageStructure.isValidPackageName("com.example."));
    }

    @Test
    void isValidPackageNameRejectsStartingWithDigit() {
        assertFalse(PackageStructure.isValidPackageName("com.1invalid"));
    }

    @Test
    void suggestLayoutContainsAllStandardLayers() {
        Map<String, String> layout = PackageStructure.suggestLayout("com.example.app");
        assertEquals(PackageStructure.STANDARD_LAYERS.size(), layout.size());
        for (String layer : PackageStructure.STANDARD_LAYERS) {
            assertEquals("com.example.app." + layer, layout.get(layer));
        }
    }

    @Test
    void suggestLayoutRejectsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> PackageStructure.suggestLayout(""));
    }
}
