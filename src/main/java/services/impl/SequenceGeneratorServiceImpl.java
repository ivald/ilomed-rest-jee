package services.impl;

import constant.CommonConstant;
import exceptions.UniqueIDException;
import models.IDSetNEntity;
import org.apache.log4j.Logger;
import repository.ifc.UniqueIDRepository;
import repository.impl.UniqueIDRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivald79 on 01/10/2014.
 * Singleton exists on a per JVM basis and returns a new, unique ID to the
 * caller.
 */
public class SequenceGeneratorServiceImpl {

    private static final Logger LOGGER = Logger.getLogger(SequenceGeneratorServiceImpl.class.getName());

    private static SequenceGeneratorServiceImpl getNewKye = null;
    private Map<String, IDSetNEntity> sequenceMap = null;
    private UniqueIDRepository uniqueIDEJB;

    /**
     * Accessor to get handle to singleton object.
     *
     * @throws UniqueIDException
     */
    public static SequenceGeneratorServiceImpl getInstance() throws UniqueIDException {
        if (getNewKye == null) {
            getNewKye = new SequenceGeneratorServiceImpl();
        }
        return getNewKye;
    }

    /**
     * Cannot instantiate. Must use instance() method to get a handle to the
     * object.
     */
    private SequenceGeneratorServiceImpl() {
        uniqueIDEJB = new UniqueIDRepositoryImpl();
        sequenceMap = new HashMap<String, IDSetNEntity>();
    }

    /**
     * The method returns next sequence id of specific entity
     *
     * @param sequenceClassKey
     * @return Integer
     * @throws Exception
     */
    public Long getNextSequenceId(String sequenceClassKey) throws Exception {
        if(!sequenceMap.containsKey(sequenceClassKey)) {
            sequenceMap.put(sequenceClassKey, new IDSetNEntity(CommonConstant.ZERO_INTEGER, CommonConstant.ZERO_INTEGER));
        }
        return this.getNextIdByClassName(sequenceClassKey);
    }

    /**
     * Returns a unique ID to the caller. This is synchronized to prevent
     * multiple callers from getting the same ID back if they called
     * getNextIDCustomer() at the same time.
     *
     * @param sequenceClassKey
     *
     * @return int next ID
     */
    private synchronized Long getNextIdByClassName(String sequenceClassKey) throws Exception {
        IDSetNEntity idSet = sequenceMap.get(sequenceClassKey);

        idSet.setMin(idSet.getMin() + CommonConstant.ONE_INTEGER);
        if (idSet.getMin() > idSet.getMax()) {
            try {
                IDSetNEntity set = uniqueIDEJB.loadNumerator(sequenceClassKey);
                // reset current id
                idSet.setMin(set.getMin() + CommonConstant.ONE_INTEGER);
                idSet.setMax(set.getMax());
                LOGGER.info("A new sequence id was assigned for the class " + sequenceClassKey);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                throw ex;
            }
        }

        return idSet.getMin();
    }
}
