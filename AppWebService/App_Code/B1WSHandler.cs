//****************************************************************************
//
//  File:      B1WSHandler.cs
//
//  Copyright (c) SAP 
//
// THIS CODE AND INFORMATION IS PROVIDED "AS IS" WITHOUT WARRANTY OF
// ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
//****************************************************************************
using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.IO;
using System.Web.Services.Protocols;
using System.Web.Hosting;



/// <summary>
/// HttpHandler in charge of redirecting all B1WS calls to B1 DI Server 
/// </summary>
/// 
public class B1WSHandler : IHttpHandler
{
    /// <summary>
    /// DI Server Node
    /// </summary>
    private SBODI_Server.Node DIServerNode;

    /// <summary>
    /// Create an instance of the DI Server Node 
    /// </summary>
    public B1WSHandler()
    {
        DIServerNode = (SBODI_Server.Node)new SBODI_Server.Node();
    }

    /// <summary>
    /// Method processing all B1WS requests and redirecting them to DI Server
    /// </summary>
    /// <param name="context">HttpContext containing all information about the request and response</param>
    public void ProcessRequest(HttpContext context)
    {
        //read request
        HttpRequest Request = context.Request;
        HttpResponse Response = context.Response;
        System.IO.Stream str = Request.InputStream;
        StreamReader reader = new StreamReader(str);
        string request = reader.ReadToEnd();
        string res = string.Empty;

        // Remove encoding="utf-8" => .NET adds it and DI Server doesn't accept it!
        int index = request.IndexOf("encoding=\"utf-");
        if (index > 0 && index < 50)
            request = request.Remove(index, 16);

        //call DI Server
        try
        {
            res = DIServerNode.Interact(request);

            // For Java compliance / Not accepted by .NET in fault cases
            //if (Request.ContentType.StartsWith("text/xml") && res.Contains("http://www.w3.org/2003/05/soap-envelope"))
            //{
            //  res = res.Replace("http://www.w3.org/2003/05/soap-envelope", "http://schemas.xmlsoap.org/soap/envelope/");
            //}

            // For Java compliance / Accepted by .NET
            // Appending prefix for the the SOAP 1.2 Subcode to be namespace-qualified
            // <env:Subcode><env:Value> replaced with <env:Subcode><env:Value>env:
            if (res.Contains("Fault"))
            {
                res = res.Replace("<env:Subcode><env:Value>", "<env:Subcode><env:Value>env:");
            }
        }
        catch (Exception ex)
        {
            res = ex.Message;
        }

        // return the response to the caller 
        // same content type as the request
        Response.Clear();
        Response.AppendHeader("Content-Type", Request.ContentType);
        Response.Write(res);
    }


    public bool IsReusable
    {
        // To enable pooling, return true here.
        // This keeps the handler in memory.
        get { return false; }
    }
}