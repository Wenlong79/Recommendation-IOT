package com.wl.controller;

import com.wl.entity.Data;
import com.wl.entity.Page;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class DeviceController {

    WebClient webClient = WebClient.create();

    String baseURL = "https://jsonmock.hackerrank.com/api/iot_devices/search?page=";

    List<Data> devices = new ArrayList<>();

    public DeviceController() {
        buildDeviceList();
    }

    @GetMapping("/hi")
    public String greeting() {
        return "Hello! Check IOT devices";
    }

    //check a specific page
    @GetMapping("/page/{pageNum}")
    public void toPage(HttpServletResponse response, @PathVariable int pageNum) throws IOException {
        response.sendRedirect(baseURL + pageNum);
    }

    //check data(devices) in a specific page
    @GetMapping("/page/{pageNum}/data")
    public List<Data> showData(@PathVariable int pageNum) {
        String apiUrl = "https://jsonmock.hackerrank.com/api/iot_devices/search?page=" + pageNum;
        Page pageObject = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        return pageObject.getData();
    }


    public Integer getPageAmount() {
        String apiUrl = "https://jsonmock.hackerrank.com/api/iot_devices/search?page=1";
        // Make the API request and get the JSON response as a Flux (reactive)
        Page pageObject = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        //System.out.println(pageObject.getTotal_pages());
        return pageObject.getTotal_pages();
    }

    @GetMapping("/pageAmount")
    public String pageAmount() {
        Integer pageAmount = getPageAmount();
        return "Total page amount :" + pageAmount;
    }

    public Page getPageObject(int pageNum) {
        String baseURL = "https://jsonmock.hackerrank.com/api/iot_devices/search?page=" + pageNum;
        Page pageObject = webClient.get()
                .uri(baseURL)
                .retrieve()
                .bodyToMono(Page.class)
                .block(); // blocking to get the result synchronously
        return pageObject;
    }

    public void buildDeviceList() {
        int pageAmount = getPageAmount();
        for (int i = 1; i <= pageAmount; i++) {
            Page page = getPageObject(i);
            //System.out.println(page.getData().size());
            List<Data> thisSet = page.getData();
            for (Data device : thisSet) {
                devices.add(device);
            }
        }
    }

    @GetMapping("/devices")
    public List<Data> showAllDevices() {
        return devices;
    }


    @GetMapping("/devices/{status}/{threshold}/{monthYear}")
    public List<Data> showSelectedDevices(@PathVariable String status, @PathVariable int threshold, @PathVariable String monthYear) {
        //[04-2019] only in this format
        Long[] timeStampRange = getTimestampRange(monthYear);

        List<Data> result = new ArrayList<>();

        for (Data device : devices) {
            if (device.getStatus().equals(status) && device.getOperatingParams().getRootThreshold() > threshold &&
                    (device.getTimestamp() > timeStampRange[0] && device.getTimestamp() < timeStampRange[1])) {
                result.add(device);
            }
        }
        return result;
    }

    @GetMapping("/{status}/{threshold}/{monthYear}")
    public Integer showSelectedDevicesAmount(@PathVariable String status, @PathVariable int threshold, @PathVariable String monthYear) {
        //[04-2019] only in this format
        Long[] timeStampRange = getTimestampRange(monthYear);

        List<Data> result = new ArrayList<>();

        for (Data device : devices) {
            if (device.getStatus().equals(status) && device.getOperatingParams().getRootThreshold() > threshold &&
                    (device.getTimestamp() > timeStampRange[0] && device.getTimestamp() < timeStampRange[1])) {
                result.add(device);
            }
        }
        return result.size();
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

}
