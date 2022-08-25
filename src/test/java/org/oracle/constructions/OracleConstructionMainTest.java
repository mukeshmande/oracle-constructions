package org.oracle.constructions;

import org.junit.jupiter.api.Test;
import org.oracle.constructions.beans.CustomerDetailsBean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OracleConstructionMainTest  {


    OracleConstructionsMain oracleConstructionsMain = new OracleConstructionsMain();


    @Test
    public void testMain() throws IOException {

        ByteArrayInputStream in = new ByteArrayInputStream("1223456,2345,us_west,BlueTeam,ProjectBanana,2211s".getBytes());
        System.setIn(in);

        oracleConstructionsMain.main(new String[0]);
    }


    @Test
    public  void testGenerateReport() {

        CustomerDetailsBean customerDetailsBean = new CustomerDetailsBean();
        customerDetailsBean.setCustomerId(2343225);
        customerDetailsBean.setContractId(2345);
        customerDetailsBean.setGeozone("us_east");
        customerDetailsBean.setTeamcode("RedTeam");
        customerDetailsBean.setProjectcode("ProjectApple");
        customerDetailsBean.setBuildduration("3445s");
        List<CustomerDetailsBean> list = new ArrayList<>();
        list.add(customerDetailsBean);

        oracleConstructionsMain.generateReport(list);

    }

}
