/*
 * The MIT License
 *
 * Copyright 2016 oncore.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.oncore.chhs.service.helper;

import com.oncore.chhs.persistence.entity.Users;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author OnCore LLC
 */
public class MessagesHelper {

    public static List<String> MESSAGES_RESPONSE = Arrays.asList("Thank you for your question.",
            "Thank you for your question. We will get back to you within 5 business days.");

    /**
     * Gets the random responses from predefined texts.
     *
     * @return response
     */
    public static String getRandomResponse() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(MESSAGES_RESPONSE.size());

        return MESSAGES_RESPONSE.get(index);

    }

    /**
     * Formats the name of the user to: First middle last names.
     *
     * @param user
     *
     * @return formatted name
     */
    public static String getFormattedName(Users user) {
        String formattedName = "";

        if (user != null) {
            if (StringUtils.isNotBlank(user.getUsrFirstname())) {
                formattedName = user.getUsrFirstname();
            }
            if (StringUtils.isNotBlank(user.getUsrMiddlename())) {
                formattedName = " ";
                formattedName = user.getUsrMiddlename();
            }
            if (StringUtils.isNotBlank(user.getUsrLastname())) {
                formattedName = " ";
                formattedName = user.getUsrLastname();
            }
        }

        return formattedName;
    }
}
