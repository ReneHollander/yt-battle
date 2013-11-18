package at.rene8888.serilib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPOutputStream;

import at.rene8888.serilib.util.Base64;

/**
 * Convert Objects to ByteArrays, Strings and write it to files
 * 
 * @author Rene Hollander
 * @version 1.0
 * 
 */
public class Serialize {

	/**
	 * Converts an Object to a ByteArray
	 * 
	 * @param object
	 *            Object to Serialize
	 * @param gzip
	 *            Compresses the ByteArray with GZIP
	 * @return Returns ByteArray form Object
	 * @throws IOException
	 */
	static public byte[] writeToByteArray(Serializable object, boolean gzip) throws IOException {
		byte[] ret = null;
		if (gzip == false) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.close();
			ret = baos.toByteArray();
			baos.close();
		} else if (gzip == true) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			ObjectOutputStream oos = new ObjectOutputStream(gos);
			oos.writeObject(object);
			oos.close();
			gos.close();
			ret = baos.toByteArray();
			baos.close();
		}
		return ret;
	}

	/**
	 * Writes an Object to File
	 * 
	 * @param object
	 *            Object to Serialize
	 * @param f
	 *            File to write to
	 * @param gzip
	 *            Compresses the ByteArray with GZIP
	 * @return Returns True if successfull
	 * @throws IOException
	 */
	static public boolean writeToFile(Serializable object, File f, boolean gzip) throws IOException {
		if (gzip == false) {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
			fos.close();
			return true;
		} else if (gzip == true) {
			FileOutputStream fos = new FileOutputStream(f);
			GZIPOutputStream gos = new GZIPOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(gos);
			oos.writeObject(object);
			oos.close();
			gos.close();
			fos.close();
			return true;
		}
		return false;
	}

	/**
	 * Writes an Object to a String
	 * 
	 * @param object
	 *            Object to Serialize
	 * @param gzip
	 *            Compresses the ByteArray with GZIP
	 * @param base64
	 *            Encodes the String with Base64
	 * @return Returns String from Object
	 * @throws IOException
	 */
	static public String writeToSring(Serializable object, boolean gzip, boolean base64) throws IOException {
		String ret = null;
		if (gzip == false && base64 == false) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.close();
			ret = baos.toString("UTF-8");
			baos.close();
			return ret;
		} else if (gzip == true && base64 == false) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			ObjectOutputStream oos = new ObjectOutputStream(gos);
			oos.writeObject(object);
			oos.close();
			gos.close();
			ret = baos.toString("UTF-8");
			baos.close();
			return ret;
		} else if (gzip == false && base64 == true) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.close();
			ret = Base64.encode(baos.toByteArray());
			baos.close();
			return ret;
		} else if (gzip == true && base64 == true) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			ObjectOutputStream oos = new ObjectOutputStream(gos);
			oos.writeObject(object);
			oos.close();
			gos.close();
			ret = Base64.encode(baos.toByteArray());
			baos.close();
			return ret;
		}
		return ret;
	}
}
