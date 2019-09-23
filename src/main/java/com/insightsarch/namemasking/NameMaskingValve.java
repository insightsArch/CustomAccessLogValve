package com.insightsarch.namemasking;

import java.io.CharArrayWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.catalina.valves.AccessLogValve;

/**
 * The Class NameMaskingValve responsible for masking the name paramater which is a part of request URIs and getting
 * logged in tomcat's access logs. So here, we are logging only first 4 characters of the name and rest characters will
 * be masked, which will be replaced by '****'.
 * 
 * @author insightsarch
 */
public class NameMaskingValve extends AccessLogValve {

    public NameMaskingValve() {
    }

    @Override
    public void log(CharArrayWriter charArrMessage) {
        try {
            String message = charArrMessage.toString();
            // Pattern to get the request URIs from log message present in double quotes
            Pattern pattern = Pattern.compile(".*\\\"(.*)\\\".*");
            Matcher matcher = pattern.matcher(message);
            String requestURI = null;
            if (matcher.find()) {
                requestURI = matcher.group(1);
                if (requestURI != null && !requestURI.isEmpty()) {
                    // Pattern for finding name parameter from URI
                    final String NAME_PATTERN = "(?<=(?i)name=)(.*?)(\\&|\\s)";
                    Pattern namePattern = Pattern.compile(NAME_PATTERN);
                    Matcher nameMatcher = namePattern.matcher(requestURI);
                    while (nameMatcher.find()) {
                        if (nameMatcher.group() != null && !nameMatcher.group().isEmpty()) {
                            final String name = nameMatcher.group().replace("&", "").trim();
                            String maskedName = null;

                            // only 4 characters are logged and remaining are masked
                            char[] nameArr = name.toCharArray();
                            for (int i = 4; i < nameArr.length; i++) {
                                nameArr[i] = '*';
                            }
                            maskedName = String.valueOf(nameArr);
                            message = message.replaceAll("\\b" + name + "\\b", maskedName);
                        }
                    }
                }
            }
            CharArrayWriter maskedMessage = new CharArrayWriter();
            maskedMessage.write(message);
            super.log(maskedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
