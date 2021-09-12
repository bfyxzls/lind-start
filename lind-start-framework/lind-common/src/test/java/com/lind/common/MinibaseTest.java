package com.lind.common;

import com.lind.common.minibase.Bytes;
import com.lind.common.minibase.Config;
import com.lind.common.minibase.KeyValue;
import com.lind.common.minibase.MStore;
import com.lind.common.minibase.MiniBase;
import lombok.SneakyThrows;
import org.junit.Test;

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
}
