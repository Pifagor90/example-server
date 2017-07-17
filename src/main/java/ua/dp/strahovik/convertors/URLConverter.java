package ua.dp.strahovik.convertors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.net.MalformedURLException;
import java.net.URL;

@FacesConverter(value = "ua.dp.strahovik.convertors.URLConverter", forClass = URL.class)
public class URLConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.toString().equals("")){
            return null;
        }
        StringBuilder url = new StringBuilder();
        String urlValue = value.toString();

        if (!urlValue.startsWith("http://", 0)) {
            url.append("http://");
        }
        url.append(urlValue);
        try {
            return new URL(url.toString());
        } catch (MalformedURLException e) {
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to URL", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
