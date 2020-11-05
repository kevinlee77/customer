package bikeseller;

import bikeseller.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerViewHandler {


    @Autowired
    private CustomerRepository customerRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        System.out.println("insert order");
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                Customer customer = new Customer();
                // view 객체에 이벤트의 Value 를 set 함
                customer.setOrderId(ordered.getId());
                customer.setItem(ordered.getItem());
                customer.setQty(ordered.getQty());
                customer.setPrice(ordered.getPrice());
                customer.setStatus(ordered.getStatus());
                // view 레파지 토리에 save
                customerRepository.save(customer);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCompleted_then_UPDATE_1(@Payload PayCompleted payCompleted) {
        System.out.println("update status pay completed");
        try {
            if (payCompleted.isMe()) {
                // view 객체 조회
                List<Customer> customerList = customerRepository.findByOrderId(payCompleted.getOrderId());
                for(Customer customer : customerList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customer.setStatus(payCompleted.getProcess());
                    // view 레파지 토리에 save
                    customerRepository.save(customer);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipped_then_UPDATE_2(@Payload Shipped shipped) {
        System.out.println("update status shipped");
        try {
            if (shipped.isMe()) {
                // view 객체 조회
                List<Customer> customerList = customerRepository.findByOrderId(shipped.getOrderId());
                for(Customer customer : customerList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customer.setStatus(shipped.getProcess());
                    // view 레파지 토리에 save
                    customerRepository.save(customer);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCancelled_then_UPDATE_3(@Payload OrderCancelled orderCancelled) {
        System.out.println("update status order cancelled");
        try {
            if (orderCancelled.isMe()) {
                // view 객체 조회
                List<Customer> customerList = customerRepository.findByOrderId(orderCancelled.getId());
                for(Customer customer : customerList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customer.setStatus(orderCancelled.getStatus());
                    // view 레파지 토리에 save
                    customerRepository.save(customer);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCancelled_then_UPDATE_4(@Payload PayCancelled payCancelled) {
        System.out.println("update status pay cancelled");
        try {
            if (payCancelled.isMe()) {
                // view 객체 조회
                List<Customer> customerList = customerRepository.findByOrderId(payCancelled.getOrderId());
                for(Customer customer : customerList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customer.setStatus(payCancelled.getProcess());
                    // view 레파지 토리에 save
                    customerRepository.save(customer);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPromoCompleted_then_UPDATE_5(@Payload PromoCompleted promoCompleted) {
        System.out.println("update status promo completed");
        try {
            if (promoCompleted.isMe()) {
                // view 객체 조회
                List<Customer> customerList = customerRepository.findByOrderId(promoCompleted.getOrderId());
                for(Customer customer : customerList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customer.setPoint(promoCompleted.getPoint());
                    // view 레파지 토리에 save
                    customerRepository.save(customer);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPromoCancelled_then_UPDATE_6(@Payload PromoCancelled promoCancelled) {
        System.out.println("update status promo cancelled");
        try {
            if (promoCancelled.isMe()) {
                // view 객체 조회
                List<Customer> customerList = customerRepository.findByOrderId(promoCancelled.getOrderId());
                for(Customer customer : customerList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    customer.setPoint(promoCancelled.getPoint());
                    // view 레파지 토리에 save
                    customerRepository.save(customer);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}