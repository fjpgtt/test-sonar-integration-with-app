package pixee;

import java.util.logging.Logger;

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
}
