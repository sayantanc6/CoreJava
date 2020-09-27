package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelTransferSample {

	public static void main(String[] args) throws FileNotFoundException,IOException{
		RandomAccessFile fromFile = new RandomAccessFile("D:\\\\To.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("D:\\From.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count    = fromChannel.size();

	//	toChannel.transferFrom(fromChannel, position, count);
		fromChannel.transferTo(position, count, toChannel);
	}

}
