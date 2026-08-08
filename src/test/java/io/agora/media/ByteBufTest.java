package io.agora.media;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ByteBufTest {

    @Test
    void shouldCreateEmptyByteBuf() {
        ByteBuf buf = new ByteBuf();
        assertNotNull(buf);
        assertEquals(0, buf.asBytes().length);
    }

    @Test
    void shouldCreateByteBufFromBytes() {
        ByteBuf buf = new ByteBuf(new byte[]{1, 2, 3, 4});
        assertNotNull(buf);
    }

    @Test
    void shouldPutAndReadShort() {
        ByteBuf buf = new ByteBuf();
        buf.put((short) 42);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        assertEquals(42, reader.readShort());
    }

    @Test
    void shouldPutAndReadInt() {
        ByteBuf buf = new ByteBuf();
        buf.put(12345);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        assertEquals(12345, reader.readInt());
    }

    @Test
    void shouldPutLong() {
        ByteBuf buf = new ByteBuf();
        buf.put(9876543210L);
        byte[] bytes = buf.asBytes();
        assertEquals(8, bytes.length); // long is 8 bytes
    }

    @Test
    void shouldPutAndReadByteArray() {
        byte[] data = {10, 20, 30};
        ByteBuf buf = new ByteBuf();
        buf.put(data);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        byte[] result = reader.readBytes();
        assertArrayEquals(data, result);
    }

    @Test
    void shouldPutAndReadString() {
        ByteBuf buf = new ByteBuf();
        buf.put("hello");
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        assertEquals("hello", reader.readString());
    }

    @Test
    void shouldPutAndReadStringMap() {
        TreeMap<Short, String> map = new TreeMap<>();
        map.put((short) 1, "one");
        map.put((short) 2, "two");
        ByteBuf buf = new ByteBuf();
        buf.put(map);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        TreeMap<Short, String> result = reader.readMap();
        assertEquals(2, result.size());
        assertEquals("one", result.get((short) 1));
        assertEquals("two", result.get((short) 2));
    }

    @Test
    void shouldPutAndReadIntMap() {
        TreeMap<Short, Integer> map = new TreeMap<>();
        map.put((short) 10, 100);
        map.put((short) 20, 200);
        ByteBuf buf = new ByteBuf();
        buf.putIntMap(map);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        TreeMap<Short, Integer> result = reader.readIntMap();
        assertEquals(2, result.size());
        assertEquals(100, result.get((short) 10));
        assertEquals(200, result.get((short) 20));
    }

    @Test
    void shouldChainPutOperations() {
        ByteBuf buf = new ByteBuf();
        ByteBuf result = buf.put((short) 1).put(2).put("test");
        assertNotNull(result);
        assertEquals(buf, result);
    }

    @Test
    void shouldHandleEmptyStringMap() {
        TreeMap<Short, String> map = new TreeMap<>();
        ByteBuf buf = new ByteBuf();
        buf.put(map);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        TreeMap<Short, String> result = reader.readMap();
        assertEquals(0, result.size());
    }

    @Test
    void shouldHandleEmptyIntMap() {
        TreeMap<Short, Integer> map = new TreeMap<>();
        ByteBuf buf = new ByteBuf();
        buf.putIntMap(map);
        byte[] bytes = buf.asBytes();
        ByteBuf reader = new ByteBuf(bytes);
        TreeMap<Short, Integer> result = reader.readIntMap();
        assertEquals(0, result.size());
    }
}
