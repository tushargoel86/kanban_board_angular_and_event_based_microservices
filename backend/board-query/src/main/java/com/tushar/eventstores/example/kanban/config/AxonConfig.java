package com.tushar.eventstores.example.kanban.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

	
	@RabbitListener(queues = "${queue_name}")
	public void receiveMessageFromTopic1(String message) {
	    System.out.println("Received topic 1 message: " + message);
	}
	
//	@Bean
//	public EventSourcingRepository<Board> boardAggregrateEventSourcingRepository(EventStore eventStore) {
//		return EventSourcingRepository.builder(Board.class)
//									  .eventStore(eventStore)
//									  .build();
//	}

//	@Bean
//	public SpringAMQPMessageSource complaintEventsMethod(Serializer serializer) {
//		DefaultAMQPMessageConverter build = new DefaultAMQPMessageConverter(serializer);
//		return new SpringAMQPMessageSource(build) {
//			@RabbitListener(queues =  "${queue_name}")
//			@Override
//			public void onMessage(Message message, Channel channel)  {
//				super.onMessage(message, channel);
//			}
//		};
//		return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {
//
//			@RabbitListener(queues =  "${axon.amqp.exchange}")
//			@Override
//			public void onMessage(Message message, Channel channel)  {
//				super.onMessage(message, channel);
//			}
//		};
//	}
}
