package org.oracle.constructions;


import org.oracle.constructions.beans.CustomerDetailsBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OracleConstructionsMain {

    public static String data = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
            "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
            "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
            "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
            "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";

    public static void main(String[] args) throws IOException {

        Reader inputString = new StringReader(data);
        BufferedReader reader = new BufferedReader(inputString);
        String line;
        List<CustomerDetailsBean> customerDetailsList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String lineData[] = line.split(",");
            if (lineData.length == 6) {
                CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();
                customerDetailsBean.setCustomerId(Long.parseLong(lineData[0]));
                customerDetailsBean.setContractId(Long.parseLong(lineData[1]));
                customerDetailsBean.setGeozone(lineData[2]);
                customerDetailsBean.setTeamcode(lineData[3]);
                customerDetailsBean.setProjectcode(lineData[4]);
                customerDetailsBean.setBuildduration(lineData[5]);
                customerDetailsList.add(customerDetailsBean);
            }
        }

        generateReport(customerDetailsList);
    }

    public static void generateReport(List<CustomerDetailsBean> customerDetailsList){
        HashMap<Long, HashSet<Long>> uniqueCustomerIdForContractId = new HashMap<>();
        HashMap<String, HashSet<Long>> uniqueCustomerIdForGeozone = new HashMap<>();
        HashMap<String, List<String>> avgBuildDurationForGeozone = new HashMap<>();
        customerDetailsList.forEach(customerDetailsBean -> {
            HashSet<Long> uniqueCustforContract = uniqueCustomerIdForContractId.getOrDefault(customerDetailsBean.getContractId(), new HashSet<>());
            uniqueCustforContract.add(customerDetailsBean.getCustomerId());
            uniqueCustomerIdForContractId.put(customerDetailsBean.getContractId(),uniqueCustforContract);

            HashSet<Long> uniqueCustforGeozone = uniqueCustomerIdForGeozone.getOrDefault(customerDetailsBean.getGeozone(), new HashSet<>());
            uniqueCustforGeozone.add(customerDetailsBean.getCustomerId());
            uniqueCustomerIdForGeozone.put(customerDetailsBean.getGeozone(), uniqueCustforGeozone);

            List<String> uniqueBuildforGeozone = avgBuildDurationForGeozone.getOrDefault(customerDetailsBean.getGeozone(), new ArrayList<>());
            uniqueBuildforGeozone.add(customerDetailsBean.getBuildduration());
            avgBuildDurationForGeozone.put(customerDetailsBean.getGeozone(), uniqueBuildforGeozone);


        });



        System.out.println("The Number of Unique customer Id for each contract Id");
        uniqueCustomerIdForContractId.forEach((k,v) ->{
            System.out.println(k+"->"+v.size());
        });


        System.out.println("The Number of Unique customer Id for each geo zone");
        uniqueCustomerIdForGeozone.forEach((k,v) ->{
            System.out.println(k+"->"+v.size());
        });


        System.out.println("The average build duration for each geo zone");
        avgBuildDurationForGeozone.forEach((k,v) ->{
            System.out.println(k+"->"+v);
        });


        System.out.println("The list of Unique customer Id for each geo zone");
        uniqueCustomerIdForGeozone.forEach((k,v) ->{
            System.out.println(k+"->"+v);
        });
    }
}
