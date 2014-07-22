package at.er.ytbattle.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;

/**
 * Converts ByteArrays, Strings, Files to Objects
 * 
 * @author Rene Hollander
 * @version 1.0
 * 
 */
public class Deserialize {

    /**
     * Reads the Object from an ByteArray
     * 
     * @param data ByteArray to read Object from
     * @param gzip Set to True, if the object is compressed with GZIP
     * @return Returns the deserialied Object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static public Object readFromByteArray(byte[] data, boolean gzip) throws IOException, ClassNotFoundException {
        Object ret = null;
        if (gzip == false) {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            ret = ois.readObject();
            ois.close();
            bais.close();
        } else if (gzip == true) {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            GZIPInputStream gis = new GZIPInputStream(bais);
            ObjectInputStream ois = new ObjectInputStream(gis);
            ret = ois.readObject();
            ois.close();
            gis.close();
            bais.close();
        }
        return ret;
    }

    /**
     * Reads the Object from a File
     * 
     * @param f File to read From
     * @param gzip Set to True, if the object is compressed with GZIP
     * @return Returns Object from File
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readFromFile(File f, boolean gzip) throws IOException, ClassNotFoundException {
        Object ret = null;
        if (gzip == false) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ret = ois.readObject();
            ois.close();
            fis.close();
        } else if (gzip == true) {
            FileInputStream fis = new FileInputStream(f);
            GZIPInputStream gis = new GZIPInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(gis);
            ret = ois.readObject();
            ois.close();
            gis.close();
            fis.close();
        }
        return ret;
    }

    /**
     * Reads an Object from String
     * 
     * @param data String to read From
     * @param gzip Set to True, if the object is compressed with GZIP
     * @param base64 Set to True, if the object is encoded with Base64
     * @return Returns Object from String
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static public Object readFromSring(String data, boolean gzip, boolean base64) throws IOException, ClassNotFoundException {
        Object ret = null;
        if (gzip == false && base64 == false) {
            ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes("UTF-8"));
            ObjectInputStream ois = new ObjectInputStream(bais);
            ret = ois.readObject();
            ois.close();
            bais.close();
            return ret;
        } else if (gzip == true && base64 == false) {
            ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes("UTF-8"));
            GZIPInputStream gis = new GZIPInputStream(bais);
            ObjectInputStream ois = new ObjectInputStream(gis);
            ret = ois.readObject();
            ois.close();
            gis.close();
            bais.close();
            return ret;
        } else if (gzip == false && base64 == true) {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(data));
            ObjectInputStream ois = new ObjectInputStream(bais);
            ret = ois.readObject();
            ois.close();
            bais.close();
            return ret;
        } else if (gzip == true && base64 == true) {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(data));
            GZIPInputStream gis = new GZIPInputStream(bais);
            ObjectInputStream ois = new ObjectInputStream(gis);
            ret = ois.readObject();
            ois.close();
            gis.close();
            bais.close();
            return ret;
        }
        return ret;
    }
}