package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
 
public class FileChannelScatterReader {
 
    public static void main (String [] args) throws Exception {
     
        new FileChannelScatterReader().readFile();
    }
     
    private void readFile() throws IOException {
 
        RandomAccessFile raf = new RandomAccessFile("D:\\softwares\\apache-zookeeper-3.6.1-bin.tar\\apache-zookeeper-3.6.1-bin\\apache-zookeeper-3.6.1-bin\\conf\\zoo.cfg", "r");
        FileChannel channel = raf.getChannel();
        System.out.println("File channel open. Reading file...");
         
        ByteBuffer buffer = ByteBuffer.allocate(4);
        channel.read(buffer);
        buffer.flip();
        int noOfRecords = buffer.getInt();
        System.out.println("Number of records: " + noOfRecords);        
         
        for (int i = 0; i < noOfRecords; i++) {
         
            // get text data size and file size
             
            ByteBuffer buff1 = ByteBuffer.allocate(4);
            ByteBuffer buff2 = ByteBuffer.allocate(4);
            ByteBuffer [] buffs = {buff1, buff2};
            channel.read(buffs);
             
            buff1.flip();
            int dataSize = buff1.getInt();
            System.out.println((i+1) + " Text data size: " + dataSize);
             
            buff2.flip();
            int fileSize = buff2.getInt();
            System.out.println((i+1) + " File data size: " + fileSize);         
         
            // get text and file data
             
            buff1 = ByteBuffer.allocate(dataSize);
            buff2 = ByteBuffer.allocate(fileSize);
             
            buffs = new ByteBuffer [] {buff1, buff2};
            channel.read(buffs);
             
            // get text
            byte [] bytes = buff1.array();
            String data = new String(bytes);
            System.out.println((i+1) + " Text data: " + data);
             
            // get file, if exists
            if (fileSize > 0) {
 
                byte [] file = buff2.array();
                System.out.println((i+1) + " Read file size: " + file.length);
                Path filePath = Paths.get((i+1) + "file.pdf");
            Files.write(filePath, file, StandardOpenOption.CREATE);
                System.out.println((i+1) + " File: " + filePath.getFileName());
            }
        }
 
        raf.close();
        channel.close();
        System.out.println("Closing channel.");
    }
}