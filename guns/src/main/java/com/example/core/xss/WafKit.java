package com.example.core.xss;

import java.util.regex.Pattern;

/**
 * Web防火墙工具类
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class WafKit {
    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤XSS脚本内容
     */
    public static String stripXSS(String value) {
        String rlt = null;

        if (null != value) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            rlt = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid anything in a src='...' type of expression
			/*scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			rlt = scriptPattern.matcher(rlt).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			rlt = scriptPattern.matcher(rlt).replaceAll("");*/

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL注入内容
     */
    public static String stripSqlInjection(String value) {
        return (null == value) ? null : value.replaceAll("('.+--)|(--)|(%7C)", ""); //value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL/XSS注入内容
     */
    public static String stripSqlXSS(String value) {
        return stripXSS(stripSqlInjection(value));
    }

}
