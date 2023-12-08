package com.wl;

import com.wl.controller.DeviceController;
import com.wl.entity.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;

@SpringBootTest
class IotRestApplicationTests {
    WebClient webClient = WebClient.create();
    @Test
    void contextLoads() {
        Long time = 1548040103302l;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new Timestamp(time));
        System.out.println(timeStamp);
    }

    @Test
    void checkPageAmount(){
        DeviceController controller = new DeviceController();
        System.out.println(controller.getPageAmount());
    }


    public Page getPageObject(int pageNum) {
        String baseURL = "https://jsonmock.hackerrank.com/api/articles?page=" + pageNum;
        Page pageObject = webClient.get()
                .uri(baseURL)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        return pageObject;
    }

    @Test
    public void showPageObject(){
        System.out.println(getPageObject(1));
    }


    public static Long[] getTimestampRange(String monthYear) {
        // Parse the input month-year string
        String[] parts = monthYear.split("-");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        // Create the first day of the month
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // Calculate the minimum and maximum timestamps for the month
        long minTimestamp = firstDayOfMonth.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        long maxTimestamp = firstDayOfMonth.plusMonths(1).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli() - 1;

        return new Long[]{minTimestamp, maxTimestamp};
    }


    @Test
    public void showTimestampRange(){
        String time = "04-2019";
        Long[] timestampRange = getTimestampRange(time);
        System.out.println(timestampRange[0] + " --- " + timestampRange[1]);
    }

}
