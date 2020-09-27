package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BasicChannelSample {
	

	public static void main(String[] args)  throws FileNotFoundException,IOException{
		RandomAccessFile aFile = new RandomAccessFile("D:\\softwares\\apache-zookeeper-3.6.1-bin.tar\\apache-zookeeper-3.6.1-bin\\apache-zookeeper-3.6.1-bin\\conf\\zoo.cfg", "rw");
	    FileChannel inChannel = aFile.getChannel();

	    ByteBuffer buf = ByteBuffer.allocate(48);

	    int bytesRead = inChannel.read(buf);
	    while (bytesRead != -1) {

	      System.out.println("Read " + bytesRead);
	      buf.flip();

	      while(buf.hasRemaining()){
	          System.out.print((char) buf.get());
	      }

	      buf.clear();
	      bytesRead = inChannel.read(buf);
	    }
	    aFile.close();
	}
}
