package biz.netcentric.parse;

import biz.netcentric.engine.TemplateContext;

/**
 * Created by hernan on 5/18/17.
 */
@FunctionalInterface
public interface Command {
    String process(String node, TemplateContext context);
}
