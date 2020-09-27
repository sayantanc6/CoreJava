package nio;

import java.nio.CharBuffer;

public class Test_Buffer {

	public void test_buffer_info() {
        CharBuffer buf = CharBuffer.allocate(10);
        position(buf, 0).limit(buf, 10);
       
        buf.put("JDK api".toCharArray()); // 7 characters
        position(buf, 7).limit(buf, 10);
   
        buf.flip(); // "Drain state"
        position(buf, 0).limit(buf, 7);
       
        char [] dest = new char[5];
        buf.get(dest);
        position(buf, 5).limit(buf, 7);
        assertEquals("JDK a", new String(dest));
       
        buf.rewind();
        position(buf, 0).limit(buf, 7);
       
        buf.get(dest, 2, 3);
        position(buf, 3).limit(buf, 7);
        assertEquals("JDJDK", new String(dest));
       
        buf.compact(); // "Fill state"
        position(buf, 4).limit(buf, 10);
       
        buf.mark();
        buf.put("s"); // [ apis]
        position(buf, 5).limit(buf, 10);
       
        buf.reset();
        position(buf, 4).limit(buf, 10);
       
        buf.put("arist"); // [ apiarist]
        position(buf, 9).limit(buf, 10);
       
        buf.flip();
        position(buf, 0).limit(buf, 9);
       
    }
	
	Test_Buffer position(CharBuffer buf, int expected){
        assertEquals(expected, buf.position());
        return this;
    }
    Test_Buffer limit(CharBuffer buf, int expected){
        assertEquals(expected, buf.limit());
        return this;
    }
    Test_Buffer capacity(CharBuffer buf, int expected){
        assertEquals(expected, buf.capacity());
        return this;
    }
}
