package by.bsu.grabar.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The type Date tag.
 */
@SuppressWarnings("serial")
public class DateTag extends TagSupport {
    private String language;

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int doStartTag() throws JspException {

        int style = DateFormat.FULL;
        DateFormat dateFormat;
        if (language.equals("en_EN")){
            dateFormat = DateFormat.getDateInstance(style, Locale.ENGLISH);
        }else {
            dateFormat = DateFormat.getDateInstance(style, new Locale("ru", "RU"));
        }
        Date date = new Date();
        String time = dateFormat.format(date);
        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
