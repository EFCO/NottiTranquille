package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.SingletonEmployee;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.Scout;
import it.ispw.efco.nottitranquille.model.DAO.RequestDAO;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import org.joda.time.DateTime;

/***
 * ApproveInsertRequest Controller.
 * <br>
 * This Use Case shows the process by which a Scout can approve (or modify) a {@link Request} of a {@link it.ispw.efco.nottitranquille.model.Structure}.
 *
 * @see it.ispw.efco.nottitranquille.view.ScoutApproveInsertRequestForm
 * @see Request
 * @see RequestStatus
 */
public class ApproveInsertRequest {

    /**
     * Default constructor
     */
    public ApproveInsertRequest() {
    }

    /**
     * Changes the {@link RequestStatus} for the given {@link Request}.
     *
     * @param request the request to change
     * @param requestStatus the new Request Status
     */
    public static void changeRequestStatus(Request request, RequestStatus requestStatus) {
        // Sets new status and lastModifiedDate
        request.setStatus(requestStatus);
        request.setLastModified(new DateTime());

        // Sets also acceptedDate if the Request is accepted
        if (requestStatus == RequestStatus.ACCEPTED) {
            request.setAcceptedDate(new DateTime());
        } else {    // otherwise sets null
            request.setAcceptedDate(null);
        }

        // Sets reviewer
        try {
            request.setReviewedBy((Scout) SingletonEmployee.getInstance().getEmployee().getRole("Scout"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Updates Request
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.update(request);
    }
}