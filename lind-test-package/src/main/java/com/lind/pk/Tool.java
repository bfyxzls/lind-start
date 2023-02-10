package com.lind.pk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author lind
 * @date 2023/2/7 17:26
 * @since 1.0.0
 */
public class Tool {

	public static void print() {
		InputStream resource = Tool.class.getClassLoader().getResourceAsStream("licence.txt");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		int bufSize = 1024;
		byte[] buffer = new byte[bufSize];
		int len = 0;
		while (true) {
			try {
				if (!(-1 != (len = resource.read(buffer, 0, bufSize))))
					break;
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
			bos.write(buffer, 0, len);
		}
		ServiceLoader<SpiHello> spiHellos = ServiceLoader.load(SpiHello.class);
		Iterator<SpiHello> iterable = spiHellos.iterator();
		while (iterable.hasNext()) {
			iterable.next().printHello();
		}
		System.out.println("value=" + bos.toString());
	}

}
