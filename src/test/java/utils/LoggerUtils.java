package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtils {
    private static final String ERROR = "❌";
    private static final String SUCCESS = "✅";
    private static final String WARNING = "⚠️";
    private static final String EXCEPTION = "❗️";

    private static final Logger logger = LogManager.getLogger(LoggerUtils.class.getSimpleName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.error(ERROR + message);
    }

    public static void logFatal(String message) {
        logger.fatal(EXCEPTION + ERROR + WARNING + message);
    }

    public static void logWarning(String message) {
        logger.warn(WARNING + message);
    }
}
