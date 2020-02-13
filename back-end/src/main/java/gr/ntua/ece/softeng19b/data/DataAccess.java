package gr.ntua.ece.softeng19b.data;

import org.apache.commons.dbcp2.BasicDataSource;
//import org.restlet.data.Status;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
//import java.util.zip.DataFormatException;

public class DataAccess {

    //private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private JdbcTemplate jdbcTemplate;
    
    public void setup(String driverClass, String url, String user, String pass) throws SQLException {
        
        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    
    public void accessDataCheck() throws DataAccessException {
        try {
            jdbcTemplate.query("select 1", ResultSet::next);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
/*
    private List<String> areaNames ;
    public void setAreaNames(){
        this.areaNames=this.fetchAreaNamesFromActual(); 
    }
    public List<String> getAreaNames(){
        return areaNames;
    }
    public List<String> fetchAreaNamesFromActual()  {
                    
        Object[] sqlParams = new Object[] {};

                
        String sqlQuery = " select DISTINCT AreaName from actualtotalload ";
        
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);

            });
    }
    public List<String> fetchAreaNamesFromAggregated() throws DataAccessException {
                    
        Object[] sqlParams = new Object[] {};

                
        String sqlQuery = " select DISTINCT AreaName from aggregatedgenerationpertype ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    public List<String> fetchAreaNamesFromForecast() throws DataAccessException {
                    
        Object[] sqlParams = new Object[] {};

                
        String sqlQuery = " select DISTINCT AreaName from dayaheadtotalloadforecast ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    */
    public void resetDB() throws DataAccessException{
        String sqlQuery = " DELETE FROM actualtotalload WHERE 1 ";
        try {
            jdbcTemplate.execute(sqlQuery);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        String sqlQuery1 = " DELETE FROM aggregatedgenerationpertype WHERE 1 ";
        try {
            jdbcTemplate.execute(sqlQuery1);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        
        String sqlQuery2 = " DELETE FROM dayaheadtotalloadforecast WHERE 1 ";
        try {
            jdbcTemplate.execute(sqlQuery2);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        String sqlQuery3 = " DELETE FROM users WHERE username!= admin";
        try {
            jdbcTemplate.execute(sqlQuery3);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
    }
//ActualTotalLoad
    public List<ActualTotalLoadForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {
                                              
        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery = " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day ,DateTime as DateTimeUTC , TotalLoadValue , updateTime as UpdateTimeUTC "+
                          " from actualtotalload INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id ) INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                          " INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) " +
                          " where AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ?"+
                          " order by DateTime ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ActualTotalLoadForSpecificDay dataLoad = new ActualTotalLoadForSpecificDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTimeUTC(rs.getString(8));
                dataLoad.setActualTotalLoadValue(rs.getFloat(9));
                dataLoad.setUpdateTimeUTC(rs.getString(10));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ActualTotalLoadForMonth> fetchActualTotalLoadForSpecificMonth(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery = " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day , Sum(TotalLoadValue)  "+
                          " from actualtotalload INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id )  INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                          " group by AreaName, ResolutionCodeText, Year , Month , Day" +
                          " having AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ?  "+
                          " order by Day ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ActualTotalLoadForMonth dataLoad = new ActualTotalLoadForMonth();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setActualTotalLoadValue(rs.getFloat(8));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ActualTotalLoadForYear> fetchActualDataLoadForSpecificYear(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Sum(TotalLoadValue)  "+
                            " from actualtotalload INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id ) INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                            " INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) " +
                            " group by AreaName, ResolutionCodeText, Year , Month " +
                            " having AreaName = ? and ResolutionCodeText = ? and Year = ?   "+
                            " order by Month ";

        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ActualTotalLoadForYear dataLoad = new ActualTotalLoadForYear();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setActualTotalLoadValue(rs.getFloat(7));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }



//DayAheadTotalLoadForecast

    public List<DayAheadTotalLoadForecastForMonth> fetchDayAheadTotalLoadForecastForSpecificMonth(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day , Sum(TotalLoadValue)  "+
                            " from dayaheadtotalloadforecast INNER JOIN  areatypecode ON ( dayaheadtotalloadforecast.AreaTypeCodeId = areatypecode.Id ) INNER JOIN  mapcode ON ( dayaheadtotalloadforecast.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( dayaheadtotalloadforecast.ResolutionCodeId = resolutioncode.Id )  " +
                            " INNER JOIN  allocatedeicdetail ON ( dayaheadtotalloadforecast.AreaCodeId = allocatedeicdetail.Id ) " +
                            " group by AreaName, ResolutionCodeText, Year , Month , Day" +
                            " having AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ?  "+
                            " order by Day";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                DayAheadTotalLoadForecastForMonth dataLoad = new DayAheadTotalLoadForecastForMonth();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDayAheadTotalLoadForecast(rs.getFloat(8));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DayAheadTotalLoadForecastForYear> fetchDayAheadTotalLoadForecastForSpecificYear(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Sum(TotalLoadValue)  "+
                            " from dayaheadtotalloadforecast INNER JOIN  areatypecode ON ( dayaheadtotalloadforecast.AreaTypeCodeId = areatypecode.Id ) INNER JOIN  mapcode ON ( dayaheadtotalloadforecast.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( dayaheadtotalloadforecast.ResolutionCodeId = resolutioncode.Id )  " +
                            " INNER JOIN  allocatedeicdetail ON ( dayaheadtotalloadforecast.AreaCodeId = allocatedeicdetail.Id ) " +
                            " group by AreaName, ResolutionCodeText, Year , Month " +
                            " having AreaName = ? and ResolutionCodeText = ? and Year = ?   "+
                            " order by Month";

        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                DayAheadTotalLoadForecastForYear dataLoad = new DayAheadTotalLoadForecastForYear();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDayAheadTotalLoadForecast(rs.getFloat(7));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DayAheadTotalLoadForecastForDay> fetchDayAheadTotalLoadForecastForSpecificDay(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {


        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day ,DateTime as DateTimeUTC , TotalLoadValue , updateTime as UpdateTimeUTC "+
                            " from dayaheadtotalloadforecast INNER JOIN  areatypecode ON ( dayaheadtotalloadforecast.AreaTypeCodeId = areatypecode.Id ) INNER JOIN  mapcode ON ( dayaheadtotalloadforecast.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( dayaheadtotalloadforecast.ResolutionCodeId = resolutioncode.Id )  " +
                            " INNER JOIN  allocatedeicdetail ON ( dayaheadtotalloadforecast.AreaCodeId = allocatedeicdetail.Id ) " +
                            " where AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ?"+
                            " order by DateTime";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                DayAheadTotalLoadForecastForDay dataLoad = new DayAheadTotalLoadForecastForDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTimeUTC(rs.getString(8));
                dataLoad.setDayAheadTotalLoadForecast(rs.getFloat(9));
                dataLoad.setUpdateTimeUTC(rs.getString(10));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

//AggregatedGenerationPerType

    public List<AggregatedGenerationPerTypeForMonth> fetchAggregatedGenerationPerTypeForSpecificMonth(String areaName,
                                                                                  String production,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();

       

        //TODO: Insert a valid SQL query
        
        if (!production.equals("AllTypes")){  
            Object[] sqlParams = new Object[] {
                    areaName,
                    production,
                    resolution,
                    year,
                    month
            };
            String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day , ProductionTypeText as ProductionType, Sum(ActualGenerationOutput)  "+
                                " from aggregatedgenerationpertype " +
                                " INNER JOIN allocatedeicdetail ON ( aggregatedgenerationpertype.AreaCodeId = allocatedeicdetail.Id ) " +
                                " INNER JOIN areatypecode ON ( aggregatedgenerationpertype.AreaTypeCodeId = areatypecode.Id )"+
                                " INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) "+
                                " INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id )  " +
                                " INNER JOIN productiontype ON (aggregatedgenerationpertype.ProductionTypeId  = productiontype.Id)"+
                                " group by ProductionTypeText, AreaName, ResolutionCodeText, Year , Month , Day" +
                                " having AreaName = ? and ProductionTypeText = ? and ResolutionCodeText = ? and Year = ? and Month = ?  "+
                                " order by Day";
            
            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AggregatedGenerationPerTypeForMonth dataLoad = new AggregatedGenerationPerTypeForMonth();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCodeText(rs.getString(2));
                    dataLoad.setMapCodeText(rs.getString(3));
                    dataLoad.setResolutionText(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setDay(rs.getInt(7));
                    dataLoad.setproductionTypeCodeText(rs.getString(8));
                    dataLoad.setActualGenerationOutputByDayValue(rs.getFloat(9));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        else {
            Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
            };
                String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day , ProductionTypeText , Sum(ActualGenerationOutput)  "+
                                " from aggregatedgenerationpertype " +
                                " INNER JOIN allocatedeicdetail ON ( aggregatedgenerationpertype.AreaCodeId = allocatedeicdetail.Id ) " +
                                " INNER JOIN areatypecode ON ( aggregatedgenerationpertype.ResolutionCodeId = areatypecode.Id )"+
                                " INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) "+
                                " INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id )  " +
                                " INNER JOIN productiontype ON (aggregatedgenerationpertype.ProductionTypeId  = productiontype.Id)"+
                                " group by ProductionTypeText, AreaName, ResolutionCodeText, Year , Month , Day" +
                                " having AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ?  "+
                                " order by Day";
            
            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AggregatedGenerationPerTypeForMonth dataLoad = new AggregatedGenerationPerTypeForMonth();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCodeText(rs.getString(2));
                    dataLoad.setMapCodeText(rs.getString(3));
                    dataLoad.setResolutionText(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setDay(rs.getInt(7));
                    dataLoad.setproductionTypeCodeText(rs.getString(8));
                    dataLoad.setActualGenerationOutputByDayValue(rs.getFloat(9));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }

        }
    }

    public List<AggregatedGenerationPerTypeForYear> fetchAggregatedGenerationPerTypeForSpecificYear(String areaName,
                                                                                  String production,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        if (!production.equals("AllTypes")){                                                                            
            Object[] sqlParams = new Object[] {
                    areaName,
                    production,
                    resolution,
                    year
            };

            //TODO: Insert a valid SQL query
            
            String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , ProductionTypeText , Sum(ActualGenerationOutput)  "+
                                " from aggregatedgenerationpertype " +
                                " INNER JOIN allocatedeicdetail ON ( aggregatedgenerationpertype.AreaCodeId = allocatedeicdetail.Id ) " +
                                " INNER JOIN areatypecode ON ( aggregatedgenerationpertype.AreaTypeCodeId = areatypecode.Id )"+
                                " INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) "+
                                " INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id )  " +
                                " INNER JOIN productiontype ON (aggregatedgenerationpertype.ProductionTypeId  = productiontype.Id)"+
                                " group by ProductionTypeText, AreaName, ResolutionCodeText, Year , Month " +
                                " having AreaName = ? and ProductionTypeText = ? and ResolutionCodeText = ? and Year = ?   "+
                                " order by Month";
            

            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AggregatedGenerationPerTypeForYear dataLoad = new AggregatedGenerationPerTypeForYear();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCodeText(rs.getString(2));
                    dataLoad.setMapCodeText(rs.getString(3));
                    dataLoad.setResolutionText(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setproductionTypeCodeText(rs.getString(7));
                    dataLoad.setActualGenerationOutputByMonthValue(rs.getFloat(8));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        else {
            Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year
            };

            //TODO: Insert a valid SQL query
            /*
            String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , ProductionTypeText as ProductionType, Sum(ActualGenerationOutput)  "+
                                " from aggregatedgenerationpertype " +
                                " INNER JOIN areatypecode ON ( aggregatedgenerationpertype.ResolutionCodeId = areatypecode.Id )"+
                                " INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) "+
                                " INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id )  " +
                                " INNER JOIN productiontype ON ( aggregatedgenerationpertype.ProductionTypeId = productiontype.Id )  " +
                                " group by ProductionTypeText, AreaName, ResolutionCodeText, Year , Month " +
                                " having AreaName = ? and ResolutionCodeText = ? and Year = ?   ";
            */
            String sqlQuery = " select AreaName, AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , ProductionTypeText , Sum(ActualGenerationOutput) from aggregatedgenerationpertype INNER JOIN areatypecode ON ( aggregatedgenerationpertype.AreaTypeCodeId = areatypecode.Id )  INNER JOIN allocatedeicdetail ON ( aggregatedgenerationpertype.AreaCodeId = allocatedeicdetail.Id ) INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id ) INNER JOIN productiontype ON ( aggregatedgenerationpertype.ProductionTypeId = productiontype.Id ) group by ProductionTypeText, AreaName, ResolutionCodeText, Year , Month having AreaName = ? and ResolutionCodeText = ? and Year = ?";

            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AggregatedGenerationPerTypeForYear dataLoad = new AggregatedGenerationPerTypeForYear();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCodeText(rs.getString(2));
                    dataLoad.setMapCodeText(rs.getString(3));
                    dataLoad.setResolutionText(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setproductionTypeCodeText(rs.getString(7));
                    dataLoad.setActualGenerationOutputByMonthValue(rs.getFloat(8));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }

        }
    }

    public List<AggregatedGenerationPerTypeForDay> fetchAggregatedGenerationPerTypeForSpecificDay(String areaName,
                                                                                  String production,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {


        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        if (!production.equals("AllTypes")){  
            Object[] sqlParams = new Object[] {
                areaName,
                production,
                resolution,
                year,
                month,
                day
            };
            String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day ,DateTime as DateTimeUTC , ProductionTypeText , ActualGenerationOutput , updateTime as UpdateTimeUTC "+
                                " from aggregatedgenerationpertype " +
                                " INNER JOIN allocatedeicdetail ON ( aggregatedgenerationpertype.AreaCodeId = allocatedeicdetail.Id ) " +
                                " INNER JOIN areatypecode ON ( aggregatedgenerationpertype.AreaTypeCodeId = areatypecode.Id )"+
                                " INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) "+
                                " INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id )  " +
                                " INNER JOIN productiontype ON (aggregatedgenerationpertype.ProductionTypeId  = productiontype.Id)"+
                                " where AreaName = ? and ProductionTypeText = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ? ";
            
            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AggregatedGenerationPerTypeForDay dataLoad = new AggregatedGenerationPerTypeForDay();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCodeText(rs.getString(2));
                    dataLoad.setMapCodeText(rs.getString(3));
                    dataLoad.setResolutionText(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setDay(rs.getInt(7));
                    dataLoad.setDateTimeUTC(rs.getString(8));
                    dataLoad.setproductionTypeCodeText(rs.getString(9));
                    dataLoad.setActualGenerationOutputValue(rs.getFloat(10));
                    dataLoad.setUpdateTimeUTC(rs.getString(11));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }
        else {
            Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
            };
            String sqlQuery =   " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day ,DateTime as DateTimeUTC , ProductionTypeText , ActualGenerationOutput , updateTime as UpdateTimeUTC "+
                                " from aggregatedgenerationpertype " +
                                " INNER JOIN allocatedeicdetail ON ( aggregatedgenerationpertype.AreaCodeId = allocatedeicdetail.Id ) " +
                                " INNER JOIN areatypecode ON ( aggregatedgenerationpertype.AreaTypeCodeId = areatypecode.Id )"+
                                " INNER JOIN mapcode ON ( aggregatedgenerationpertype.MapCodeId = mapcode.Id ) "+
                                " INNER JOIN resolutioncode ON ( aggregatedgenerationpertype.ResolutionCodeId = resolutioncode.Id )  " +
                                " INNER JOIN productiontype ON (aggregatedgenerationpertype.ProductionTypeId  = productiontype.Id)"+
                                " where AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ?";
            
            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AggregatedGenerationPerTypeForDay dataLoad = new AggregatedGenerationPerTypeForDay();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCodeText(rs.getString(2));
                    dataLoad.setMapCodeText(rs.getString(3));
                    dataLoad.setResolutionText(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setDay(rs.getInt(7));
                    dataLoad.setDateTimeUTC(rs.getString(8));
                    dataLoad.setproductionTypeCodeText(rs.getString(9));
                    dataLoad.setActualGenerationOutputValue(rs.getFloat(10));
                    dataLoad.setUpdateTimeUTC(rs.getString(11));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }

    }


//ActualvsForecast
    public List<ActualvsForecastForDay> fetchActualvsForecastForDay(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {
                                              
        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        Integer day = date.getDayOfMonth();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month,
                day
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery = " select  actualtotalload.AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, actualtotalload.Year ,actualtotalload.Month ,actualtotalload.Day ,actualtotalload.DateTime as DateTimeUTC , actualtotalload.TotalLoadValue as TotalLoadValue ,dayaheadtotalloadforecast.TotalLoadValue as DayAheadLoadValue"+
                          " from actualtotalload  INNER JOIN  dayaheadtotalloadforecast ON ( actualtotalload.AreaName = dayaheadtotalloadforecast.AreaName  "+
                          " and actualtotalload.DateTime = dayaheadtotalloadforecast.DateTime " +
                          " and actualtotalload.AreaTypeCodeId = dayaheadtotalloadforecast.AreaTypeCodeId " +
                          " and actualtotalload.MapCodeId = dayaheadtotalloadforecast.MapCodeId " +
                          " and actualtotalload.ResolutionCodeId = dayaheadtotalloadforecast.ResolutionCodeId " +
                          " and actualtotalload.AreaCodeId = dayaheadtotalloadforecast.AreaCodeId )" +
                          " INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) " +
                          " INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id ) " +
                          " INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id )  " +
                          " INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                          " where actualtotalload.AreaName = ? and ResolutionCodeText = ? and actualtotalload.Year = ? and actualtotalload.Month = ? and actualtotalload.Day = ?"+
                          " order by actualtotalload.DateTime ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ActualvsForecastForDay dataLoad = new ActualvsForecastForDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTimeUTC(rs.getString(8));
                dataLoad.setActualTotalLoadValue(rs.getFloat(9));
                dataLoad.setDayAheadTotalLoadForecastValue(rs.getFloat(10));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ActualvsForecastForMonth> fetchActualvsForecastForMonth(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {

        Integer year = date.getYear();
        Integer month = date.getMonthValue();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year,
                month
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery = " select  actualtotalload.AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, actualtotalload.Year ,actualtotalload.Month ,actualtotalload.Day , Sum(actualtotalload.TotalLoadValue) as TotalLoadValue,Sum(dayaheadtotalloadforecast.TotalLoadValue) as DayAheadLoadValue "+
                          " from actualtotalload  INNER JOIN  dayaheadtotalloadforecast ON ( actualtotalload.AreaName = dayaheadtotalloadforecast.AreaName  "+
                          " and actualtotalload.DateTime = dayaheadtotalloadforecast.DateTime " +
                          " and actualtotalload.AreaTypeCodeId = dayaheadtotalloadforecast.AreaTypeCodeId " +
                          " and actualtotalload.MapCodeId = dayaheadtotalloadforecast.MapCodeId " +
                          " and actualtotalload.ResolutionCodeId = dayaheadtotalloadforecast.ResolutionCodeId " +
                          " and actualtotalload.AreaCodeId = dayaheadtotalloadforecast.AreaCodeId )" +
                          " INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) " +
                          " INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id ) " +
                          " INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id )  " +
                          " INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                          " where actualtotalload.AreaName = ? and ResolutionCodeText = ? and actualtotalload.Year = ? and actualtotalload.Month = ? "+
                          " order by actualtotalload.Day ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ActualvsForecastForMonth dataLoad = new ActualvsForecastForMonth();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setActualTotalLoadByDayValue(rs.getFloat(8));
                dataLoad.setDayAheadTotalLoadForecastByDayValue(rs.getFloat(9));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ActualvsForecastForYear> fetchActualvsForecastForYear(String areaName,
                                                                                  String resolution,
                                                                                  LocalDate date) throws DataAccessException {
        Integer year = date.getYear();

        Object[] sqlParams = new Object[] {
                areaName,
                resolution,
                year
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery = " select  actualtotalload.AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, actualtotalload.Year ,actualtotalload.Month , Sum(actualtotalload.TotalLoadValue) as TotalLoadValue,Sum(dayaheadtotalloadforecast.TotalLoadValue) as DayAheadLoadValue "+
                          " from actualtotalload  LEFT JOIN  dayaheadtotalloadforecast ON ( actualtotalload.AreaName = dayaheadtotalloadforecast.AreaName  "+
                          " and actualtotalload.DateTime = dayaheadtotalloadforecast.DateTime " +
                          " and actualtotalload.AreaTypeCodeId = dayaheadtotalloadforecast.AreaTypeCodeId " +
                          " and actualtotalload.MapCodeId = dayaheadtotalloadforecast.MapCodeId " +
                          " and actualtotalload.ResolutionCodeId = dayaheadtotalloadforecast.ResolutionCodeId " +
                          " and actualtotalload.AreaCodeId = dayaheadtotalloadforecast.AreaCodeId )" +
                          " INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) " +
                          " INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id ) " +
                          " INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id )  " +
                          " INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                          " where actualtotalload.AreaName = ? and ResolutionCodeText = ? and actualtotalload.Year = ?  "+
                          " order by actualtotalload.Month ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ActualvsForecastForYear dataLoad = new ActualvsForecastForYear();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCodeText(rs.getString(2));
                dataLoad.setMapCodeText(rs.getString(3));
                dataLoad.setResolutionText(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setActualTotalLoadByMonthValue(rs.getFloat(7));
                dataLoad.setDayAheadTotalLoadForecastByMonthValue(rs.getFloat(8));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }


     public List<User> fetchUser(String userName ) throws DataAccessException {
        Object[] sqlParams = new Object[] {
            userName
        };

        //TODO: Insert a valid SQL query
        
        String sqlQuery = " select *"+
                          " from users " +
                          " where username = ? ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                User dataLoad = new User();
                dataLoad.setUsername(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setEmail(rs.getString(2));
                dataLoad.setRequestsPerDayQuota(rs.getInt(3));            
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

}