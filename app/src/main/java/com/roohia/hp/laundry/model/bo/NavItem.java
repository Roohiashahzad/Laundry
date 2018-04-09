package com.roohia.hp.laundry.model.bo;



public class NavItem {
    private String title;
    private int icon;
    private boolean isSelected;

    public NavItem(String title, int icon, boolean isSelected) {


        this.title = title;
        this.icon = icon;
        this.isSelected = isSelected;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
