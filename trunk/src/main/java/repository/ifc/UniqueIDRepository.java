package repository.ifc;

import exceptions.UniqueIDException;
import models.IDSetNEntity;

/**
 * Created by ivald79 on 30/09/2014.
 */
public interface UniqueIDRepository {

    final static String LOAD_NUMERATOR_SELECT = "SELECT id, step FROM E_NUMERATOR WHERE name = (?);";
    final static String LOAD_NUMERATOR_UPDATE = "UPDATE E_NUMERATOR SET id = id + step WHERE name = (?);";
    final static String LOAD_NUMERATOR_INSERT = "INSERT INTO E_NUMERATOR(name, id, step) VALUES(?, ?, ?);";

    IDSetNEntity loadNumerator(String sequenceKey) throws UniqueIDException;

}
