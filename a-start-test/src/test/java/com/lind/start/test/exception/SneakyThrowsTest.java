package com.lind.start.test.exception;


import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class SneakyThrowsTest {
    public static ImmutableMap<String, String> map = ImmutableMap.of("one", "two");

    @Test
    public void nested() {
        Son son = new Son();
        son.doResult();
        System.out.print(map.toString());
    }

    @Test
    public void nestedException() {
        Father father = new Father();
        father.helloThrow(new Do() {
            @SneakyThrows
            @Override
            public String process() throws IllegalArgumentException {
                father.read("ok");
                return "ok";
            }
        }, "zzl");
    }

    interface Do {
        String process() throws IllegalArgumentException;
    }

    class Father {
        /**
         * 有这个就不用写new RuntimeException了，也不用在方法上throws异常了，即在调用方法时，也省去了throws关系字
         */
        @SneakyThrows
        public void print() {
            try {
                Integer a = null;
                System.out.print("c=" + a.toString());

            } catch (Exception e) {
                e.getStackTrace();
            }

        }

        public String read(String name) throws Exception {
            if (StringUtils.isEmpty(name)) {
                throw new IllegalArgumentException("name not empty!");
            }
            System.out.println("read name:" + name);
            return name;
        }

        public void helloThrow(Do $do, String name) {
            $do.process();
            System.out.println("helloThrow name:" + name);
        }

        public void nestedException() {
        }
    }

    class Son extends Father {

        public void a() {
            try {
                b();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void b() throws IOException {

        }

        public void doResult() {
            print();
            System.out.print("do result!");
        }

        @Override
        public void nestedException() {
            Father father = new Father();
            father.helloThrow(new Do() {

                //这块由于father.read()方法抛出的是Exception异常，需要手工处理，而添加SneakyThrows之后，lombok帮我们处理了
                @SneakyThrows
                @Override
                public String process() throws IllegalArgumentException {
                    String a = father.read("ok");
                    return a;
                }
            }, "zzl");
        }

    }
}
