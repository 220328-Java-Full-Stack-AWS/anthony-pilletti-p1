package com.revature.ers.dao;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Status;
import com.revature.ers.models.Type;
import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionManager;

import java.sql.*;
import java.util.*;



public class ReimbursementDaoImp implements ReimbursementDao{


    @Override
    public Reimbursement createReimbursement(Reimbursement r) {
        String sql = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT,REIMB_AUTHOR,REIMB_STATUS_ID,REIMB_TYPE_ID) "
                    + "VALUES (?,?,?,?)";
        try{
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, r.getAmount());
            pstmt.setInt(2, r.getAuthor().getId());
            pstmt.setInt(3, r.getStatus().toInt());
            pstmt.setInt(4, r.getType().toInt());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()){
                int key = keys.getInt(1);
                r.setId(key);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public List<Reimbursement> getReimbursementUser(User u) {

        List<Reimbursement> list = new ArrayList<>();

        try{
            String sql = "SELECT er.REIMB_ID, er.REIMB_AMOUNT, er.REIMB_SUBMITTED, er.REIMB_RESOLVED, eu.username as resolver_username  , ers_reimbursement_status.reimb_status, ers_reimbursement_type.reimb_type "
                + "from ers_reimbursement er "
                + "join ers_reimbursement_status "
                + "on er.reimb_status_id = ers_reimbursement_status.reimb_status_id "
                + "join ers_reimbursement_type "
                + "on er.reimb_type_id = ers_reimbursement_type.reimb_type_id "
                + "left join ers_users eu "
                + "on er.reimb_resolver = eu.user_id "
                + "where er.reimb_author = ? "
                + "order by er.reimb_id ";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, u.getId());

            ResultSet results = pstmt.executeQuery();
            while(results.next()){
                Reimbursement r = new Reimbursement();
                r.setId(results.getInt("reimb_id"));
                r.setAmount(results.getInt("reimb_amount"));
                r.setSubmitted(results.getDate("reimb_submitted"));
                r.setResolved(results.getDate("reimb_resolved"));
                //r.setResolver(results.getString("resolver_username"));
                r.setStatus(results.getString("reimb_status"));
                r.setType(results.getString("reimb_type"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reimbursement> getReimbursementByID(int id) {
        List<Reimbursement> list = new ArrayList<>();
        String sql = "SELECT er.REIMB_ID, er.REIMB_AMOUNT, er.REIMB_SUBMITTED, er.REIMB_RESOLVED, a.username as author_username, r.username as resolver_username  , ers_reimbursement_status.reimb_status, ers_reimbursement_type.reimb_type "
            + "from ers_reimbursement er "
            + "join ers_reimbursement_status "
            + "on er.reimb_status_id = ers_reimbursement_status.reimb_status_id "
            + "join ers_reimbursement_type"
            + "on er.reimb_type_id = ers_reimbursement_type.reimb_type_id "
            + "left join ers_users a "
            + "on er.reimb_AUTHOR = a.user_id "
            + "left join ers_users r "
            + "on er.reimb_resolver = r.user_id "
            + "where er.reimb_id = ? ";
        try{
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                Reimbursement r = new Reimbursement();
                r.setId(results.getInt("reimb_id"));
                r.setAmount(results.getInt("reimb_amount"));
                r.setSubmitted(results.getDate("reimb_submitted"));
                r.setResolved(results.getDate("reimb_resolved"));
                //r.setAuthor(results.getString("author_username"));
                //r.setResolver(results.getString("resolver_username"));
                r.setStatus(results.getString("reimb_status"));
                r.setType(results.getString("reimb_type"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateReimbursement(Reimbursement r, User u) {
        String sql = "UPDATE ERS_REIMBURSEMENT SET reimb_status_id=?, reimb_resolver=?  WHERE reimb_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, r.getStatus().toInt());
            pstmt.setInt(2, u.getId());
            pstmt.setInt(3, r.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelReimbursement(int id) {
        String sql = "DELETE FROM ERS_REIMBURSEMENT WHERE REIMB_ID=?";

        try{
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelReimbursement(Reimbursement r) {
        int id = r.getId();
        String sql = "DELETE FROM ERS_REIMBURSEMENT WHERE REIMB_ID=?";

        try{
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
