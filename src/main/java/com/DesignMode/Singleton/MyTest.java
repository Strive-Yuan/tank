package com.DesignMode.Singleton;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;


public class MyTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(12.03);
        BigDecimal bigDecimal1 = new BigDecimal(12.030);
        System.out.println(bigDecimal1 == bigDecimal);
        System.out.println(bigDecimal.equals(bigDecimal1));
        ReentrantLock reentrantLock = new ReentrantLock();
    }
    String json = """
            {
                          "items": [
                              {
                                  "transactionId": 1284011,
                                  "email": "josy.Kammogne@ccjk.com",
                                  "fullname": "Josy Kammogne",
                                  "Admin": "yvonne.li@ccjk.com",
                                  "balance": "613.10",
                                  "status": "Rejected",
                                  "description":"2222",
                                  "dateCreated": "2022-11-17T11:37:39+08:00",
                                  "dateUpdated": "2022-12-07T15:42:10+08:00",
                                  "resourceType": "abroad",
                                  "resourceId": "HE66",
                                  "currencyType": "USD",
                                  "actualPaymentDate": "2022-11-17T11:37:39+08:00",
                                  "company": "CCJK CO LIMITED",
                                  "debit": 3087.2275
                              }
                          ],
                          "current_page": 1,
                          "next_page": 0,
                          "previous_page": 0,
                          "total_pages": 1,
                          "records_per_page": 1000
                      }
            """;
//    MarsConvertToCashDTO convertToCash = Jackson.fromJson(json, MarsConvertToCashDTO.class);
//    List<CostConvertToCashDTO> insertList = new ArrayList<>();
//    List<CostConvertToCashDTO> updateList = new ArrayList<>();
//    LocalDate yesterday = LocalDate.of(2022, 11, 17);
//        convertToCash.items.forEach(convertToCashDTO -> {
//        List<CostConvertToCashDTO> allList = costConvertToCashService.findByTransactionId(convertToCashDTO.transactionId);
//        allList.forEach(ctc -> ctc.id = null);
//        CostConvertToCashDTO costConvertToCash = costConvertToCashService.findByRawData(convertToCashDTO.transactionId, false, false);
//        if (costConvertToCash == null) {
//            //如果创建时间和查询时间相同则入库
//            if (convertToCashDTO.dateCreated.toLocalDate().equals(yesterday)) {
//                fillConvertToCash(convertToCashDTO, false, true, false);
//                insertList.add(convertToCashDTO);
//            }
//        } else {
//            //如果创建时间和查询时间不相同，修改时间和查询时间相同则 则认为是修改
//            if (!convertToCashDTO.dateUpdated.toLocalDate().equals(yesterday)) {
//                //如果数据已被锁定则库中数据不变新增一条危险数据入库
//                if (costConvertToCash.locked) {
//                    fillConvertToCash(convertToCashDTO, true, true, true);
//                    if (!allList.contains(convertToCashDTO)) {
//                        insertList.add(convertToCashDTO);
//                    }
//                    //如果数据未被锁定则修改当前库中数据
//                } else {
//                    convertToCashDTO.id = costConvertToCash.id;
//                    convertToCashDTO.paymentTime = costConvertToCash.dateUpdated;
//                    convertToCashDTO.paymentAmount = costConvertToCash.paymentAmount;
//                    convertToCashDTO.dataSourceType = costConvertToCash.dataSourceType;
//                    convertToCashDTO.locked = costConvertToCash.locked;
//                    convertToCashDTO.isFilled = costConvertToCash.isFilled;
//                    convertToCashDTO.isDanger = costConvertToCash.isDanger;
//                    updateList.add(convertToCashDTO);
//                }
//            }
//        }
//    });
//        System.out.println("insertList.size:" + insertList.size());
//        System.out.println("updateList.size:" + updateList.size());
//        costConvertToCashService.batchInsert(insertList);
//        costConvertToCashService.batchUpdate(updateList);
}
