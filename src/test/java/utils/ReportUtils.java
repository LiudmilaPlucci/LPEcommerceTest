package utils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportUtils {

    private static String LINE = "\n" + "-".repeat(100);
    private static String LONG_TABS = "\t".repeat(10);
    private static String SHORT_TABS = "\t".repeat(8);

    public static void logLine() {
        LoggerUtils.logInfo(LINE);
    }

    public static void logReportHeader() {
        final String header =
                """
                
                
                %sTEST RUN
                %sDATA: %s
                """.formatted(LONG_TABS, SHORT_TABS, getLocalDAteTime());
        LoggerUtils.logInfo(
               LINE + header + LINE
        );
    }

    private static String getLocalDAteTime(){
        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-dd-MM, HH:mm");

        return dateTimeNow.format(dateTimeFormat);
    }

    public static void logTestName(Method method) {
String testInfo = """
        Run Class: %s""".formatted((method.getDeclaringClass().getSimpleName() + " test: " + method.getName()));

       LoggerUtils.logInfo(testInfo);
    }

}
