/**
 * Copyright (c) 2008, MTV Networks
 */

package com.mtvi.plateng.hudson.regex;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Class to store configuration for the plugin.
 * 
 * @author edelsonj
 * 
 */
public class Configuration {

    /**
     * A logger object.
     */
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

    /**
     * The expression used to parse a user's username. Expected to be a regular
     * expression.
     */
    private String userNameExpression;

    /**
     * The format string used to create an email address.
     * 
     * @see http://java.sun.com/j2se/1.5.0/docs/api/java/util/Formatter.html#syntax
     */
    private String emailAddressPattern;

    /**
     * The compiled regular expression Pattern.
     */
    private Pattern userNamePattern;

    public String getEmailAddressPattern() {
        return emailAddressPattern;
    }

    public String getUserNameExpression() {
        return userNameExpression;
    }

    /**
     * Determine if this configuration object is valid.
     * 
     * @return true if this object is valid
     */
    public boolean isValid() {
        getUserNamePattern();
        return (userNamePattern != null) && (emailAddressPattern != null);
    }

    public void setEmailAddressPattern(String emailAddressPattern) {
        this.emailAddressPattern = emailAddressPattern;
    }

    public void setUserNameExpression(String userNameExpression) {
        this.userNameExpression = userNameExpression;
    }

    /**
     * If necessary, compile the userNameExpression property into a RegEx
     * pattern.
     * 
     * @return the compiled Pattern.
     */
    protected Pattern getUserNamePattern() {
        if (userNamePattern == null && userNameExpression != null) {
            try {
                userNamePattern = Pattern.compile(userNameExpression);
            } catch (PatternSyntaxException e) {
                LOGGER.log(Level.WARNING, "Bad username expression: " + userNameExpression, e);
            }
        }
        return userNamePattern;
    }

}
