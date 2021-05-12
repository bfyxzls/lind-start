package com.lind.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

@RestController
public class OneController {
    private static final String loremIpsum = "lind is man he is great man";
    private static final String[] hashTags = {"latin", "italy", "roman", "caesar", "cicero"};
    static String version = "8";
    static String TEST_IN = "test-stream-in" + version;
    static String TEST_OUT_END = "test-stream-out-end" + version;
    static String TEST_OUT_WORD = "test-stream-out-word" + version;
    private KafkaStreams streams1;
    private Random randomNumber = new Random();
    private String randomMessage;

    /**
     * 对以#latin结尾的消息，进行发布到其它队列.
     */
    @GetMapping("/startProcessing1")
    public void startProcessing1() {

        Properties props = getStreamProperties();

        final StreamsBuilder builder = new StreamsBuilder();
        //魔术关键行,以#latin结尾的发到另一个队列
        builder.stream(TEST_IN).filter((key, value) -> ((String) value).endsWith("latin")).to(TEST_OUT_END);

        final Topology topology = builder.build();
        streams1 = new KafkaStreams(topology, props);
        streams1.start();

    }

    /**
     * 对单词进行记数，结果发布到word-total队列.
     */
    @GetMapping("/startProcessing2")
    public void startProcessing2() {

        Properties props = getStreamProperties();

        final StreamsBuilder builder = new StreamsBuilder();
        //魔术关键行,以#latin结尾的发到另一个队列
        KStream<String, String> textLines = builder.stream(TEST_IN);
        KTable<String, Long> wordCounts = textLines
                // Create new KStream: by implements {@link ValueMapper} transform map textLine to String array
                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W+")))
                // select value as key
                .selectKey((key, word) -> word)
                .groupByKey()
                // count the number of records in this new stream. use default store
                .count(Materialized.as("counts-store"));
        // write this new kstream into another Kafka topic named WordsWithCountsTopic
        wordCounts.toStream().to(TEST_OUT_WORD, Produced.with(Serdes.String(), Serdes.Long()));

        // inspect what kinds of topology is created
        final Topology topology = builder.build();
        streams1 = new KafkaStreams(topology, props);
        streams1.start();

    }

    private Properties getStreamProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "processor1");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.4.26:9092");
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        // provide defualt serdes(serializer and deserializer)
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        return props;
    }

    /**
     * 为了从主题中读取无限制的数据流，我们需要创建一个小型应用程序，以发送无限制的数据流。我们模拟一条Tweet流，
     * 在Tweet末尾恰好有一个标签。每秒都会在该主题上发布一条消息。Tweets始终包含相同的消息（Lorem ipsum…），
     * 主题标签是从5个主题标签的固定列表中随机选择的。
     */
    @GetMapping("/sendMessages")
    public void sendMessages() {
// create instance for properties to access producer configs
        Properties props = new Properties();

        // Assign localhost id, 参考http://kafka.apache.org/documentation/#producerapi
        props.put("bootstrap.servers", "192.168.4.26:9092");
        // Set acknowledgements for producer requests.
        props.put("acks", "all");
        // If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        // Specify buffer size in config
        props.put("batch.size", 16384);
        // Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        // The buffer.memory controls the total amount of memory available to the
        // producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            while (true) {
                // Every second send a message
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
                randomMessage = loremIpsum + " " + hashTags[randomNumber.nextInt(hashTags.length)];
                producer.send(new ProducerRecord<String, String>(TEST_IN, null, randomMessage));
            }
        } finally {
            producer.close();
        }

    }
}
