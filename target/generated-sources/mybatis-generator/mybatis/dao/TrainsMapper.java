package mybatis.dao;

import java.util.List;
import mybatis.model.Trains;

public interface TrainsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trains
     *
     * @mbg.generated Sat Apr 11 00:13:44 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trains
     *
     * @mbg.generated Sat Apr 11 00:13:44 EEST 2020
     */
    int insert(Trains record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trains
     *
     * @mbg.generated Sat Apr 11 00:13:44 EEST 2020
     */
    Trains selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trains
     *
     * @mbg.generated Sat Apr 11 00:13:44 EEST 2020
     */
    List<Trains> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trains
     *
     * @mbg.generated Sat Apr 11 00:13:44 EEST 2020
     */
    int updateByPrimaryKey(Trains record);
}