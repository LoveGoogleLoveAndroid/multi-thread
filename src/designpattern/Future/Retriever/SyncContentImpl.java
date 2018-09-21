package designpattern.Future.Retriever;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SyncContentImpl implements Content{
    private byte[] contentBytes;

    @Override
    public byte[] getBytes() {
        return contentBytes;
    }

    public SyncContentImpl(String urlStr) {
        System.out.println(Thread.currentThread().getName() + ": getting " + urlStr);

        try
        {
            URL url = new URL(urlStr);
            DataInputStream in = new DataInputStream(url.openStream());
            byte[] buffer = new byte[1];
            int index = 0;

            try
            {
                while(true)
                {
                    int c = in.readUnsignedByte();

                    if (buffer.length <= index)
                    {
                        byte[] largerbuffer = new byte[buffer.length * 2];
                        System.arraycopy(buffer, 0, largerbuffer, 0, index);
                        buffer = largerbuffer;
                    }
                    buffer[index++] = (byte)c;
                }
            }
            finally {
                in.close();
                contentBytes = new byte[index];
                System.arraycopy(buffer, 0, contentBytes, 0, index);
            }
            //contentBytes = new byte[index];
            //System.arraycopy(buffer, 0, contentBytes, 0, index);
        }
        catch (MalformedURLException e)
        {

        }
        catch (IOException e)
        {

        }
    }
}
