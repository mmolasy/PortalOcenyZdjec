package pl.molasym.photoGrade.sql;

import pl.molasym.photoGrade.entities.Invitation;

/**
 * Created by moliq on 20.11.16.
 */
public class InvitationSQL {
    public static final String getInvitationStatus = "Select i.status from Invitation i where i.id= :invitationId";
    public static final String getInvitationByUsers = "Select i from Invitation i where i.from= :userFrom and i.to= :userTo";
    public static final String getInvitationById = "Select i from Invitation i where i.id= :invitationId";
    public static final String getInvitationToUser = "Select i from Invitation i where i.to= :user";


}
