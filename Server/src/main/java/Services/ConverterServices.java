package Services;

/**
 * Created by andrei on 2017-04-13.
 */
public class ConverterServices {
    public static Integer ConvertToInt32(String value) throws ServiceException{
        Integer result;

        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ServiceException(e.getMessage());
        }
        return result;
    }
}
