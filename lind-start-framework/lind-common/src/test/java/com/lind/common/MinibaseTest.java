package com.lind.common;

import com.lind.common.minibase.*;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;

import static com.lind.common.minibase.DiskFile.TRAILER_SIZE;

public class MinibaseTest {
    @SneakyThrows
    @Test
    public void write() {
        Config conf = new Config().setDataDir("d:\\miniBase").setMaxMemstoreSize(1).setFlushMaxRetries(1)
                .setMaxDiskFiles(10);
        final MiniBase db = MStore.create(conf).open();

        // Put
        db.put(Bytes.toBytes(1), Bytes.toBytes(2));

        // Scan
        MiniBase.Iter<KeyValue> kv = db.scan();
        while (kv.hasNext()) {
            KeyValue kvalue = kv.next();
            System.out.println(kvalue);
        }

    }

    @SneakyThrows
    @Test
    public void read() {
        File f = new File("d:\\miniBase\\data.00000000000000000000.tmp");
        RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");

        long fileSize = f.length();
        assert fileSize > TRAILER_SIZE;
        randomAccessFile.seek(fileSize - TRAILER_SIZE);

        byte[] buffer = new byte[8];
        assert randomAccessFile.read(buffer) == buffer.length;
        assert fileSize == Bytes.toLong(buffer);
    }
}
