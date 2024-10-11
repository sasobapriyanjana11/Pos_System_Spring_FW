package lk.ijse.gdse68.POS_System_REST_API.util;

import java.util.Random;
import java.util.UUID;

public class AppUtill {

    // Method to generate UUID-based Order ID
    public static String createOrderId() {
        // Extract a portion of the UUID's hashcode (numeric only) and prefix with 'O'
        return "O" + Math.abs(UUID.randomUUID().hashCode());  // "O" + numeric portion
    }

    // Method to generate UUID-based Order Detail ID
    public static String createOrderDetailId() {
        return "OD" + Math.abs(UUID.randomUUID().hashCode());  // "OD" + numeric portion
    }
}
