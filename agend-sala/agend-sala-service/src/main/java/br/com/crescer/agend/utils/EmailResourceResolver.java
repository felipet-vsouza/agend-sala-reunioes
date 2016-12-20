package br.com.crescer.agend.utils;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.context.Context;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.util.ClassLoaderUtils;
import org.thymeleaf.util.Validate;

public class EmailResourceResolver implements IResourceResolver {

    public EmailResourceResolver() {
        super();
    }

    @Override
    public String getName() {
        return getClass().getName().toUpperCase();
    }

    @Override
    public InputStream getResourceAsStream(TemplateProcessingParameters params, String resourceName) {
        Validate.notNull(resourceName, "Resource name cannot be null");
        if (StringContext.class.isAssignableFrom(params.getContext().getClass())) {
            String content = ((StringContext) params.getContext()).getContent();
            return IOUtils.toInputStream(content);
        }
        return ClassLoaderUtils.getClassLoader(ClassLoaderResourceResolver.class).getResourceAsStream(resourceName);
    }

    public static class StringContext extends Context {

        private final String content;

        public StringContext(String content) {
            this.content = content;
        }

        public StringContext(String content, Locale locale) {
            super(locale);
            this.content = content;
        }

        public StringContext(String content, Locale locale, Map<String, ?> variables) {
            super(locale, variables);
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }
}
