package org.oracle.constructions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.oracle.constructions.beans.CustomerDetailsBean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OracleConstructionMainTest  {

    @Mock
    OracleConstructionsMain oracleConstructionsMain;

    public static String data = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
            "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
            "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
            "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
            "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\n";

    @BeforeEach
    void init(){
    oracleConstructionsMain = Mockito.mock(OracleConstructionsMain.class);
}


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

//        generateReport(list);

        Mockito.verify(oracleConstructionsMain).generateReport(list);

    }

}
