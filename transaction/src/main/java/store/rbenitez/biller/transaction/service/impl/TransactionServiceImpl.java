package store.rbenitez.biller.transaction.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import store.rbenitez.biller.transaction.client.CustomerClient;
import store.rbenitez.biller.transaction.dto.TransactionDetailDto;
import store.rbenitez.biller.transaction.dto.TransactionDto;
import store.rbenitez.biller.transaction.entities.Transaction;
import store.rbenitez.biller.transaction.entities.TransactionDetail;
import store.rbenitez.biller.transaction.repository.TransactionRepository;
import store.rbenitez.biller.transaction.service.TransactionDetailService;
import store.rbenitez.biller.transaction.service.TransactionService;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailService transactionDetailService;
    private final CustomerClient customerClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionDetailService transactionDetailService, CustomerClient customerClient) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailService = transactionDetailService;
        this.customerClient = customerClient;
    }

    @Override
    public Mono<Transaction> saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Transaction> getTransaction(Integer idTransaction) {
        return transactionRepository.findById(idTransaction);
    }

    /*@Override
    public Mono<Integer> transactionCreated(TransactionDto transactionDto) {
        //Guardar el cliente
        //Guardar transaccion
        //Guardar los trasanctioDetails
        return customerClient.saveCustomer(transactionDto.getCustomerDto()).flatMap(customerDto -> {
            log.info("[Transaction created] <- Register customer: {}",customerDto);
             saveTransaction(buildTransaction(transactionDto)).flatMap(transaction -> {
                 transactionDetailService.saveTransactionDetails(buildTransactionDetail(transactionDto.getTransactionDetailDto(), transaction.getId()));
                 return Mono.just(transaction.getId());
            });
        });
    }*/

    @Override
    public Mono<Integer> transactionCreated(TransactionDto transactionDto) {
        // Guardar el cliente
        // Guardar transacción
        // Guardar los transactionDetails
        return customerClient.saveCustomer(transactionDto.getCustomerDto()).flatMap(customerDto -> {
            log.info("[Transaction created] <- Register customer: {}", customerDto);
            return saveTransaction(buildTransaction(transactionDto))
                    .flatMap(transaction -> {
                        transactionDetailService.saveTransactionDetails(buildTransactionDetail(transactionDto.getTransactionDetailDto(), transaction.getId()));
                        return Mono.just(transaction.getId());
                    });
        });
    }

    public Transaction buildTransaction(TransactionDto dto){
        return Transaction.builder()
                .amount(dto.getAmount())
                .cashOutF(dto.getCashOutF())
                .cashOutN(dto.getCashOutN())
                .changeF(dto.getChangeF())
                .changeN(dto.getChangeN())
                .createdAt(LocalDateTime.now())
                .creditnote_reason_code(dto.getCreditnote_reason_code())
                .creditnoteReference(dto.getCreditnoteReference())
                .currencyCode(dto.getCurrencyCode())
                .payId(dto.getPayId())
                .customerId(cu)
                .build();
    }

    public List<TransactionDetail> buildTransactionDetail(List<TransactionDetailDto> transactionDetailDtos, Integer idTransaction){
        List<TransactionDetail> transactionDetails = new ArrayList<>();
        transactionDetailDtos.forEach(detailDto -> {
            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .transactionId(idTransaction)
                    .baseAmount(detailDto.getBaseAmount())
                    .taxAmount(detailDto.getTaxAmount())
                    .quantity(detailDto.getQuantity())
                    .price(detailDto.getPrice())
                    .description(detailDto.getDescription())
                    .size(detailDto.getSize())
                    .unitOfMeasure(detailDto.getUnitOfMeasure())
                    .eFactUnitOfMeasure(detailDto.getEFactUnitOfMeasure())
                    .createdAt(LocalDateTime.now())
                    .sapCode(detailDto.getSapCode())
                    .eanCode(detailDto.getEanCode())
                    .onuCode(detailDto.getOnuCode())
                    .discountAmount(detailDto.getDiscountAmount())
                    .discountRate(detailDto.getDiscountRate())
                    .discountValue(detailDto.getDiscountValue())
                    .discountPromotionCode(detailDto.getDiscountPromotionCode())
                    .discountCouponCode(detailDto.getDiscountCouponCode())
                    .discountClassCode(detailDto.getDiscountClassCode())
                    .totalAmount(detailDto.getTotalAmount())
                    .build();
            transactionDetails.add(transactionDetail);
        });
        return transactionDetails;
    }
}
