package at.er.ytbattle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ObjectSerialization {

	static public void objectToFile(Object o, File out) throws IOException {
		FileOutputStream fos = new FileOutputStream(out);
		GZIPOutputStream gos = new GZIPOutputStream(fos, true);
		ObjectOutputStream oos = new ObjectOutputStream(gos);
		oos.writeObject(o);
		oos.flush();
		gos.flush();
		fos.flush();
		
		oos.close();
		gos.close();
		fos.close();
	}

	static public Object fileToObject(File in) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(in);
		GZIPInputStream gis = new GZIPInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(gis);
		Object o = ois.readObject();
		
		ois.close();
		gis.close();
		fis.close();
		
		return o;
	}
}
