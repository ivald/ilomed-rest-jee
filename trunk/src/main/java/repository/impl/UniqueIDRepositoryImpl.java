package repository.impl;

import constant.CommonConstant;
import exceptions.UniqueIDException;
import models.IDSetNEntity;
import org.apache.log4j.Logger;
import repository.ifc.UniqueIDRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ivald79 on 30/09/2014.
 */
public class UniqueIDRepositoryImpl extends AbstractSQLRepositoryImpl implements UniqueIDRepository {

    private static final Logger LOGGER = Logger.getLogger(AbstractSQLRepositoryImpl.class.getName());

    private PreparedStatement preparedStatement = null;
    private final Long NUMERATOR_STEP = 2L;

    /**
     *
     * @param sequenceKey
     * @return
     * @throws UniqueIDException
     */
    public final IDSetNEntity loadNumerator(String sequenceKey) throws UniqueIDException {

        ResultSet resultset = null;
        IDSetNEntity idSet = null;
        boolean isNumeratorExist = false;

        try {
            this.preparedStatement = getConnection().prepareStatement(
                    UniqueIDRepository.LOAD_NUMERATOR_SELECT);

            this.preparedStatement.setString(1, sequenceKey);

            resultset = this.executeQuery();

            if (resultset.next()) {
                Long id = resultset.getLong("id");
                Long step = resultset.getLong("step");
                idSet = new IDSetNEntity(id, (id + step));
                isNumeratorExist = true;
            }

            if (isNumeratorExist) {
                this.preparedStatement = this.getConnection().prepareStatement(
                        UniqueIDRepository.LOAD_NUMERATOR_UPDATE);
                this.preparedStatement.setString(1, sequenceKey);
                executeUpdate();
            } else {
                // insert a new numerator name in case of unexisting one
                this.preparedStatement = this.getConnection().prepareStatement(
                        UniqueIDRepository.LOAD_NUMERATOR_INSERT);
                this.preparedStatement.setString(1, sequenceKey);
                this.preparedStatement.setLong(2, CommonConstant.ONE_INTEGER);
                this.preparedStatement.setLong(3, NUMERATOR_STEP);

                executeUpdate();

                idSet = new IDSetNEntity(CommonConstant.ONE_INTEGER, NUMERATOR_STEP);
            }

        } catch (SQLException sqle) {
            LOGGER.error(sqle.getMessage());
            throw new UniqueIDException("Numerator of a " + sequenceKey
                    + " wasn't added successfully.");
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }

                closeConnection(getConnection());

                LOGGER.info("DB resources were closed successfully.");
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return idSet;
    }

    @Override
    protected PreparedStatement getPreparedStatement() {
        return this.preparedStatement;
    }
}

