package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.Customer;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery extends AbstractQuery {
    private static CustomerQuery instance;

    private CustomerQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static CustomerQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new CustomerQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "customers";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Customer.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setCountry(resultSet.getString("country"));
            list.add(customer);
        }
        resultSet.close();
        return list;
    }
}
