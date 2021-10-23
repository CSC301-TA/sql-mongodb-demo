package ca.utoronto.utm.mcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {
    public static String convert(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static JSONArray resultSetToJSONArray(ResultSet rs) throws Exception {
        JSONArray arr = new JSONArray();
        while (rs.next()) {

            int columns = rs.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();

            for (int i = 0; i < columns; i++)
                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));

            arr.put(obj);
        }
        return arr;
    }
}
