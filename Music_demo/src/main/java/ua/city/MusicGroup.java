package ua.city;

public class MusicGroup {
    private int groupId;
    private String groupName;

    // Конструктор за замовченням
    public MusicGroup() {
    }

    // Конструктор з параметрами
    public MusicGroup(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    // Геттери та сеттери
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}



