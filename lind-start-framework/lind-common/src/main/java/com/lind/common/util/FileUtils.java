package com.lind.common.util;

import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.Function;

import static org.springframework.util.Assert.notNull;

/**
 * 文件读取工具.
 */
public class FileUtils {
    public static final String OBJ_NO_NULL = "对象不能为空";
    static Function<String, String> resourceFun;

    /**
     * 禁止实例化.
     */
    private FileUtils() {
    }

    /**
     * 设置文件获取的路径为资源resource路径.
     */
    public static void setResourcePath() {
        resourceFun = (path) -> {
            try {
                return ResourceUtils.getFile("classpath:" + path).getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    /**
     * IO方式读取文件到对象.
     *
     * @param name .
     * @return .
     */
    public static byte[] readResourceByteArray(String name) throws IOException {
        if (resourceFun != null) {
            name = resourceFun.apply(name);
        }
        File f = new File(name);
        notNull(f, OBJ_NO_NULL);

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * NIO方式读取.
     * Channel(通道)，Buffer(缓冲区), Selector。传统IO基于字节流和字符流进行操作，而NIO基于Channel和Buffer(缓冲区)进行操作，
     * 数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。Selector(选择区)用于监听多个通道的事件（比如：连接打开，数据到达）。
     * 因此，Selector单个线程可以监听多个数据通道
     *
     * @param name .
     * @return .
     * @throws IOException .
     */
    public static byte[] readResourceByteArrayNIO(String name) throws IOException {
        if (resourceFun != null) {
            name = resourceFun.apply(name);
        }
        File f = new File(name);
        notNull(f, OBJ_NO_NULL);
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            notNull(fs, OBJ_NO_NULL);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 大文件读取.
     *
     * @param name .
     * @return .
     * @throws IOException .
     */
    public static byte[] readResourceByteArrayBigFileNIO(String name) throws IOException {
        FileChannel fc = null;
        try {
            if (resourceFun != null) {
                name = resourceFun.apply(name);
            }
            File f = new File(name);
            notNull(f, OBJ_NO_NULL);

            fc = new RandomAccessFile(f.getAbsoluteFile(), "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 将对象写到资源文件.
     */
    public static void writeResourceFromByteArrayNIO(String path, byte[] obj) {
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            notNull(file, OBJ_NO_NULL);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IllegalArgumentException("文件建立不成功");
                }
            }
            fos = new FileOutputStream(file.getAbsoluteFile());
            notNull(fos, OBJ_NO_NULL);
            FileChannel channel = fos.getChannel();
            ByteBuffer src = ByteBuffer.wrap(obj);
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
            System.out.println("初始化容量和limit：" + src.capacity() + ","
                    + src.limit());
            int length = 0;
            while ((length = channel.write(src)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
                System.out.println("写入长度:" + length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
