public class ClassRooms {

    private int classroomId;
    private String className;

    public ClassRooms(int classroomId, String className){
        this.classroomId = classroomId;
        this.className = className;
    }

    @Override
    public String toString() {
        return className;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
