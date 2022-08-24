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
        OracleConstructionsMain main = new OracleConstructionsMain();

        ValidationUtility utility = new ValidationUtility();
        Reader inputString = new StringReader(data);
        BufferedReader reader = new BufferedReader(inputString);
        String line;

        String lineText;
        List<String> inputLines = new ArrayList<>();
        System.out.println("Enter input in following format & sequence:");
        System.out.println("Customer Id ,Contract Id, Geo Zone, Team Code, Project Code, Build Duration\n");
        System.out.println("Press Enter key to add next input on next line & press Enter key two times to submit input\n");
        System.out.println("Enter Input from here :- ");

//        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
//        while ((lineText = stdin.readLine()) != null && lineText.length()!= 0) {
//            inputLines.add(lineText);
//        }


        List<CustomerDetailsBean> customerDetailsList = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
//        for(String line : inputLines){
                String lineData[] = line.split(",");

                CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();

                if (utility.customerIdValidation(lineData[0]) && utility.contractIdValidation(lineData[1])
                        && utility.geoZoneValidation(lineData[2]) && utility.teamProjectCodeValidation(lineData[3])
                        && utility.teamProjectCodeValidation(lineData[4]) && utility.buildDurationValidation(lineData[5])) {
                    customerDetailsBean.setCustomerId(Long.parseLong(lineData[0]));
                    customerDetailsBean.setContractId(Long.parseLong(lineData[1]));
                    customerDetailsBean.setGeozone(lineData[2]);
                    customerDetailsBean.setTeamcode(lineData[3]);
                    customerDetailsBean.setProjectcode(lineData[4]);
                    customerDetailsBean.setBuildduration(lineData[5]);

                    customerDetailsList.add(customerDetailsBean);
                }
            }
            main.generateReport(customerDetailsList);
        } catch (CustomerDetailsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateReport(List<CustomerDetailsBean> customerDetailsList) {
        HashMap<Long, HashSet<Long>> uniqueCustomerIdForContractId = new HashMap<>();
        HashMap<String, HashSet<Long>> uniqueCustomerIdForGeozone = new HashMap<>();
        HashMap<String, List<String>> avgBuildDurationForGeozone = new HashMap<>();
        customerDetailsList.forEach(customerDetailsBean -> {
            HashSet<Long> uniqueCustforContract = uniqueCustomerIdForContractId.getOrDefault(customerDetailsBean.getContractId(), new HashSet<>());
            uniqueCustforContract.add(customerDetailsBean.getCustomerId());
            uniqueCustomerIdForContractId.put(customerDetailsBean.getContractId(), uniqueCustforContract);

            HashSet<Long> uniqueCustforGeozone = uniqueCustomerIdForGeozone.getOrDefault(customerDetailsBean.getGeozone(), new HashSet<>());
            uniqueCustforGeozone.add(customerDetailsBean.getCustomerId());
            uniqueCustomerIdForGeozone.put(customerDetailsBean.getGeozone(), uniqueCustforGeozone);

            List<String> uniqueBuildforGeozone = avgBuildDurationForGeozone.getOrDefault(customerDetailsBean.getGeozone(), new ArrayList<>());
            uniqueBuildforGeozone.add(customerDetailsBean.getBuildduration());
            avgBuildDurationForGeozone.put(customerDetailsBean.getGeozone(), uniqueBuildforGeozone);
        });
        System.out.println("The Number of Unique customer Id for each contract Id:");
        System.out.println("Contract Id, Customer Id");
        uniqueCustomerIdForContractId.forEach((k, v) -> {
            System.out.println(k + ", " + v.size());
        });
        System.out.println();

        System.out.println("The Number of Unique customer Id for each geo zone:");
        System.out.println("Geo Zone, Customer Id");
        uniqueCustomerIdForGeozone.forEach((k, v) -> {
            System.out.println(k + ", " + v.size());
        });
        System.out.println();

        HashMap<String, Long> averageBuidDuration = new HashMap<>();
        avgBuildDurationForGeozone.forEach((k, v) -> {
            long totalBuildTime;
            totalBuildTime = v.stream().mapToLong(time -> Integer.parseInt(time.substring(0, time.lastIndexOf('s')))).sum();
            averageBuidDuration.put(k, totalBuildTime / v.size());
        });
        System.out.println("The average build duration in seconds for each geo zone:");
        System.out.println("Geo Zone, Average Build Duration");
        averageBuidDuration.forEach((k, v) -> {
            System.out.println(k + ", " + v);
        });
        System.out.println();

        System.out.println("The list of Unique customer Id for each geo zone:");
        System.out.println("Geo Zone, Customer Id");
        uniqueCustomerIdForGeozone.forEach((k, v) -> {
            System.out.println(k + ", " + v);
        });
    }
}
