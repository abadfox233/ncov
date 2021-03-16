package cn.yogehaoren.common;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangNing yogehaoren@gamil.com <br>
 * @since 1.0
 */
public class CookieManager implements CookieJar {

    private final Map<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
        cookieStore.put(httpUrl.host(),cookies);
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> cookies = cookieStore.get(httpUrl.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public void addCookie(URI uri, Cookie cookie){
        List<Cookie> cookieList = cookieStore.get(uri.getHost())!=null ? cookieStore.get(uri.getHost()):new LinkedList<Cookie>();
        cookieList.add(cookie);
        cookieStore.put(uri.getHost(),cookieList);
    }
}
