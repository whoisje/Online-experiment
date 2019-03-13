package com.jack.aicore.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

/**
 * @author wangwj
 * @data 2018/12/22
 */
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String wxSessionCodeGet(String url) {
        String result = null;
        InputStream in = null;
        try {
            URL url1 = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Stream<String> stream = reader.lines();
            result = stream.reduce("", (a, b) -> a + b);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());

                }
            }

        }
        return result;

    }

    public static String executePython(String code) {
        String result = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            URL url = new URL("https://m.runoob.com/api/compile.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            out = urlConnection.getOutputStream();
            PrintWriter writer = new PrintWriter(out);
            writer.write("code=" + code);
            writer.flush();
            int resultCode = urlConnection.getResponseCode();
            if (resultCode == 200) {
                in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                Stream<String> stream = reader.lines();
                result = stream.reduce("", (a, b) -> a + b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());

                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());

                }
            }
        }
        return result;
    }
}
