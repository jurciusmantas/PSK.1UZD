package mybatis.dao;

import java.util.List;
import mybatis.model.Destinations;
import org.mybatis.cdi.Mapper;

@Mapper
public interface DestinationsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table destinations
     *
     * @mbg.generated Sun Apr 12 15:28:16 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table destinations
     *
     * @mbg.generated Sun Apr 12 15:28:16 EEST 2020
     */
    int insert(Destinations record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table destinations
     *
     * @mbg.generated Sun Apr 12 15:28:16 EEST 2020
     */
    Destinations selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table destinations
     *
     * @mbg.generated Sun Apr 12 15:28:16 EEST 2020
     */
    List<Destinations> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table destinations
     *
     * @mbg.generated Sun Apr 12 15:28:16 EEST 2020
     */
    int updateByPrimaryKey(Destinations record);
}