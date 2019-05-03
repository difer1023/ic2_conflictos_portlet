package co.com.ic2.conflictos.portlet;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import co.com.ic2.facade.GrupoInvestigacionFacade;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * Portlet implementation class ProductosNoClasificadosPortlet
 */
public class ProductosNoClasificadosPortlet extends GenericPortlet {
 
	public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

    	GrupoInvestigacionFacade grupoInvestigacionFacade=new GrupoInvestigacionFacade();
    	User user = null;
		try {
			user = PortalUtil.getUser(renderRequest);
			
			int codigoGrupo=Integer.parseInt((String) user.getExpandoBridge().getAttribute("codigoGrupo"));
			
	    	JSONArray productos=JSONFactoryUtil.createJSONArray(grupoInvestigacionFacade.consultarProductosSinClasificacion(codigoGrupo));
			
	    	renderRequest.setAttribute("productosNoClasificados",productos );
	    	
	    	include(viewTemplate, renderRequest, renderResponse);
	    	
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
 
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(ProductosNoClasificadosPortlet.class);
}
