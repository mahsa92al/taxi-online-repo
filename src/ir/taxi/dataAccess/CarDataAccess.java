package ir.taxi.dataAccess;

import ir.taxi.model.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class CarDataAccess extends DataBaseAccess{

    public CarDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<Integer> saveGroupOfCar(List<Car> cars) throws SQLException {
        List<Integer>autoGeneratedKeys = new ArrayList<>();
        if(getConnection() != null){
            String sqlQuery = "insert into cars (model , color) values (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            for (Car item:cars) {
                stmt.setString(1, item.getModel());
                stmt.setString(2, item.getCarColor());
                stmt.executeUpdate(); //?
                ResultSet autoKey = stmt.getGeneratedKeys();
                autoKey.next();
                autoGeneratedKeys.add(autoKey.getInt(1));
            }
            return autoGeneratedKeys;
        }
        return null;
    }

    public void saveNewCar(Car car) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into cars (model , color) values (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, car.getModel());
            stmt.setString(2, car.getCarColor());
        }
    }
}
