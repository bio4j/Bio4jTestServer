/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.era7.bioinfo.bio4j.server;

/**
 *
 * @author ppareja
 */
public class RequestList {

    public static final String DOWNLOAD_PROTEIN_MULTIFASTA_REQUEST = "download_protein_multifasta";
    public static final String GET_PROTEIN_MULTIFASTA_REQUEST = "get_protein_multifasta";
    public static final String GET_GENE_UNIPROT_ACCESSIONS_REQUEST = "get_gene_uniprot_accessions";

    public static final String GO_ANNOTATION_REQUEST = "go_annotation";
    public static final String GO_SLIM_REQUEST = "go_slim";
    public static final String DOWNLOAD_GO_ANNOTATION_REQUEST = "download_go_annotation";
    public static final String DOWNLOAD_GO_SLIM_REQUEST = "download_go_slim";

    public static final String GET_PROTEIN_INFO_REQUEST = "get_protein_info";

    public static final String UPLOAD_BLAST_AND_GET_COVERAGE_XML_REQUEST = "upload_blast_and_get_coverage_xml";
    public static final String LOAD_BLAST_FILE_FROM_URL_AND_GET_COVERAGE_XML_REQUEST = "load_blast_file_from_url_and_get_coverage_xml";

    public static final String GET_GO_ANNOTATION_CHART_XML_REQUEST = "get_go_annotation_chart_xml";
    public static final String GET_GO_SLIM_CHART_XML_REQUEST = "get_go_slim_chart_xml";

    public static final String GET_GO_ANNOTATION_GEXF_REQUEST = "get_go_annotation_gexf";
    public static final String GET_GO_ANNOTATION_GEXF_FROM_URL_REQUEST = "get_go_annotation_gexf_from_url";
    public static final String DOWNLOAD_GO_ANNOTATION_GEXF_FROM_URL_REQUEST = "download_go_annotation_gexf_from_url";
    
    public static final String GET_CUFF_LINKS_FULL_REPORT_REQUEST = "get_cuff_links_full_report";
    public static final String CUFFLINKS_QUALITY_CONTROL_REQUEST = "cufflinks_quality_control";

}
