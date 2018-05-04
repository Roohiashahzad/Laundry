package com.roohia.hp.laundry.model.database;




import com.roohia.hp.laundry.model.dbo.Order;
import com.roohia.hp.laundry.model.dbo.OrderItems;
import com.roohia.hp.laundry.model.dbo.User;
import com.roohia.hp.laundry.model.utils.PreferenceUtils;

import java.util.List;


public class DBHandler {
    private static DBHandler ourInstance = new DBHandler();

    private DBHandler() {
    }

    public static DBHandler getInstance() {
        return ourInstance;
    }

    public long getNextUserId() {
        List<User> user = User.listAll(User.class);
        if (user != null && user.size() > 0)
            return user.get(user.size() - 1).getId() + 1;
        else
            return 1;
    }

    public long getNextOrderId() {
        List<Order> order = Order.listAll(Order.class);
        if (order != null && order.size() > 0)
            return order.get(order.size() - 1).getId() + 1;
        else
            return 1;
    }

    public long getNextItemId() {
        List<OrderItems> orderItems = OrderItems.listAll(OrderItems.class);
        if (orderItems != null && orderItems.size() > 0)
            return orderItems.get(orderItems.size() - 1).getId() + 1;
        else
            return 1;
    }

    public User getCurrentUser() {
        List<User> list = User.find(User.class, "User_Email = ?", PreferenceUtils.getUserEmail());
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void saveLoginUser(User user) {
        List<User> list = User.find(User.class, "User_Email = ?",user.getUserEmail());
        if (list.size() > 0) {
            User old = list.get(0);
            old.setFullName(user.getUserName());
            //old.setAddress(user.getAddress());
            old.setUserName(user.getUserName());
            //old.setContact(user.getContact());
            old.setUserEmail(user.getUserEmail());
            old.setUserId(user.getUserId());
            old.save();
        } else {
            user.save();
        }
    }

    public void saveUser(User user) {
        List<User> list = User.find(User.class, "User_Email = ?",user.getUserEmail());
        if (list.size() > 0) {
            User old = list.get(0);
            old.setFullName(user.getFullName());
            old.setAddress(user.getAddress());
            old.setUserName(user.getUserName());
            old.setContact(user.getContact());
            old.setUserEmail(user.getUserEmail());
            old.setUserId(user.getUserId());
            old.save();
        } else {
            user.save();
        }
    }

/*
    public List<Category> getCategoriesByAreaId(int parentId) {
        List<Category> categories = new ArrayList<>();
        categories = Category.find(Category.class, "Parent_Id = ?", parentId + "");
        return categories;
    }

    public List<SubCategory> getSubCategoriesByCategoryId(int parentId) {
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories = SubCategory.find(SubCategory.class, "Parent_Id = ?", parentId + "");
        return subCategories;
    }

    public List<SubLocation> getSubLocationsByLocationId(int officeId) {
        List<SubLocation> subLocations = new ArrayList<>();
        subLocations = SubLocation.find(SubLocation.class, "Senior_Office_Id=?", officeId + "");
        return subLocations;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        locations = Location.listAll(Location.class);
        return locations;
    }

    public List<UserIssue> getAllUserIssues() {
        List<UserIssue> userIssues = new ArrayList<>();
        userIssues = UserIssue.listAll(UserIssue.class);
        return userIssues;
    }

    public List<Area> getAllAreas() {
        List<Area> areas = new ArrayList<>();
        areas = Area.listAll(Area.class);
        return areas;
    }

    public void saveSyncData(ArrayList<Area> areas, ArrayList<Category> categories, ArrayList<SubCategory> subCategories, ArrayList<Location> locations, ArrayList<SubLocation> subLocations, ArrayList<UserIssue> userIssues) {

        for (Area area : areas) {
            List<Area> existingAreas = Area.find(Area.class, "LOV_Detail_Id=?", area.getLOVDetailId() + "");
            if (existingAreas.size() > 0) {
                Area tempArea = existingAreas.get(0);
                tempArea.setLOVDetailId(area.getLOVDetailId());
                tempArea.setLOVDesc(area.getLOVDesc());
                tempArea.setParentId(area.getParentId());
                tempArea.setLOVOrder(area.getLOVOrder());
                tempArea.setVisibleExpression(area.getVisibleExpression());
                tempArea.setInVisibleExpression(area.getInVisibleExpression());
                tempArea.setPrevRefNo(area.getPrevRefNo());
                tempArea.setEntryDate(area.getEntryDate());
                tempArea.setActive(area.isActive());
                tempArea.save();
            } else
                area.save();
        }


        for (Category category : categories) {
            List<Category> existingCats = Category.find(Category.class, "LOV_Detail_Id=?", category.getLOVDetailId() + "");
            if (existingCats.size() > 0) {
                Category tempCat = existingCats.get(0);
                tempCat.setLOVId(category.getLOVId());
                tempCat.setLOVDesc(category.getLOVDesc());
                tempCat.setParentId(category.getParentId());
                tempCat.setLOVOrder(category.getLOVOrder());
                tempCat.setVisibleExpression(category.getVisibleExpression());
                tempCat.setInVisibleExpression(category.getInVisibleExpression());
                tempCat.setPrevRefNo(category.getPrevRefNo());
                tempCat.setEntryDate(category.getEntryDate());
                tempCat.setActive(category.isActive());
                tempCat.save();
            } else
                category.save();
        }

        for (SubCategory subCategory : subCategories) {
            List<SubCategory> existingSubCats = SubCategory.find(SubCategory.class, "LOV_Detail_Id=?", subCategory.getLOVDetailId() + "");
            if (existingSubCats.size() > 0) {
                SubCategory tempSubCat = existingSubCats.get(0);
                tempSubCat.setLOVId(subCategory.getLOVId());
                tempSubCat.setLOVDesc(subCategory.getLOVDesc());
                tempSubCat.setParentId(subCategory.getParentId());
                tempSubCat.setLOVOrder(subCategory.getLOVOrder());
                tempSubCat.setVisibleExpression(subCategory.getVisibleExpression());
                tempSubCat.setInVisibleExpression(subCategory.getInVisibleExpression());
                tempSubCat.setPrevRefNo(subCategory.getPrevRefNo());
                tempSubCat.setEntryDate(subCategory.getEntryDate());
                tempSubCat.setActive(subCategory.isActive());
                tempSubCat.save();
            } else
                subCategory.save();
        }

        for (Location location : locations) {
            List<Location> existingLocations = Location.find(Location.class, "Office_Id=?", location.getOfficeId() + "");
            if (existingLocations.size() > 0) {
                Location tempLocation = existingLocations.get(0);
                tempLocation.setOfficeName(location.getOfficeName());
                tempLocation.setSeniorOfficeId(location.getSeniorOfficeId());
                tempLocation.save();
            } else
                location.save();
        }

        for (SubLocation sublocation : subLocations) {
            List<SubLocation> existingSubLocations = SubLocation.find(SubLocation.class, "Office_Id=?", sublocation.getOfficeId() + "");
            if (existingSubLocations.size() > 0) {
                SubLocation tempSubLocation = existingSubLocations.get(0);
                tempSubLocation.setOfficeName(sublocation.getOfficeName());
                tempSubLocation.setSeniorOfficeId(sublocation.getSeniorOfficeId());
                tempSubLocation.save();
            } else
                sublocation.save();
        }


        for (UserIssue userIssue : userIssues) {
            Issue issue = userIssue.getIssue();
            List<UserIssue> existingUserIssues = UserIssue.find(UserIssue.class, "Issue_Id=?", userIssue.getIssueId() + "");
            if(existingUserIssues.size() > 0){
                UserIssue tempUserIssue = existingUserIssues.get(0);
                Issue tempIssue = tempUserIssue.getIssue();
                long id = tempIssue.getId();
                tempIssue.delete();
                issue.setId(id);
                issue.save();
                tempUserIssue.setIssue(issue);
                tempUserIssue.setArea(userIssue.getArea());
                tempUserIssue.setCategory(userIssue.getCategory());
                tempUserIssue.setSubcat(userIssue.getSubcat());
                tempUserIssue.setSubLocation(userIssue.getSubLocation());
                tempUserIssue.setIssueStatusDesc(userIssue.getIssueStatusDesc());
                tempUserIssue.save();

            }else {
                issue.save();
                userIssue.save();
            }
        }

    }
*/



}
