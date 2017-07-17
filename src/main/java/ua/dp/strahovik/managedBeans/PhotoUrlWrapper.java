package ua.dp.strahovik.managedBeans;


import java.net.URL;

public class PhotoUrlWrapper {
    private URL url;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PhotoUrlWrapper{" +
                "url=" + url +
                '}';
    }
}
