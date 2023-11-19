package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;

public class DiskMapTest {
    private static final String FILE_PATH = "test_disk_map.txt";
    private DiskMap diskMap = new DiskMap(FILE_PATH);

    @Test
    public void testExists() {
        File file = new File(FILE_PATH);
        Assertions.assertTrue(file.exists());
    }

    @Test
    public void testPutAndGet() {
        diskMap.put("testKey", "testValue");
        String value = diskMap.get("testKey");
        Assertions.assertEquals("testValue", value);
    }

    @Test
    public void testRemove() {
        diskMap.put("testKey", "testValue");
        diskMap.remove("testKey");
        Assertions.assertFalse(diskMap.containsKey("testKey"));
    }
}
