import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task_4 {
    public static void main(String[] args) {
        String[] ipAddresses = {
                "192.168.1.1",
                "255.255.255.255",
                "0.0.0.0",
                "256.100.50.25",
                "192.168.1",
                "192.168.1.1.1",
                "abc.def.gh.ij"
        };

        for (String ip : ipAddresses) {
            try {
                boolean isValid = validateIP(ip);
                System.out.println("IP-адрес [" + ip + "] -> " + (isValid ? "ВАЛИДЕН" : "НЕВАЛИДЕН"));
            } catch (Exception e) {
                System.err.println("Ошибка валидации для " + ip + ": " + e.getMessage());
            }
        }
    }

    public static boolean validateIP(String ip) {
        if (ip == null) {
            return false;
        }

        // Регулярное выражение для одного числа (октет) от 0 до 255:
        String byteRegex = "(25[0-5]|2[0-4]\\d|[01]?\\d?\\d)";

        String ipRegex = "^" + byteRegex + "\\." + byteRegex + "\\." + byteRegex + "\\." + byteRegex + "$";
        Pattern pattern = Pattern.compile(ipRegex);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}