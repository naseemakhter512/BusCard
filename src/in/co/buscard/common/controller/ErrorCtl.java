package in.co.buscard.common.controller;
import in.co.buscard.util.ServletUtility;
import in.co.buscard.wsx.controller.ORSView;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ErrorCtl extends BaseCtl
{
	/**
	 * Logger to log the messages.
	 */
	public static final String OP_SAVE_UP = "Save";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forwardView(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}