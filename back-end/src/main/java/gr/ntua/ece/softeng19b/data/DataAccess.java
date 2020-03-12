package gr.ntua.ece.softeng19b.data;

import org.restlet.resource.ResourceException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.restlet.data.Status;
import gr.ntua.ece.softeng19b.data.model.*;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Random;
//import java.util.zip.DataFormatException;

public class DataAccess {

    //private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 10;

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

    private List<String> areaNamesATL ;
    private List<String> areaNamesAGPT ;
    private List<String> areaNamesDATL ;

    public void setAreaNamesATL(){
        this.areaNamesATL=this.fetchAreaNamesFromActual(); 
    }
    public void setAreaNamesAGPT(){
        this.areaNamesAGPT=this.fetchAreaNamesFromAggr(); 
    }
    public void setAreaNamesDATL(){
        this.areaNamesDATL=this.fetchAreaNamesFromDayAh(); 
    }

    public List<String> getAreaNamesATL(){
        return areaNamesATL;
    }
    public List<String> getAreaNamesAGPT(){
        return areaNamesAGPT;
    }
    public List<String> getAreaNamesDATL(){
        return areaNamesDATL;
    }

    public List<String> fetchAreaNamesFromActual()  {
                    
        Object[] sqlParams = new Object[] {};
        String sqlQuery = " select Distinct AreaName from actualtotalload ";
        
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);
            });
    }
    public List<String> fetchAreaNamesFromAggr()  {
                    
        Object[] sqlParams = new Object[] {};
        String sqlQuery = " select Distinct AreaName from aggregatedgenerationpertype ";
        
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);
            });
    }
    public List<String> fetchAreaNamesFromDayAh()  {
                    
        Object[] sqlParams = new Object[] {};
        String sqlQuery = " select Distinct AreaName from dayaheadtotalloadforecast ";
        
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);
            });
    }
    

    private List<String> prodTypes ;
    public void setProdTypes(){
        this.prodTypes=this.fetchProdTypesFromAggregated(); 
    }

    public List<String> getProdTypes(){
        return prodTypes;
    }
    public List<String> fetchProdTypesFromAggregated()  {
                    
        Object[] sqlParams = new Object[] {};
        String sqlQuery = " select ProductionTypeText from productiontype ";
        
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                return rs.getString(1);

            });
    }
    
    public int getQuotasFromToken (String token){
        Object[] sqlParams = new Object[] {token};
        String sqlQuery = " select quotas from user where token = ? ";
        
        return jdbcTemplate.queryForObject(sqlQuery, sqlParams, Integer.class);
    }
    public int getQuotasFromUsername (String token){
        Object[] sqlParams = new Object[] {token};
        String sqlQuery = " select quotas from user where username = ? ";
        
        return jdbcTemplate.queryForObject(sqlQuery, sqlParams, Integer.class);
    }
    public String getEmailFromUsername (String token){
        Object[] sqlParams = new Object[] {token};
        String sqlQuery = " select email from user where username = ? ";
        
        return jdbcTemplate.queryForObject(sqlQuery, sqlParams, String.class);
    }

    public void updateQuotasForUser (int quotas ,String token){
        Object[] sqlParams = new Object[] {quotas,token};
        String sqlQuery = " update user set quotas = ? where token = ? ";
        jdbcTemplate.update(sqlQuery, sqlParams);
    }
    
    //insert into db
    //using LOCAL DATA


    public long insertATLIntoDatabaseUsingLD(String dir){
        //String[] rows = row.split(";");
        //Object[] sqlParams =new Object[]{rows[0], rows[1],rows[2],rows[3],rows[4],rows[5],rows[6],rows[7],rows[8],rows[9],rows[10],rows[11],rows[12],rows[13],rows[14],rows[15],rows[16]};
        
         String sqlQuery = "LOAD DATA INFILE 'C:/Users/user/Desktop/2.0TL19-25-our-master/back-end/src/main/java/gr/ntua/ece/softeng19b/api/resource/temp.csv' IGNORE INTO TABLE actualtotalload FIELDS TERMINATED BY ';'  LINES TERMINATED BY '\\r\\n'  " +
         "(Id, EntityCreatedAt, EntityModifiedAt, ActionTaskID, Status, Year, Month, Day, DateTime, AreaName, UpdateTime, TotalLoadValue, AreaTypeCodeId, AreaCodeId, ResolutionCodeId, MapCodeId, RowHash)";
         //" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        int x = jdbcTemplate.update(sqlQuery, new Object[]{});
        return x;
    }

    public long insertDATLIntoDatabaseUsingLD(String dir){
        //String[] rows = row.split(";");

         String sqlQuery = "LOAD DATA INFILE 'C:/Users/user/Desktop/2.0TL19-25-our-master/back-end/src/main/java/gr/ntua/ece/softeng19b/api/resource/temp.csv' IGNORE INTO TABLE dayaheadtotalloadforecast FIELDS TERMINATED BY ';'  LINES TERMINATED BY '\\r\\n'  " +
         "(Id, EntityCreatedAt, EntityModifiedAt, ActionTaskID, Status, Year, Month, Day, DateTime, AreaName, UpdateTime, TotalLoadValue, AreaTypeCodeId, AreaCodeId, ResolutionCodeId, MapCodeId, RowHash)";
         //" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        int x = jdbcTemplate.update(sqlQuery, new Object[]{});
        return x;
    }
    public long insertAGPTIntoDatabaseUsingLD(String dir){
        //String[] rows = row.split(";");

         String sqlQuery = "LOAD DATA INFILE 'C:/Users/user/Desktop/2.0TL19-25-our-master/back-end/src/main/java/gr/ntua/ece/softeng19b/api/resource/temp.csv' IGNORE INTO TABLE aggregatedgenerationpertype FIELDS TERMINATED BY ';'  LINES TERMINATED BY '\\r\\n'  " +
         "(Id, EntityCreatedAt, EntityModifiedAt, ActionTaskID, Status, Year, Month, Day, DateTime, AreaName, UpdateTime, ActualGenerationOutput, ActualConsuption, AreaTypeCodeId, AreaCodeId, ResolutionCodeId, MapCodeId, ProductionTypeId, RowHash)";

        
        int x = jdbcTemplate.update(sqlQuery, new Object[]{});
        return x;
    }
    
    public long insertATLIntoDatabase(String[] rows){
        //System.out.println("WE COO?");
        //String [] rows = datas.split("\n"); //seperator for lines
        int n = 19 ; // to keep the first 19 character for dates year-month-day hours:minutes:seconds
        //data[i].substring(0, n);
        String [] data;
        Object[] sqlParam;
        List<Object[]> sqlParams= new  ArrayList<Object[]>();
        String sqlQuery = " insert ignore into actualtotalload " +
                        "   values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
        for (int i = 1 ; i < rows.length ; i ++){
            data = rows[i].split(";");
            //System.out.println(data[0]);
            //System.out.println(data[1]);
            sqlParam = new Object[] {data[0],data[1].substring(0, n),data[2].substring(0, n),data[3],null,data[5],data[6],data[7],data[8].substring(0, n),data[9],data[10].substring(0, n),data[11],data[12],data[15],data[13],data[14],data[16]};
            sqlParams.add(sqlParam);
            //sqlParams = new Object[] {data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[15],data[13],data[14],data[16]};
            
            //sqlQuery +="   values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            //"   ON DUPLICATE KEY UPDATE";

        }
        int[] x = jdbcTemplate.batchUpdate(sqlQuery, sqlParams);
        //
        int toReturn = 0;
        for (int i= 0 ; i < x.length ;i ++){
            toReturn+=x[i];
        }
        return toReturn;
    }

    public long insertDATLIntoDatabase(String[] rows){
        //System.out.println("WE COO?");
        //String [] rows = datas.split("\n"); //seperator for lines
        int n = 19 ; // to keep the first 19 character for dates year-month-day hours:minutes:seconds
        //data[i].substring(0, n);
        String [] data;
        Object[] sqlParam;
        List<Object[]> sqlParams= new  ArrayList<Object[]>();
        //dbase  AreaTypeCodeId	MapCodeId	AreaCodeId	ResolutionCodeId	RowHash	
        //           12             13             14           15              16
        //data  AreaTypeCodeId	AreaCodeId	ResolutionCodeId	MapCodeId	RowHash

        String sqlQuery = " insert ignore into dayaheadtotalloadforecast " +
                        "   values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
        for (int i = 1 ; i < rows.length ; i ++){
            data = rows[i].split(";");
            sqlParam = new Object[] {data[0],data[1].substring(0, n),data[2].substring(0, n),data[3],null,data[5],data[6],data[7],data[8].substring(0, n),data[9],data[10].substring(0, n),data[11],data[12],data[15],data[13],data[14],data[16]};
            sqlParams.add(sqlParam);
            //sqlParams = new Object[] {data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[15],data[13],data[14],data[16]};
            
            //sqlQuery +="   values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            //"   ON DUPLICATE KEY UPDATE";

        }
        int[] x = jdbcTemplate.batchUpdate(sqlQuery, sqlParams);
        //
        int toReturn = 0;
        for (int i= 0 ; i < x.length ;i ++){
            toReturn+=x[i];
        }
        return toReturn;
    }

    public long insertAGPTIntoDatabase(String[] rows){
        //System.out.println("WE COO?");
        //String [] rows = datas.split("\n"); //seperator for lines
        int n = 19 ; // to keep the first 19 character for dates: year-month-day hours:minutes:seconds
        //data[i].substring(0, n);
        String [] data;
        Object[] sqlParam;
        List<Object[]> sqlParams= new  ArrayList<Object[]>();

        String sqlQuery = " insert ignore into aggregatedgenerationpertype " +
                        "   values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ? )";
        for (int i = 1 ; i < rows.length ; i ++){
            data = rows[i].split(";");
            sqlParam = new Object[] {data[0],data[1].substring(0, n),data[2].substring(0, n),
                                    data[3],null,data[5],data[6],data[7],data[8].substring(0, n),
                                    data[9],data[10].substring(0, n),data[11],data[12],data[13],   
                                    data[17],data[15],data[16],data[14],data[18]};
            sqlParams.add(sqlParam);
            //sqlParams = new Object[] {data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[15],data[13],data[14],data[16]};
            
            //sqlQuery +="   values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            //"   ON DUPLICATE KEY UPDATE";

        }
        int[] x = jdbcTemplate.batchUpdate(sqlQuery, sqlParams);
        //
        int toReturn = 0;
        for (int i= 0 ; i < x.length ;i ++){
            toReturn+=x[i];
        }
        return toReturn;
    }

    public long countATLLines(){
        long x = jdbcTemplate.queryForObject("select COUNT(1) from actualtotalload ",new Object[]{}, Long.class);
        return x;
    }
    public long countAGPTLines(){
        long x = jdbcTemplate.queryForObject("select COUNT(1) from aggregatedgenerationpertype ",new Object[]{}, Long.class);
        return x;
    }
    public long countDATLFLines(){
        long x = jdbcTemplate.queryForObject("select COUNT(1) from dayaheadtotalloadforecast ",new Object[]{}, Long.class);
        return x;
    }

//ActualTotalLoad
    public List<ATLRecordForSpecificDay> fetchActualDataLoadForSpecificDate(String areaName,
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
        //FORCE INDEX (`IX_ActualTotalLoad_AreaName`)
        String sqlQuery = " select AreaName , AreaTypeCodeText , MapCodeText , ResolutionCodeText, Year ,Month , Day ,DateTime as DateTimeUTC , TotalLoadValue , updateTime as UpdateTimeUTC "+
                          " from actualtotalload INNER JOIN  areatypecode ON ( actualtotalload.AreaTypeCodeId = areatypecode.Id ) INNER JOIN  mapcode ON ( actualtotalload.MapCodeId = mapcode.Id ) INNER JOIN  resolutioncode ON ( actualtotalload.ResolutionCodeId = resolutioncode.Id )  " +
                          " INNER JOIN  allocatedeicdetail ON ( actualtotalload.AreaCodeId = allocatedeicdetail.Id ) " +
                          " where AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ?"+
                          " order by DateTime " ;
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                ATLRecordForSpecificDay dataLoad = new ATLRecordForSpecificDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTime(rs.getString(8));
                dataLoad.setActualTotalLoadValue(rs.getFloat(9));
                dataLoad.setUpdateTime(rs.getString(10));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ATLRecordForSpecificMonth> fetchActualTotalLoadForSpecificMonth(String areaName,
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
                ATLRecordForSpecificMonth dataLoad = new ATLRecordForSpecificMonth();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setday(rs.getInt(7));
                dataLoad.setActualTotalLoadByDayValue(rs.getFloat(8));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ATLRecordForSpecificYear> fetchActualDataLoadForSpecificYear(String areaName,
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
                ATLRecordForSpecificYear dataLoad = new ATLRecordForSpecificYear();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setActualTotalLoadByMonthValue(rs.getFloat(7));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }



//DayAheadTotalLoadForecast

    public List<DATLFRecordForSpecificMonth> fetchDayAheadTotalLoadForecastForSpecificMonth(String areaName,
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
                DATLFRecordForSpecificMonth dataLoad = new DATLFRecordForSpecificMonth();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setdayAheadTotalLoadForecast(rs.getFloat(8));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DATLFRecordForSpecificYear> fetchDayAheadTotalLoadForecastForSpecificYear(String areaName,
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
                DATLFRecordForSpecificYear dataLoad = new DATLFRecordForSpecificYear();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setdayAheadTotalLoadForecast(rs.getFloat(7));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<DATLFRecordForSpecificDay> fetchDayAheadTotalLoadForecastForSpecificDay(String areaName,
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
                DATLFRecordForSpecificDay dataLoad = new DATLFRecordForSpecificDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTime(rs.getString(8));
                dataLoad.setdayAheadTotalLoadForecast(rs.getFloat(9));
                dataLoad.setUpDateTime(rs.getString(10));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

//AggregatedGenerationPerType

    public List<AGPTRecordForSpecificMonth> fetchAggregatedGenerationPerTypeForSpecificMonth(String areaName,
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
                    AGPTRecordForSpecificMonth dataLoad = new AGPTRecordForSpecificMonth();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCode(rs.getString(2));
                    dataLoad.setMapCode(rs.getString(3));
                    dataLoad.setResolutionCode(rs.getString(4));
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
                    AGPTRecordForSpecificMonth dataLoad = new AGPTRecordForSpecificMonth();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCode(rs.getString(2));
                    dataLoad.setMapCode(rs.getString(3));
                    dataLoad.setResolutionCode(rs.getString(4));
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

    public List<AGPTRecordForSpecificYear> fetchAggregatedGenerationPerTypeForSpecificYear(String areaName,
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
                    AGPTRecordForSpecificYear dataLoad = new AGPTRecordForSpecificYear();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCode(rs.getString(2));
                    dataLoad.setMapCode(rs.getString(3));
                    dataLoad.setResolutionCode(rs.getString(4));
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
                    AGPTRecordForSpecificYear dataLoad = new AGPTRecordForSpecificYear();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCode(rs.getString(2));
                    dataLoad.setMapCode(rs.getString(3));
                    dataLoad.setResolutionCode(rs.getString(4));
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

    public List<AGPTRecordForSpecificDay> fetchAggregatedGenerationPerTypeForSpecificDay(String areaName,
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
                                " where AreaName = ? and ProductionTypeText = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ? " +
                                " Order BY DateTime ASC";
            
            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AGPTRecordForSpecificDay dataLoad = new AGPTRecordForSpecificDay();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCode(rs.getString(2));
                    dataLoad.setMapCode(rs.getString(3));
                    dataLoad.setResolutionCode(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setDay(rs.getInt(7));
                    dataLoad.setDateTime(rs.getString(8));
                    dataLoad.setproductionTypeCodeText(rs.getString(9));
                    dataLoad.setActualGenerationOutputValue(rs.getFloat(10));
                    dataLoad.setUpDateTime(rs.getString(11));
                    
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
                                " where AreaName = ? and ResolutionCodeText = ? and Year = ? and Month = ? and Day = ?" +
                                " Order BY ProductionTypeText,DateTime ASC";
            
            try {
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    AGPTRecordForSpecificDay dataLoad = new AGPTRecordForSpecificDay();
                    dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                    dataLoad.setAreaTypeCode(rs.getString(2));
                    dataLoad.setMapCode(rs.getString(3));
                    dataLoad.setResolutionCode(rs.getString(4));
                    dataLoad.setYear(rs.getInt(5));
                    dataLoad.setMonth(rs.getInt(6));
                    dataLoad.setDay(rs.getInt(7));
                    dataLoad.setDateTime(rs.getString(8));
                    dataLoad.setproductionTypeCodeText(rs.getString(9));
                    dataLoad.setActualGenerationOutputValue(rs.getFloat(10));
                    dataLoad.setUpDateTime(rs.getString(11));
                    
                    return dataLoad;

                });
            }
            catch(Exception e) {
                throw new DataAccessException(e.getMessage(), e);
            }
        }

    }


//ActualvsForecast
    public List<AvFRecordForSpecificDay> fetchActualvsForecastForDay(String areaName,
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
                AvFRecordForSpecificDay dataLoad = new AvFRecordForSpecificDay();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setDateTime(rs.getString(8));
                dataLoad.setActualTotalLoadValue(rs.getFloat(9));
                dataLoad.setDayAheadTotalLoadForecastValue(rs.getFloat(10));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<AvFRecordForSpecificMonth> fetchActualvsForecastForMonth(String areaName,
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
                          " GROUP BY actualtotalload.Day " +
                          " order by actualtotalload.Day ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                AvFRecordForSpecificMonth dataLoad = new AvFRecordForSpecificMonth();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setDay(rs.getInt(7));
                dataLoad.setActualTotalLoadValue(rs.getFloat(8));
                dataLoad.setDayAheadTotalLoadForecastValue(rs.getFloat(9));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<AvFRecordForSpecificYear> fetchActualvsForecastForYear(String areaName,
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
                          " GROUP BY actualtotalload.Month " +
                          " order by actualtotalload.Month ";
        
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                AvFRecordForSpecificYear dataLoad = new AvFRecordForSpecificYear();
                dataLoad.setAreaName(rs.getString(1)); //get the string located at the 1st column of the result set
                dataLoad.setAreaTypeCode(rs.getString(2));
                dataLoad.setMapCode(rs.getString(3));
                dataLoad.setResolutionCode(rs.getString(4));
                dataLoad.setYear(rs.getInt(5));
                dataLoad.setMonth(rs.getInt(6));
                dataLoad.setActualTotalLoadValue(rs.getFloat(7));
                dataLoad.setDayAheadTotalLoadForecastValue(rs.getFloat(8));
                
                return dataLoad;

            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    
    public void resetDB(String x) throws DataAccessException{
        String sqlQuery = " DELETE QUICK FROM actualtotalload WHERE 1 ";
        //String sqlQuery = " TRUNCATE TABLE actualtotalload ";
        try {
            jdbcTemplate.execute(sqlQuery);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        String sqlQuery1 = " DELETE QUICK FROM aggregatedgenerationpertype WHERE 1 ";
        //String sqlQuery1 = " TRUNCATE TABLE aggregatedgenerationpertype ";
        try {
            jdbcTemplate.execute(sqlQuery1);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        
        String sqlQuery2 = " DELETE QUICK FROM dayaheadtotalloadforecast WHERE 1 ";
        //String sqlQuery2 = " TRUNCATE TABLE dayaheadtotalloadforecast ";
        try {
            jdbcTemplate.execute(sqlQuery2);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        String sqlQuery3 = " DELETE QUICK FROM user WHERE 1";
        //String sqlQuery3 = " TRUNCATE TABLE user";
        try {
            jdbcTemplate.execute(sqlQuery3);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
        String sqlQuery4 = " DELETE QUICK FROM administrator WHERE 1";
        //String sqlQuery4 = "  TRUNCATE TABLE administrator";
        try {
            jdbcTemplate.execute(sqlQuery4);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
        
        String sqlQuery5 = "INSERT INTO administrator( username, password, email, token) VALUES ('admin','321nimda','admin@here.com','admintoken123')";
        try {
            jdbcTemplate.execute(sqlQuery5);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
    }
    public void resetDB() throws DataAccessException{
        //String sqlQuery = " DELETE QUICK FROM actualtotalload WHERE 1 ";
        String sqlQuery = " TRUNCATE TABLE actualtotalload ";
        try {
            jdbcTemplate.execute(sqlQuery);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        //String sqlQuery1 = " DELETE QUICK FROM aggregatedgenerationpertype WHERE 1 ";
        String sqlQuery1 = " TRUNCATE TABLE aggregatedgenerationpertype ";
        try {
            jdbcTemplate.execute(sqlQuery1);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        
        //String sqlQuery2 = " DELETE QUICK FROM dayaheadtotalloadforecast WHERE 1 ";
        String sqlQuery2 = " TRUNCATE TABLE dayaheadtotalloadforecast ";
        try {
            jdbcTemplate.execute(sqlQuery2);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
        //String sqlQuery3 = " DELETE QUICK FROM user WHERE 1";
        String sqlQuery3 = " TRUNCATE TABLE user";
        try {
            jdbcTemplate.execute(sqlQuery3);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
        //String sqlQuery4 = " DELETE QUICK FROM administrator WHERE 1";
        String sqlQuery4 = "  TRUNCATE TABLE administrator";
        try {
            jdbcTemplate.execute(sqlQuery4);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
        
        String sqlQuery5 = "INSERT INTO administrator( username, password, email, token) VALUES ('admin','321nimda','admin@here.com','admintoken123')";
        try {
            jdbcTemplate.execute(sqlQuery5);
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e);
        } 
    }

    /*
	 * Check if the input token corresponds in an actual admin.
	 */
	public boolean isAdmin(String token) {
		int count = jdbcTemplate.queryForObject("select count(*) from administrator where "
				+ "token=?", new Object[] { token }, Integer.class);
		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * Check if the input token corresponds in an actual user.
	 */
	public boolean isUser(String token) {
		int count = jdbcTemplate.queryForObject("select count(*) from user where "
				+ "token=?", new Object[] { token }, Integer.class);
		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
    }
    public boolean hasQuotasLeft(String token){
        int quotas = jdbcTemplate.queryForObject("select quotas from user where "
                + "token=?", new Object[] { token }, Integer.class);
        if (quotas > 1) return true;
        else return false;
    }
    public boolean isUsers(String username) {
		int count = jdbcTemplate.queryForObject("select count(*) from user where "
				+ "username=?", new Object[] { username }, Integer.class);
		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
    }
    public Optional<User> getUserByUsername(String username){
        Object[] sqlparams = new Object[] {username};
    	List<User> users = jdbcTemplate.query("select * from user where username = ?",sqlparams , (ResultSet rs, int rowNum) -> {
            User dataLoad = new User(rs.getString(2),rs.getString(3),rs.getString(5),rs.getInt(6));
            return dataLoad;

        });
		if (users.size() == 1)  {
			return Optional.of(users.get(0));
		}
		else {
			return Optional.empty();
		}
    }
    public User updateUserByUsername(String username , String email,Integer quotas){
        if (email!= null && quotas != -100){
            Object[] sqlparams = new Object[] { email,quotas, username};
            jdbcTemplate.update("UPDATE user SET email = ?,quotas = ? WHERE username = ?",sqlparams);
            return new User(username,email,quotas);
        }     
        else if (email== null  ){
            Object[] sqlparams = new Object[] { quotas, username};
            jdbcTemplate.update("UPDATE user SET quotas = ? WHERE username = ?",sqlparams);
            email = getEmailFromUsername(username);
            return new User(username,email,quotas);
        }
        else if (quotas== -100    ){
            Object[] sqlparams = new Object[] { email, username};
            jdbcTemplate.update("UPDATE user SET email = ? WHERE username = ?",sqlparams);
            quotas = getQuotasFromUsername(username);
            return new User(username,email,quotas);
        }
        else {
            return null;
        }
    }

    public String addUser(String username, String email, String password) {
		/*
		 * Insert the new user in the User table.
		 */
        if (isUsers(username)){
            return null;
        }
    	String token = getRandomString();
		jdbcTemplate.update("insert into user(username, password,   email,  token) "
					+ "values(?, ?, ?, ?)", new Object[] {username, password, email, token});
		return token;
		
    }

    public String createUser(String username, String email, String password, Integer quotas) {
		/*
		 * Insert the new user in the User table.
		 */
        if (isUsers(username)){
            return null;
        }
		jdbcTemplate.update("insert into user(username, password,   email , token, quotas) "
					+ "values(?, ?, ?, -1, ?)", new Object[] {username, password,email, quotas});
		return "OK";
    }
    
    public String getRandomString() {
		int leftLimit = 97; /* letter 'a' */
		int rightLimit = 122; /* letter 'z' */
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) 
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
    }

    public String getToken( String username, String password) throws ResourceException{
		/*
		 * Check if a user logged in.
		 */
		int count = jdbcTemplate.queryForObject("select count(*) from user where "
				+ "username=? and password=?", new Object[] { username, password}, Integer.class);
		String token;
		if (count > 0) {
			/*
			 * A user logged in.
			 */
			int userId = jdbcTemplate.queryForObject("select id from user where "
					+ "username=? and password=?", new Object[] { username, password }, Integer.class);
			token = getRandomString();
			/*
			 * Update newly created token.
			 */
			jdbcTemplate.update("update user set token=? where id=?", new Object[] {token, userId});
		}
		else {
			/*
			 * Check if a administrator logged in.
			 */
			count = jdbcTemplate.queryForObject("select count(*) from administrator where "
					+ "username=? and password=?", new Object[] { username, password}, Integer.class);
			if (count > 0) {
				/*
				 * An administrator logged in.
				 */
				int adminId = jdbcTemplate.queryForObject("select id from administrator where "
						+ "username=? and password=?", new Object[] { username, password }, Integer.class);
				token = getRandomString();
				/*
				 * Update newly created token.
				 */
				jdbcTemplate.update("update administrator set token=? where id=?", new Object[] {token, adminId});
			}
			else {
				/*
				 * Wrong username or password.
				 */
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Wrong username or password");
			}
		}
		return token;
	}

    public String tokenIsValid(String auth) throws ResourceException{
		/*
		 * Check if token exists in user
		 */
		int id;
		int count = jdbcTemplate.queryForObject("select count(*) from user where "
				+ "token=?", new Object[] { auth }, Integer.class);
		if (count == 0) {
			/*
			 * Check if token exists in Administrator
			 */
            //System.out.println(auth);
			count = jdbcTemplate.queryForObject("select count(*) from administrator where "
					+ "token=?", new Object[] { auth }, Integer.class);
			if (count == 0) {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Token not found");
			}
			else {
				id = jdbcTemplate.queryForObject("select id from administrator where "
						+ "token=?", new Object[] { auth }, Integer.class);
				/*
				 * Disable token.
				 */
				jdbcTemplate.update("update administrator set token=? where id=?", new Object[] {-1, id});
			}
		}
		else {
			id = jdbcTemplate.queryForObject("select id from user where "
					+ "token=?", new Object[] { auth }, Integer.class);
			/*
			 * Disable token.
			 */
			jdbcTemplate.update("update user set token=? where id=?", new Object[] {-1, id});
		}
		return new String("OK");
	}
}