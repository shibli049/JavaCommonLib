/*
 * Copyright (C) 2016 shibli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.shibli049.db;



/**
 *
 * @author shibli
 */
public class DbUtil {


    /**
     * Generates a paginated native {@code query} with the specified query
     * String for Oracle.
     * <p><b>Caution:</b> {@code endIndex} less than {@code 1} or
     * {@code startIndex} will return empty {@link java.sql.ResultSet}/{@link java.util.List}
     * when the generated query executes.</p>
     * <p>The pagination query will be:
     * SELECT * FROM (
     * SELECT A.*, rownum rn FROM ( {@code query}
     * ) A WHERE rownum &lt;= endIndex  ) B  WHERE rn >= 1</p>
     *
     * @param query
     *          native query String
     * @param startIndex
     *        rownum >= startIndex
     * @param endIndex
     *        rownum &lt;= endIndex
     * @return Oracle pagination query with start and end index
     *
     */
    public static String generateOraclePagingQuery(String query, int startIndex, int endIndex){

        String paginQuery = "SELECT * FROM ("
                + "    SELECT A.*, rownum rn FROM ( "
                + query
                + " ) A WHERE rownum <= " + endIndex
                + "  ) B"
                + "  WHERE rn >= " + startIndex;
        return paginQuery;
    }
}
