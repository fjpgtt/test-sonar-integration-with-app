package pixee;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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

    public static void unzipFromHttpResponse(HttpResponse<InputStream> response, Path destDir) throws IOException {
        try (InputStream inputStream = response.body();
             ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream))) {

            byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    Path outputPath = destDir.resolve(zipEntry.getName());
                    Files.createDirectories(outputPath.getParent());
                    try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
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


    public void downloadCodeWorkspace(final URL codeArchiveURL, final Path pathDestination)
            throws IOException, URISyntaxException, InterruptedException {

        final HttpRequest request = HttpRequest.newBuilder().uri(codeArchiveURL.toURI()).GET().build();
        final HttpResponse<InputStream> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        if (response.statusCode() != OK.getStatusCode()) {
            throw new IllegalStateException("Failed to download file from " + codeArchiveURL);
        }

        try (final InputStream inputStream = response.body();
             final ZipInputStream zipInputStream =
                     ZipSecurity.createHardenedInputStream(new BufferedInputStream(inputStream))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    final String fileName = zipEntry.getName();
                    final Path outputPath = pathDestination.resolve(fileName);

                    Files.createDirectories(outputPath.getParent());
                    Files.copy(zipInputStream, outputPath, StandardCopyOption.REPLACE_EXISTING);
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }
}
