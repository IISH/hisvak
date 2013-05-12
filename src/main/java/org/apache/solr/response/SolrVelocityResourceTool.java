package org.apache.solr.response;


import org.apache.velocity.tools.ConversionUtils;
import org.apache.velocity.tools.generic.ResourceTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

final class SolrVelocityResourceTool extends ResourceTool {

    private static final Logger log = LoggerFactory.getLogger(SolrVelocityResourceTool.class);

    private ClassLoader solrClassLoader;
    private String lang;

    public SolrVelocityResourceTool(ClassLoader cl, String lang) {
        solrClassLoader = cl;
        this.lang = lang;
    }

    @Override
    protected ResourceBundle getBundle(String baseName, Object loc) {
        final Locale locale = toLocaleParams(loc);
        if (baseName == null || locale == null) {
            log.warn("Unable to determine the locale and resource bundle.");
            return null;
        }
        return ResourceBundle.getBundle(baseName, locale, solrClassLoader);
    }

    private Locale toLocaleParams(Object loc) {
        if (lang == null) return (loc == null) ? getLocale() : toLocale(loc);
        return new Locale(lang);
    }

    // Why did Velocity Tools make this private?  Copied from ResourceTools.java
    private Locale toLocale(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Locale) {
            return (Locale) obj;
        }
        String s = String.valueOf(obj);
        return ConversionUtils.toLocale(s);
    }
}