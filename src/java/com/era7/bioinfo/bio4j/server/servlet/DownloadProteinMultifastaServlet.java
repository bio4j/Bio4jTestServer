/*
 * Copyright (C) 2010-2011  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.era7.bioinfo.bio4j.server.servlet;

import com.era7.bioinfo.bio4j.server.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4j.server.RequestList;
import com.era7.bioinfo.bio4jmodel.util.Bio4jManager;
import com.era7.bioinfo.bio4jmodel.util.NodeRetriever;
import com.era7.lib.bioinfoxml.uniprot.ProteinXML;
import com.era7.lib.communication.xml.Request;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;

/**
 * Downloads a multifasta file including the corresponding fasta format for every protein passed as a parameter.
 * @author ppareja
 */
public class DownloadProteinMultifastaServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        servletLogic(request, response);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        servletLogic(request, response);


    }

    private void servletLogic(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {


        OutputStream out = response.getOutputStream();

        try {

            

            Request myReq = new Request(request.getParameter(Request.TAG_NAME));

            if (myReq.getMethod().equals(RequestList.DOWNLOAD_PROTEIN_MULTIFASTA_REQUEST)) {
                
                Bio4jManager manager = new Bio4jManager(CommonData.DATABASE_FOLDER);

                String fileName = myReq.getParameters().getChildText("file_name");

                Element proteinsXml = myReq.getParameters().getChild("proteins");

                List<Element> proteins = proteinsXml.getChildren(ProteinXML.TAG_NAME);

                int responseLength = 0;

                for (Element element : proteins) {
                    ProteinXML protein = new ProteinXML(element);

                    System.out.println("retrieving sequence for: " + protein.getId());

                    NodeRetriever nodeRetriever = new NodeRetriever(manager);
                    ProteinNode proteinNode = nodeRetriever.getProteinNodeByAccession(protein.getId());

                    StringBuilder resultStBuilder = new StringBuilder();
                    com.era7.bioinfo.bio4j.server.util.FastaUtil.getFastaFormatForProtein(proteinNode, resultStBuilder);
                  
                    byte[] byteArray = resultStBuilder.toString().getBytes();
                    responseLength += byteArray.length;
                    out.write(byteArray);
                    out.flush();
                }

                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".fasta");

                response.setContentLength(responseLength);

            } else {
                out.write("There is no such method".getBytes());
            }

        } catch (Exception e) {
            out.write("Error...".getBytes());
            out.write(e.getStackTrace()[0].toString().getBytes());
        }
        
        out.flush();
        out.close();


    }
}
