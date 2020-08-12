package com.lind.start.test.hbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/6/5 15:09
 **/
@Data
@ConfigurationProperties("winter.hbase")
public class WinterHBaseProperties {


    /**
     * 命名空间，默认为空，务必设置好
     */
    private String nameSpace = "";

    /**
     * 是否启用HBase starter
     */
    private boolean enable = true;

    /**
     * 权限账号
     */
    private String username;


    /**
     * zookeeper相关配置
     */
    private WinterZookeeperProperties zookeeper = new WinterZookeeperProperties();
    /**
     * 客户端相关配置，重试次数与时间等
     */
    private WinterHBaseClientProperties client = new WinterHBaseClientProperties();
    private Rpc rpc = new Rpc();


    @Data
    public class Rpc {
        /**
         * 表示一次RPC请求的超时时间。如果某次RPC时间超过该值，客户端就会主动关闭socket，单位ms
         */
        private Long timeout = 1000L;
    }

    @Data
    public class WinterZookeeperProperties {

        /**
         * zookeeper地址，以英文逗号隔开
         */
        private String quorum;

    }

    @Data
    public class WinterHBaseClientProperties {

        /**
         * 失败重试等待时间，单位 ms
         */
        private Long pause = 1000L;

        /**
         * 重试的相关配置
         */
        private Retries retries = new Retries();

        /**
         *
         */
        private Operation operation = new Operation();

        /**
         *
         */
        private Scanner scanner = new Scanner();


        @Data
        public class Retries {
            /**
             * 失败重试次数
             */
            private Integer numbers = 3;

        }

        @Data
        public class Operation {

            /**
             * 该参数表示HBase客户端发起一次数据操作直至得到响应之间总的超时时间，单位ms 据操作类型包括get、append、increment、delete、put等
             */
            private Long timeout = 1000L;

        }

        @Data
        public class Scanner {
            /**
             * 表示HBase客户端发起一次scan操作的rpc调用至得到响应之间总的超时时间
             */
            private Long timeout = 60000L;

        }

    }

}
