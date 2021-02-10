package gz.example.common.util;

public class StringToJson {

    public static String toJsonString(String theString) {
        theString = theString.replace(">", "&gt;");
        theString = theString.replace("<", "&lt;");
        theString = theString.replace(" ", "&nbsp;");
        theString = theString.replace("\"", "&quot;");
        theString = theString.replace("\'", "&#39;");
        theString = theString.replace("\\", "\\\\");//对斜线的转义
        theString = theString.replace("\n", "\\n");
        theString = theString.replace("\r", "\\r");

        return theString;

    }

    /**
     * JSON字符串特殊字符处理，比如：“\A1;1300”
     * @param s
     * @return String
     */
    public static String string2Json(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

}