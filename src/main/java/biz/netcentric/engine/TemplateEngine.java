package biz.netcentric.engine;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * Created by hernan on 5/17/17.
 */
public class TemplateEngine {
    public static final String REQUEST_ATTRIBUTE_NAME = "request";
    private static final String SCRIPT_ENGINE_NAME = "nashorn";
    private ScriptEngine scriptEngine;

    /**
     *
     * @throws IOException
     */
    public TemplateEngine() throws IOException {
        scriptEngine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);
    }

    /**
     *
     * @param name
     * @param object
     */
    public void addAttribute(String name, Object object) {
        ScriptContext context = scriptEngine.getContext();
        context.setAttribute(name, object, ScriptContext.ENGINE_SCOPE);
    }

    /**
     *
     * @param script
     * @return
     * @throws ScriptException
     */
    public Object eval(String script) throws ScriptException {
        return scriptEngine.eval(script);
    }

    /**
     *
     * @param name
     * @return
     */
    public Object getAttribute(String name) {
        return scriptEngine.get(name);
    }
}
