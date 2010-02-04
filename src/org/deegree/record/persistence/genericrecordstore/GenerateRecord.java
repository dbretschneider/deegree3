//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
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
package org.deegree.record.persistence.genericrecordstore;

import java.util.List;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

/**
 * Generates the records in DC and ISO in all representation types (brief, summary, full).
 * 
 * @author <a href="mailto:thomas@lat-lon.de">Steffen Thomas</a>
 * @author last edited by: $Author: thomas $
 * 
 * @version $Revision: $, $Date: $
 */
public class GenerateRecord {

    private OMFactory factory = OMAbstractFactory.getOMFactory();

    private OMNamespace namespaceDC = factory.createOMNamespace( "http://purl.org/dc/elements/1.1/", "dc" );

    private OMNamespace namespaceDCT = factory.createOMNamespace( "http://purl.org/dc/terms/", "dct" );
    
    private OMNamespace namespaceGMD = factory.createOMNamespace( "http://www.isotc211.org/2005/gmd", "" );

    private OMElement identifier = null;

    private OMElement language = null;

    private OMElement characterSet = null;

    private OMElement parentIdentifier = null;

    private List<OMElement> hierarchyLevel = null;

    private List<OMElement> hierarchyLevelName = null;

    private List<OMElement> contact = null;

    private OMElement dateStamp = null;

    private OMElement metadataStandardName = null;

    private OMElement metadataStandardVersion = null;

    private OMElement dataSetURI = null;

    private List<OMElement> locale = null;

    private List<OMElement> spatialRepresentationInfo = null;

    private List<OMElement> referenceSystemInfo = null;

    private List<OMElement> metadataExtensionInfo = null;

    private List<OMElement> identificationInfo = null;

    private List<OMElement> contentInfo = null;

    private OMElement distributionInfo = null;

    private List<OMElement> dataQualityInfo = null;

    private List<OMElement> portrayalCatalogueInfo = null;

    private List<OMElement> metadataConstraints = null;

    private List<OMElement> applicationSchemaInfo = null;

    private OMElement metadataMaintenance = null;

    private List<OMElement> series = null;

    private List<OMElement> describes = null;

    private List<OMElement> propertyType = null;

    private List<OMElement> featureType = null;

    private List<OMElement> featureAttribute = null;

    private OMElement isoBriefElement = null;

    private OMElement isoSummaryElement = null;

    private OMElement isoFullElement = null;
    
    private QueryableProperties qp;
    
    private ReturnableProperties rp;

    /**
     * Adds the elements for the brief representation in ISO.
     * 
     * @return OMElement
     */
    private OMElement setISOBriefElements() {

        isoBriefElement = factory.createOMElement( "MD_Metadata", namespaceGMD );
        // identifier
        isoBriefElement.addChild( identifier );
        // type
        if ( hierarchyLevel != null ) {
            for ( OMElement elem : hierarchyLevel ) {
                isoBriefElement.addChild( elem );
            }
        }
        // BoundingBox, GraphicOverview, ServiceType, ServiceTypeVersion
        if ( identificationInfo != null ) {
            for ( OMElement elem : identificationInfo ) {
                isoBriefElement.addChild( elem );
            }
        }
        return isoBriefElement;

    }

    /**
     * Adds the elements for the summary representation in ISO.
     * 
     * @return OMElement
     */
    private OMElement setISOSummaryElements() {

        isoSummaryElement = factory.createOMElement( "MD_Metadata", namespaceGMD );
        // identifier
        isoSummaryElement.addChild( identifier );
        // Language
        if ( language != null ) {
            isoSummaryElement.addChild( language );
        }
        // MetadataCharacterSet
        if ( characterSet != null ) {
            isoSummaryElement.addChild( characterSet );
        }
        // ParentIdentifier
        if ( parentIdentifier != null ) {
            isoSummaryElement.addChild( parentIdentifier );
        }
        // type
        if ( hierarchyLevel != null ) {
            for ( OMElement elem : hierarchyLevel ) {
                isoSummaryElement.addChild( elem );
            }
        }
        // HierarchieLevelName
        if ( hierarchyLevelName != null ) {
            for ( OMElement elem : hierarchyLevelName ) {
                isoSummaryElement.addChild( elem );
            }
        }
        // Modified
        if ( dateStamp != null ) {
            isoSummaryElement.addChild( dateStamp );
        }
        // MetadataStandardName
        if ( metadataStandardName != null ) {
            isoSummaryElement.addChild( metadataStandardName );
        }
        // MetadataStandardVersion
        if ( metadataStandardVersion != null ) {
            isoSummaryElement.addChild( metadataStandardVersion );
        }
        // ReferenceInfoSystem
        if ( referenceSystemInfo != null || referenceSystemInfo.size() != 0 ) {
            for ( OMElement refSysInfoElem : referenceSystemInfo ) {
                isoSummaryElement.addChild( refSysInfoElem );
            }
        }
        // BoundingBox, GraphicOverview, ServiceType, ServiceTypeVersion, Abstract, Creator, Contributor, CouplingType,
        // Publisher, ResourceIdentifier, ResourceLanguage, RevisionDate,
        // Rights, ServiceOperation, SpatialResolution, SpatialRepresentationType, TopicCategory
        if ( identificationInfo != null ) {
            for ( OMElement elem : identificationInfo ) {
                isoSummaryElement.addChild( elem );
            }
        }
        // Format, FormatVersion, OnlineResource
        if ( distributionInfo != null ) {
            isoSummaryElement.addChild( distributionInfo );
        }
        // Lineage
        if ( dataQualityInfo != null ) {
            for ( OMElement elem : dataQualityInfo ) {
                isoSummaryElement.addChild( elem );
            }
        }

        return isoSummaryElement;

    }

    /**
     * Adds the elements for the full representation in ISO.
     * 
     * @return OMElement
     */
    private OMElement setISOFullElements() {

        isoFullElement = factory.createOMElement( "MD_Metadata", namespaceGMD );
        // identifier
        isoFullElement.addChild( identifier );
        // Language
        if ( language != null ) {
            isoFullElement.addChild( language );
        }
        // MetadataCharacterSet
        if ( characterSet != null ) {
            isoFullElement.addChild( characterSet );
        }
        // ParentIdentifier
        if ( parentIdentifier != null ) {
            isoFullElement.addChild( parentIdentifier );
        }
        // type
        if ( hierarchyLevel != null ) {
            for ( OMElement elem : hierarchyLevel ) {
                isoFullElement.addChild( elem );
            }
        }
        // HierarchieLevelName
        if ( hierarchyLevelName != null ) {
            for ( OMElement elem : hierarchyLevelName ) {
                isoFullElement.addChild( elem );
            }
        }
        // contact
        for ( OMElement elem : contact ) {
            isoFullElement.addChild( elem );
        }
        // Modified
        if ( dateStamp != null ) {
            isoFullElement.addChild( dateStamp );
        }
        // MetadataStandardName
        if ( metadataStandardName != null ) {
            isoFullElement.addChild( metadataStandardName );
        }
        // MetadataStandardVersion
        if ( metadataStandardVersion != null ) {
            isoFullElement.addChild( metadataStandardVersion );
        }
        // dataSetURI
        if ( dataSetURI != null ) {
            isoFullElement.addChild( dataSetURI );
        }
        // locale
        if ( locale != null ) {
            for ( OMElement elem : locale ) {
                isoFullElement.addChild( elem );
            }
        }
        // spatialRepresenatationInfo
        if ( spatialRepresentationInfo != null ) {
            for ( OMElement elem : spatialRepresentationInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // ReferenceInfoSystem
        if ( referenceSystemInfo != null || referenceSystemInfo.size() != 0 ) {
            for ( OMElement refSysInfoElem : referenceSystemInfo ) {
                isoFullElement.addChild( refSysInfoElem );
            }
        }
        // metadataExtensionInfo
        if ( metadataExtensionInfo != null ) {
            for ( OMElement elem : metadataExtensionInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // BoundingBox, GraphicOverview, ServiceType, ServiceTypeVersion, Abstract, Creator, Contributor, CouplingType,
        // Publisher, ResourceIdentifier, ResourceLanguage, RevisionDate,
        // Rights, ServiceOperation, SpatialResolution, SpatialRepresentationType, TopicCategory
        if ( identificationInfo != null ) {
            for ( OMElement elem : identificationInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // contentInfo
        if ( contentInfo != null ) {
            for ( OMElement elem : contentInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // Format, FormatVersion, OnlineResource
        if ( distributionInfo != null ) {
            isoFullElement.addChild( distributionInfo );
        }
        // Lineage
        if ( dataQualityInfo != null ) {
            for ( OMElement elem : dataQualityInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // portrayalCatalogueInfo
        if ( portrayalCatalogueInfo != null ) {
            for ( OMElement elem : portrayalCatalogueInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // metadataConstraints
        if ( metadataConstraints != null ) {
            for ( OMElement elem : metadataConstraints ) {
                isoFullElement.addChild( elem );
            }
        }
        // applicationSchemaInfo
        if ( applicationSchemaInfo != null ) {
            for ( OMElement elem : applicationSchemaInfo ) {
                isoFullElement.addChild( elem );
            }
        }
        // metadataMaintenance
        if ( metadataMaintenance != null ) {
            isoFullElement.addChild( metadataMaintenance );
        }
        // series
        if ( series != null ) {
            for ( OMElement elem : series ) {
                isoFullElement.addChild( elem );
            }
        }
        // describes
        if ( describes != null ) {
            for ( OMElement elem : describes ) {
                isoFullElement.addChild( elem );
            }
        }
        // propertyType
        if ( propertyType != null ) {
            for ( OMElement elem : propertyType ) {
                isoFullElement.addChild( elem );
            }
        }
        // featureType
        if ( featureType != null ) {
            for ( OMElement elem : featureType ) {
                isoFullElement.addChild( elem );
            }
        }
        // featureAttribute
        if ( featureAttribute != null ) {
            for ( OMElement elem : featureAttribute ) {
                isoFullElement.addChild( elem );
            }
        }

        return isoFullElement;

    }
    
    
    /**
     * Creation of the "brief"-representation in DC of a record.
     * 
     * @param factory
     * @param omElement
     */
    private void setDCBriefElements( OMElement omElement ) {

        
        OMElement omType = factory.createOMElement( "type", namespaceDC );

        for(String identifier : qp.getIdentifierDC()){
        OMElement omIdentifier = factory.createOMElement( "identifier", namespaceDC );
        omIdentifier.setText( identifier );
        omElement.addChild( omIdentifier );
        }
        for ( String title : qp.getTitle() ) {
            OMElement omTitle = factory.createOMElement( "title", namespaceDC );
            omTitle.setText( title );
            omElement.addChild( omTitle );
        }
        if ( qp.getType() != null ) {
            omType.setText( qp.getType() );
        } else {
            omType.setText( "" );
        }
        omElement.addChild( omType );
    }
    
    /**
     * Creation of the "summary"-representation in DC of a record.
     * 
     * @param omElement
     */
    private void setDCSummaryElements( OMElement omElement ) {
        setDCBriefElements( omElement );

        OMElement omSubject;
        // dc:subject
        for ( Keyword subjects : qp.getKeywords() ) {
            for ( String subject : subjects.getKeywords() ) {
                omSubject = factory.createOMElement( "subject", namespaceDC );
                omSubject.setText( subject );
                omElement.addChild( omSubject );
            }
        }
        if ( qp.getTopicCategory() != null ) {
            for ( String subject : qp.getTopicCategory() ) {
                omSubject = factory.createOMElement( "subject", namespaceDC );
                omSubject.setText( subject );
                omElement.addChild( omSubject );
            }
        }
        // dc:format
        if ( qp.getFormat() != null || qp.getFormat().size() != 0 ) {
            for ( Format format : qp.getFormat() ) {
                OMElement omFormat = factory.createOMElement( "format", namespaceDC );
                omFormat.setText( format.getName() );
                omElement.addChild( omFormat );
            }
        } else {
            OMElement omFormat = factory.createOMElement( "format", namespaceDC );
            omElement.addChild( omFormat );
        }

        // dct:relation
        if ( rp.getRelation() != null || rp.getRelation().size() != 0 ) {
            for ( String relation : rp.getRelation() ) {
                OMElement omFormat = factory.createOMElement( "relation", namespaceDC );
                omFormat.setText( relation );
                omElement.addChild( omFormat );
            }
        } else {
            OMElement omFormat = factory.createOMElement( "relation", namespaceDC );
            omElement.addChild( omFormat );
        }

        // dct:modified
        // for ( Date date : qp.getModified() ) {
        // OMElement omModified = factory.createOMElement( "modified", namespaceDCT );
        // omModified.setText( date.toString() );
        // omElement.addChild( omModified );
        // }
        if ( qp.getModified() != null ) {
            OMElement omModified = factory.createOMElement( "modified", namespaceDCT );
            omModified.setText( qp.getModified().toString() );
            omElement.addChild( omModified );
        } else {
            OMElement omModified = factory.createOMElement( "modified", namespaceDCT );
            omElement.addChild( omModified );
        }
        // dct:abstract
        for ( String _abstract : qp.get_abstract() ) {
            OMElement omAbstract = factory.createOMElement( "abstract", namespaceDCT );
            omAbstract.setText( _abstract.toString() );
            omElement.addChild( omAbstract );
        }

        // dct:spatial
        // TODO

    }

    /**
     * Creation of the "full"-representation in DC of a record.
     * 
     * @param omElement
     */
    private void setDCFullElements( OMElement omElement ) {

        setDCSummaryElements( omElement );

        if ( rp.getCreator() != null ) {
            OMElement omCreator = factory.createOMElement( "creator", namespaceDC );
            omCreator.setText( rp.getCreator() );
            omElement.addChild( omCreator );
        }

        if ( rp.getPublisher() != null ) {
            OMElement omPublisher = factory.createOMElement( "publisher", namespaceDC );
            omPublisher.setText( rp.getPublisher() );
            omElement.addChild( omPublisher );
        }
        if ( rp.getContributor() != null ) {
            OMElement omContributor = factory.createOMElement( "contributor", namespaceDC );
            omContributor.setText( rp.getContributor() );
            omElement.addChild( omContributor );
        }
        if ( rp.getSource() != null ) {
            OMElement omSource = factory.createOMElement( "source", namespaceDC );
            omSource.setText( rp.getSource() );
            omElement.addChild( omSource );
        }
        if ( rp.getLanguage() != null ) {
            OMElement omLanguage = factory.createOMElement( "language", namespaceDC );
            omLanguage.setText( rp.getLanguage() );
            omElement.addChild( omLanguage );
        }

        // dc:rights
        if ( rp.getRights() != null ) {
            for ( String rights : rp.getRights() ) {
                OMElement omRights = factory.createOMElement( "rights", namespaceDC );
                omRights.setText( rights );
                omElement.addChild( omRights );
            }
        }

    }
    
    

    /**
     * @return the isoBriefElement
     */
    public OMElement getIsoBriefElement() {
        return setISOBriefElements();
    }

    /**
     * @return the isoSummaryElement
     */
    public OMElement getIsoSummaryElement() {
        return setISOSummaryElements();
    }

    /**
     * @return the isoFullElement
     */
    public OMElement getIsoFullElement() {
        return setISOFullElements();
    }
    
    

    /**
     * @return the dcBriefElement
     */
    public void buildElementAsDcBriefElement(OMElement element) {
        setDCBriefElements(element );
    }

    /**
     * @return the dcSummaryElement
     */
    public void buildElementAsDcSummaryElement(OMElement element) {
        setDCSummaryElements( element );
    }

    /**
     * @return the dcFullElement
     */
    public void buildElementAsDcFullElement(OMElement element) {
        setDCFullElements( element );
    }

    /**
     * @return the identifier
     */
    public OMElement getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier
     *            the identifier to set
     */
    public void setIdentifier( OMElement identifier ) {
        this.identifier = identifier;
    }

    /**
     * @return the language
     */
    public OMElement getLanguage() {
        return language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage( OMElement language ) {
        this.language = language;
    }

    /**
     * @return the characterSet
     */
    public OMElement getCharacterSet() {
        return characterSet;
    }

    /**
     * @param characterSet
     *            the characterSet to set
     */
    public void setCharacterSet( OMElement characterSet ) {
        this.characterSet = characterSet;
    }

    /**
     * @return the parentIdentifier
     */
    public OMElement getParentIdentifier() {
        return parentIdentifier;
    }

    /**
     * @param parentIdentifier
     *            the parentIdentifier to set
     */
    public void setParentIdentifier( OMElement parentIdentifier ) {
        this.parentIdentifier = parentIdentifier;
    }

    /**
     * @return the hierarchyLevel
     */
    public List<OMElement> getHierarchyLevel() {
        return hierarchyLevel;
    }

    /**
     * @param hierarchyLevel
     *            the hierarchyLevel to set
     */
    public void setHierarchyLevel( List<OMElement> hierarchyLevel ) {
        this.hierarchyLevel = hierarchyLevel;
    }

    /**
     * @return the hierarchyLevelName
     */
    public List<OMElement> getHierarchyLevelName() {
        return hierarchyLevelName;
    }

    /**
     * @param hierarchyLevelName
     *            the hierarchyLevelName to set
     */
    public void setHierarchyLevelName( List<OMElement> hierarchyLevelName ) {
        this.hierarchyLevelName = hierarchyLevelName;
    }

    /**
     * @return the contact
     */
    public List<OMElement> getContact() {
        return contact;
    }

    /**
     * @param contact
     *            the contact to set
     */
    public void setContact( List<OMElement> contact ) {
        this.contact = contact;
    }

    /**
     * @return the dateStamp
     */
    public OMElement getDateStamp() {
        return dateStamp;
    }

    /**
     * @param dateStamp
     *            the dateStamp to set
     */
    public void setDateStamp( OMElement dateStamp ) {
        this.dateStamp = dateStamp;
    }

    /**
     * @return the metadataStandardName
     */
    public OMElement getMetadataStandardName() {
        return metadataStandardName;
    }

    /**
     * @param metadataStandardName
     *            the metadataStandardName to set
     */
    public void setMetadataStandardName( OMElement metadataStandardName ) {
        this.metadataStandardName = metadataStandardName;
    }

    /**
     * @return the metadataStandardVersion
     */
    public OMElement getMetadataStandardVersion() {
        return metadataStandardVersion;
    }

    /**
     * @param metadataStandardVersion
     *            the metadataStandardVersion to set
     */
    public void setMetadataStandardVersion( OMElement metadataStandardVersion ) {
        this.metadataStandardVersion = metadataStandardVersion;
    }

    /**
     * @param dataSetURI
     *            the dataSetURI to set
     */
    public void setDataSetURI( OMElement dataSetURI ) {
        this.dataSetURI = dataSetURI;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale( List<OMElement> locale ) {
        this.locale = locale;
    }

    /**
     * @param spatialRepresentationInfo
     *            the spatialRepresentationInfo to set
     */
    public void setSpatialRepresentationInfo( List<OMElement> spatialRepresentationInfo ) {
        this.spatialRepresentationInfo = spatialRepresentationInfo;
    }

    /**
     * @return the referenceSystemInfo
     */
    public List<OMElement> getReferenceSystemInfo() {
        return referenceSystemInfo;
    }

    /**
     * @param referenceSystemInfo
     *            the referenceSystemInfo to set
     */
    public void setReferenceSystemInfo( List<OMElement> referenceSystemInfo ) {
        this.referenceSystemInfo = referenceSystemInfo;
    }

    /**
     * @return the metadataExtensionInfo
     */
    public List<OMElement> getMetadataExtensionInfo() {
        return metadataExtensionInfo;
    }

    /**
     * @param metadataExtensionInfo
     *            the metadataExtensionInfo to set
     */
    public void setMetadataExtensionInfo( List<OMElement> metadataExtensionInfo ) {
        this.metadataExtensionInfo = metadataExtensionInfo;
    }

    /**
     * @param contentInfo
     *            the contentInfo to set
     */
    public void setContentInfo( List<OMElement> contentInfo ) {
        this.contentInfo = contentInfo;
    }

    /**
     * @return the distributionInfo
     */
    public OMElement getDistributionInfo() {
        return distributionInfo;
    }

    /**
     * @param distributionInfo
     *            the distributionInfo to set
     */
    public void setDistributionInfo( OMElement distributionInfo ) {
        this.distributionInfo = distributionInfo;
    }

    /**
     * @return the dataQualityInfo
     */
    public List<OMElement> getDataQualityInfo() {
        return dataQualityInfo;
    }

    /**
     * @param dataQualityInfo
     *            the dataQualityInfo to set
     */
    public void setDataQualityInfo( List<OMElement> dataQualityInfo ) {
        this.dataQualityInfo = dataQualityInfo;
    }

    /**
     * @param portrayalCatalogueInfo
     *            the portrayalCatalogueInfo to set
     */
    public void setPortrayalCatalogueInfo( List<OMElement> portrayalCatalogueInfo ) {
        this.portrayalCatalogueInfo = portrayalCatalogueInfo;
    }

    /**
     * @param metadataConstraints
     *            the metadataConstraints to set
     */
    public void setMetadataConstraints( List<OMElement> metadataConstraints ) {
        this.metadataConstraints = metadataConstraints;
    }

    /**
     * @param applicationSchemaInfo
     *            the applicationSchemaInfo to set
     */
    public void setApplicationSchemaInfo( List<OMElement> applicationSchemaInfo ) {
        this.applicationSchemaInfo = applicationSchemaInfo;
    }

    /**
     * @param metadataMaintenance
     *            the metadataMaintenance to set
     */
    public void setMetadataMaintenance( OMElement metadataMaintenance ) {
        this.metadataMaintenance = metadataMaintenance;
    }

    /**
     * @param series
     *            the series to set
     */
    public void setSeries( List<OMElement> series ) {
        this.series = series;
    }

    /**
     * @param describes
     *            the describes to set
     */
    public void setDescribes( List<OMElement> describes ) {
        this.describes = describes;
    }

    /**
     * @param propertyType
     *            the propertyType to set
     */
    public void setPropertyType( List<OMElement> propertyType ) {
        this.propertyType = propertyType;
    }

    /**
     * @param featureType
     *            the featureType to set
     */
    public void setFeatureType( List<OMElement> featureType ) {
        this.featureType = featureType;
    }

    /**
     * @param featureAttribute
     *            the featureAttribute to set
     */
    public void setFeatureAttribute( List<OMElement> featureAttribute ) {
        this.featureAttribute = featureAttribute;
    }

    /**
     * @return the identificationInfo
     */
    public List<OMElement> getIdentificationInfo() {
        return identificationInfo;
    }

    /**
     * @param identificationInfo
     *            the identificationInfo to set
     */
    public void setIdentificationInfo( List<OMElement> identificationInfo ) {
        this.identificationInfo = identificationInfo;
    }

    /**
     * @return the QueryableProperties
     */
    public QueryableProperties getQueryableProperties() {
        return qp;
    }

    /**
     * @param QueryableProperties the QueryableProperties to set
     */
    public void setQueryableProperties( QueryableProperties qp ) {
        this.qp = qp;
    }

    /**
     * @return the ReturnableProperties
     */
    public ReturnableProperties getReturnableProperties() {
        return rp;
    }

    /**
     * @param rp the rp to set
     */
    public void setReturnableProperties( ReturnableProperties rp ) {
        this.rp = rp;
    }
    
    

}
