package Code8;


import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Converter implements Serializable {
    private static final long serialVersionUID = 34L;
    public static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos)) {
            objectOutputStream.writeObject(object);
            return baos.toByteArray();
        }
    }

    public static Object convertFromBytes(byte[] buffer) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
             ObjectInputStream objectInputStream = new ObjectInputStream(bais)){
            Object object = objectInputStream.readObject();
            return object;
        }
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        long time = zonedDateTime.toInstant().toEpochMilli();
        return new Date(time);
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        return localDateTime;
    }
}
