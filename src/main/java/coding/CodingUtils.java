package coding;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public final class CodingUtils {

    public static String streamToString(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer);
        return writer.toString();
    }

}


