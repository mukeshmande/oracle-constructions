package org.oracle.constructions;


import org.oracle.constructions.beans.CustomerDetailsBean;
import org.oracle.constructions.exception.CustomerDetailsException;
import org.oracle.constructions.utils.ValidationUtility;

import java.io.*;
import java.util.*;

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

        String lineText;
        List<String> inputLines = new ArrayList<>();

        System.out.println("Press Enter key to add next input on next line & press Enter key two times to submit enter");
        System.out.println("Enter Input from here :- ");

//        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
//        while ((lineText = stdin.readLine()) != null && lineText.length()!= 0) {
//            inputLines.add(lineText);
//        }


        List<CustomerDetailsBean> customerDetailsList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
//        for(String line : inputLines){
            String lineData[] = line.split(",");
            if (lineData.length == 6) {
                CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();

                if(ValidationUtility.customerIdValidation(lineData[0]))
                    customerDetailsBean.setCustomerId(Long.parseLong(lineData[0]));

                if(ValidationUtility.contractIdValidation(lineData[1]))
                customerDetailsBean.setContractId(Long.parseLong(lineData[1]));

                if(ValidationUtility.geoZoneValidation(lineData[2]))
                customerDetailsBean.setGeozone(lineData[2]);

                if(ValidationUtility.teamProjectCodeValidation(lineData[3]))
                customerDetailsBean.setTeamcode(lineData[3]);

                if(ValidationUtility.teamProjectCodeValidation(lineData[4]))
                customerDetailsBean.setProjectcode(lineData[4]);

                if(ValidationUtility.buildDurationValidation(lineData[5]))
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
        System.out.println();

        System.out.println("The Number of Unique customer Id for each geo zone");
        uniqueCustomerIdForGeozone.forEach((k,v) ->{
            System.out.println(k+"->"+v.size());
        });
        System.out.println();


        HashMap<String, Long> averageBuidDuration = new HashMap<>();
        avgBuildDurationForGeozone.forEach((k,v) ->{
            long totalBuildTime;
            totalBuildTime = v.stream().mapToLong(time -> Integer.parseInt(time.substring(0, time.lastIndexOf('s')))).sum();
            averageBuidDuration.put(k, totalBuildTime/v.size());
        });

        System.out.println("The average build duration for each geo zone");
        averageBuidDuration.forEach((k,v) -> {
            System.out.println(k+"->"+v);
        });


        System.out.println();
        System.out.println("The list of Unique customer Id for each geo zone");
        uniqueCustomerIdForGeozone.forEach((k,v) ->{
            System.out.println(k+"->"+v);
        });
    }
}
