//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2010 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.commons.jdbc;

import static org.deegree.commons.jdbc.ConnectionManager.getConnection;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;

/**
 * <code>LayerDatabaseHelper</code>
 * 
 * @author <a href="mailto:schmitz@lat-lon.de">Andreas Schmitz</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 */
public class LayerDatabaseHelper {

    private static final Logger LOG = getLogger( LayerDatabaseHelper.class );

    /**
     * @param layersConnId
     * @param name
     * @param title
     * @param connId
     * @param table
     * @param crs
     */
    public static void addLayer( String layersConnId, String name, String title, String connId, String table, String crs ) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection( layersConnId );
            stmt = conn.prepareStatement( "insert into layers (name, title, connid, sourcetable, crs) values (?, ?, ?, ?, ?)" );
            stmt.setString( 1, name );
            stmt.setString( 2, title == null ? name : title );
            stmt.setString( 3, connId );
            stmt.setString( 4, table );
            stmt.setString( 5, crs );
            stmt.executeUpdate();
        } catch ( SQLException e ) {
            LOG.info( "A DB error occurred: '{}'.", e.getLocalizedMessage() );
            LOG.trace( "Stack trace:", e );
        } finally {
            if ( stmt != null ) {
                try {
                    stmt.close();
                } catch ( SQLException e ) {
                    LOG.info( "A DB error occurred: '{}'.", e.getLocalizedMessage() );
                    LOG.trace( "Stack trace:", e );
                }
            }
            if ( conn != null ) {
                try {
                    conn.close();
                } catch ( SQLException e ) {
                    LOG.info( "A DB error occurred: '{}'.", e.getLocalizedMessage() );
                    LOG.trace( "Stack trace:", e );
                }
            }
        }
    }

}
