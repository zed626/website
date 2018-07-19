package com.aiwac.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * 微信过滤表情
 * @author hsw
 *
 */
public class EmojiFilter {

	private static final Logger logger = LogManager.getLogger(EmojiFilter.class);
    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
        	logger.info("filter theInstance(emoji code");
            source = emojiMatcher.replaceAll("*");
            return source;
        }
        return source;
    }
}