package RegularExpression;

class RegularExpression {
    /**
     * First symbol must be any ASCII letter or $ or _
     * if first symbol _ others symbols must be (one _ is considered as false)
     * Other symbols may be only either ASCII letter or underscore or digit     *
     *
     * @return regular expression string
     */
    static String variableName() {
        return "[\\p{Alpha}$][\\w$]*|_[\\w$]+";
    }

    /**
     * string containing number less than 256
     * @return regular expression string
     */
    static String numberLess256() {

        return "\\d|[01]\\d{2}|\\d{2}|2[0-4]\\d|25[0-5]";
    }

    static String ipV4(){
        return String.format("((%s)\\.){3}(%s)",numberLess256(),numberLess256());
    }

   static String phone()
    {
        return "(\\+972-?|0)5[0|2-8](-?\\d){7}";
    }

    static String email()
    {
        return "[^\\s,@]*@[a-zA-Z0-9]+(-?[a-zA-Z0-9])*(\\.[a-zA-Z](-?[a-zA-Z])*[a-zA-Z]?){1,3}";
    }

    static String arithmeticExpression()
    {
        return "\\s*([0-9]\\s*[\\+\\-\\*\\/]?\\s*)*;?\\s*";
    }
}
