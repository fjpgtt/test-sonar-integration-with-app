package pixee;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    private static int myIntegerValue;

    public static void main(String[] args) {
        myIntegerValue = Integer.parseInt("3");
    }

    public void log(){
        logger.info(String.format("Processing annotations %d", myIntegerValue));
    }

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void unzipFromHttpResponse(HttpResponse<InputStream> response, final Path pathDestination) throws IOException {
        try (final InputStream inputStream = response.body();
             final ZipInputStream zipInputStream =
                     ZipSecurity.createHardenedInputStream(new BufferedInputStream(inputStream))) {

            final byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    final Path outputPath = pathDestination.resolve(zipEntry.getName());
                    Files.createDirectories(outputPath.getParent());
                    try (final OutputStream outputStream = Files.newOutputStream(outputPath)) {
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, len);
                        }
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }
}
