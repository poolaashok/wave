package Test;

import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class dataProvider {
    @DataProvider(name = "products")
    public static Object[][] products() {
            return new Object[][]{{"Moto G (Black, with 8 GB)","195"},
                    {"HP Pavilion 15-n039TX Laptop","562"},
                    {"Canon PowerShot SX160 IS Point & Shoot","143"}};
        }
    }
//        HashMap<Integer, String> products = new HashMap<Integer, String>();
//        products.put(1, "Moto G (Black, with 8 GB)");
//        products.put(2, "HP Pavilion 15-n039TX Laptop");
//        products.put(3, "Canon PowerShot SX160 IS Point & Shoot");
//        return new Object[][]{{
//            products
//        }};
//    }
//}


