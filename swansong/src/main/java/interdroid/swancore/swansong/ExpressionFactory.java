package interdroid.swancore.swansong;

public class ExpressionFactory {

    public static Expression parse(String parseString)
            throws ExpressionParseException {
        return SwanExpressionParser.parseExpression(parseString);
    }

}
