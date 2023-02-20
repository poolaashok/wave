package Test;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class dataProvider {
    @DataProvider(name = "products")
    public static Object[][] products() {
        return new Object[][]{{"Moto G (Black, with 8 GB)","195"}, {"HP Pavilion 15-n039TX Laptop","562"},{"Canon PowerShot SX160 IS Point & Shoot","143"}};

    }
}
//        List<String>productsList = new ArrayList<String>();
//        productsList.add("//label[text()='Samsung Galaxy Grand 2 (White)']");
//        productsList.add("//label[text()='Sony Cyber-shot DSC-H300 Advance Point and shoot']");
//        productsList.add("//label[text()='HP Pavilion 15-n039TX Laptop']");
//        productsList.add("//label[text()='Netgear Wireless-N 150 Router (WNR612)']");
//        return new Object[][]{{
//            productsList
//        }};
//    }
//}
