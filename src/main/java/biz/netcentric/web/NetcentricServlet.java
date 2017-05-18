package biz.netcentric.web;

import biz.netcentric.engine.Template;
import biz.netcentric.engine.TemplateEngine;
import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hernan on 5/17/17.
 */
@WebServlet(urlPatterns = {"/template"})
@Slf4j
public class NetcentricServlet extends HttpServlet {
    private static final String DEFAULT_TEMPLATE_PATH = "/index.html";
    private static Template template;

    @Override
    public void init() throws ServletException {
        super.init();

        try (InputStream templateStream = getServletContext().getResourceAsStream(DEFAULT_TEMPLATE_PATH)) {
            template = new Template(templateStream);
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TemplateEngine engine = new TemplateEngine();
            engine.addAttribute(TemplateEngine.REQUEST_ATTRIBUTE_NAME, request);
            String html = template.build(engine);
            response.getOutputStream().print(html);
        } catch (ScriptException e) {
            response.getOutputStream().print(e.getMessage());
        }
    }
}
