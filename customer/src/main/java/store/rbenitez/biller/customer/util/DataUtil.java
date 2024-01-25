package store.rbenitez.biller.customer.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import store.rbenitez.biller.customer.entities.Customer;

import javax.swing.text.DateFormatter;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public final class DataUtil {

    public DataUtil() {
    }

    public static Integer LEVEL = 2;

    /**
     * Get date String with format is 8601.
     * @param format format date
     * @return String
     */
    public static String getCurrentDate(String format){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return now.format(dtf);
    }

}
