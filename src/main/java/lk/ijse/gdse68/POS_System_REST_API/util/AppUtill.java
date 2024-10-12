package lk.ijse.gdse68.POS_System_REST_API.util;

import java.util.Random;
import java.util.UUID;

public class AppUtill {

    // Method to generate Order ID in the format OXXX
    public static String createOrderId() {
        int randomNum = (int) (Math.random() * 1000);
        return String.format("O%03d", randomNum);  // Ensures format OXXX
    }

    // Method to generate Order Detail ID in the format ODXXX
    public static String createOrderDetailId() {
        int randomNum = (int) (Math.random() * 1000);
        return String.format("OD%03d", randomNum);  // Ensures format ODXXX
    }

}
