package gnu.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ResourceStreamHandler extends URLStreamHandler {
    public static final String CLASS_RESOURCE_URI_PREFIX = "class-resource:/";
    public static final int CLASS_RESOURCE_URI_PREFIX_LENGTH = 16;
    ClassLoader cloader;

    public ResourceStreamHandler(ClassLoader classLoader) {
        this.cloader = classLoader;
    }

    public static URL makeURL(Class cls) throws MalformedURLException {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        StringBuilder sb = new StringBuilder();
        sb.append(CLASS_RESOURCE_URI_PREFIX);
        if (lastIndexOf >= 0) {
            sb.append(name.substring(0, lastIndexOf));
            sb.append('/');
            name = name.substring(lastIndexOf + 1);
        }
        sb.append(name);
        return new URL((URL) null, sb.toString(), new ResourceStreamHandler(cls.getClassLoader()));
    }

    public URLConnection openConnection(URL url) throws IOException {
        String url2 = url.toString();
        String substring = url2.substring(16);
        int indexOf = substring.indexOf(47);
        if (indexOf > 0) {
            substring = substring.substring(0, indexOf).replace('.', '/') + substring.substring(indexOf);
        }
        URL resource = this.cloader.getResource(substring);
        if (resource != null) {
            return resource.openConnection();
        }
        throw new FileNotFoundException(url2);
    }
}
