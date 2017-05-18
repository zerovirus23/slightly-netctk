package biz.netcentric.engine;

import biz.netcentric.model.Person;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by hernan on 5/17/17.
 */
public class Template {
    private static final String RHINO_COMPAT_LIB = "load('nashorn:mozilla_compat.js');\n";
    private static final String SCRIPT_SELECTOR = "script[type=\"server/javascript\"]";
    private static final String PERSON_VAR_NAME = "person";

    private TemplateContext context;
    private Document document;

    /**
     *
     */
    public Template(InputStream templateStream) throws IOException, ScriptException {
        context = new TemplateContext();
        document = Jsoup.parse(templateStream, StandardCharsets.UTF_8.name(), StringUtils.EMPTY);
    }

    /**
     *
     * @return
     * @throws ScriptException
     */
    private Person evaluateScript(TemplateEngine engine) throws ScriptException {
        Element scriptNode = document.select(SCRIPT_SELECTOR).first();
        String script = RHINO_COMPAT_LIB + scriptNode.html();
        engine.addAttribute(PERSON_VAR_NAME, Person.defaultPerson);
        engine.eval(script);
        return (Person) engine.getAttribute(PERSON_VAR_NAME);
    }

    public String build(TemplateEngine engine) throws ScriptException, IOException {
        Person person = evaluateScript(engine);
        context.put(TemplateContext.PERSON_ATTRIBUTE_NAME, person);
        parseInterpolation();
        parseConditionals();
        parseLoops();
        return document.toString();
    }

    private void parseInterpolation(){
    }

    private void parseLoops(){
    }

    private void parseConditionals(){
    }
}
