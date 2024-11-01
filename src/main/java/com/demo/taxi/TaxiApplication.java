package com.demo.taxi;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class TaxiApplication {

	@Autowired
	public TaxiApplication(ConsumerService consumerService, ProducerService producerService) {
		this.consumerService = consumerService;
		this.producerService = producerService;
	}

	public ConsumerService consumerService;
	public static ProducerService producerService;

	public static void main(String[] args) {
		SpringApplication.run(TaxiApplication.class);
		// Параметры Kafka
		String bootstrapServers = "192.168.0.197:9092";
		String topic = "my-topic";

		// Конфигурация производителя
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());



		// Запуск 100 клиентов в отдельных потоках
		for (int i = 0; i < 1000; i++) {
			int finalI = i;
			new Thread(() -> {
				// Создание производителя
				KafkaProducer<String, String> producer = new KafkaProducer<>(props);
				while (true) {
					// Создание сообщения
					String message = "Сообщение от клиента " + (finalI + 1);

					// Отправка сообщения
					producer.send(new ProducerRecord<>(topic, message));

					// Пауза на 1 секунду
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		// Закрытие производителя
		//producer.close();
	}
}

